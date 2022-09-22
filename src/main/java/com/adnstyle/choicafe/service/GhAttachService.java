package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.common.FileUploadDirByYML;
import com.adnstyle.choicafe.domain.GhAttach;
import com.adnstyle.choicafe.repository.GhAttachRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GhAttachService {

    private final FileUploadDirByYML fileUploadDirByYML;

    private final GhAttachRepository ghAttachRepository;

    private static final Logger log = LoggerFactory.getLogger(GhAttachService.class);


    public List<GhAttach> selectAttach(String tableType, Long tableSeq) {
        GhAttach ghAttach = new GhAttach();
        ghAttach.setTableType(tableType);
        ghAttach.setTableSeq(tableSeq);
        return ghAttachRepository.selectAttach(ghAttach);
    }


    /**
     * 이미지 화면 보기, 파일 다운로드
     * @param ghAttach 파일 식별자
     * @param handing 파일 다운로드 여부 ( download / view )
     * @throws IOException
     */
    public void download(GhAttach ghAttach,String handing) throws IOException {


        //DB에서 파일 데이터 가져오기
        ghAttach = ghAttachRepository.selectAttach(ghAttach).get(0);

        File f = new File(fileUploadDirByYML.getSaveDir() + ghAttach.getSavedDir() + "/" + ghAttach.getSavedName());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        response.setContentType(ghAttach.getType());
        
        if (handing.equals("download")) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(ghAttach.getDisplayName(), "UTF-8") + "\"");
        }
        response.setContentLength((int) f.length());

        FileInputStream fin = null;
        FileChannel inputChannel = null;
        WritableByteChannel outputChannel = null;

        try {
            fin = new FileInputStream(f);
            inputChannel = fin.getChannel();

            outputChannel = Channels.newChannel(response.getOutputStream());
            inputChannel.transferTo(0, fin.available(), outputChannel);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (fin != null) fin.close();
                if (inputChannel.isOpen()) inputChannel.close();
                if (outputChannel.isOpen()) outputChannel.close();
            } catch (Exception e) {
                fin.close();
                inputChannel.close();
                outputChannel.close();
            }

        }

    }



    /**
     * 파일 저장
     *
     * @param seq 게시물 식별자
     * @param tableName 참조 테이블
     * @param fileList 첨부파일 리스트
     */
    @Transactional
    public void save(Long seq, String tableName, List<MultipartFile> fileList) {

        if (fileList.get(0) == null) {
            return;
        }
        if (fileList.get(0).isEmpty()) {
            return;
        }


        List<GhAttach> ghAttachList = new ArrayList<>();

        String saveDir = getSaveDir(tableName);// 파일 경로 설정

        createDirectory(saveDir); // 파일 폴더 생성 (없을경우)

        for (MultipartFile file : fileList) {

            String fileExt = file.getContentType(); //파일 타입 생성

            String saveFileName = RandomStringUtils.randomAlphanumeric(20); //파일에 저장할 저장명 설정 (랜덤한 문자 20자)

            try {
                //난수화된 이름으로 파일 업로드
                Path path = Paths.get(saveDir, saveFileName);
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GhAttach ghAttach = new GhAttach();
            ghAttach.setTableType(tableName);
            ghAttach.setTableSeq(seq);
            ghAttach.setDisplayName(file.getOriginalFilename());
            ghAttach.setSavedName(saveFileName);
            ghAttach.setSavedDir(StringUtils.removeStart(saveDir, fileUploadDirByYML.getSaveDir()));
            ghAttach.setType(fileExt);
            ghAttach.setSize(file.getSize());

            ghAttachList.add(ghAttach);

        }
        insertAttach(ghAttachList);
    }


    /**
     * 첨부파일 갱신
     * @param seq 게시물 식별자
     * @param tableType 참조테이블
     * @param ghAttachList 저장된 파일 리스트
     * @param file 업로드할 파일 리스트
     */
    @Transactional
    public void update(Long seq, String tableType,List<GhAttach> ghAttachList,List<MultipartFile> file) {

        GhAttach attach = new GhAttach();
        attach.setTableSeq(seq);
        attach.setTableType(tableType);

        //기존 db에 등록된 파일
        List<GhAttach> DBghAttach = ghAttachRepository.selectAttach(attach);

        for (GhAttach i : DBghAttach) {

            for (GhAttach j : ghAttachList) {

                if (i.getSeq() != j.getSeq()){
                    continue;
                }

                //db에 저장된 파일 상태
                String status = j.getStatus();

                if (status.equals("_delete")) {
                    //DB에 저장된 파일 삭제 요청 -> 기존파일 삭제

                    File f = new File(fileUploadDirByYML.getSaveDir() + i.getSavedDir() + "/" + i.getSavedName());

                    if (f.exists()) {
                        f.delete();
                    }
                    deleteAttach(i);
                }

            }
        }
        save(seq, tableType, file);
    }

    @Transactional
    public int insertAttach(List<GhAttach> ghAttachList) {
        return ghAttachRepository.insertAttach(ghAttachList);
    }

    @Transactional
    public int deleteAttach(GhAttach ghAttach) {
        return ghAttachRepository.deleteAttach(ghAttach);
    }



    private void createDirectory(String saveDir) {
        Path path = Paths.get(saveDir);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


    public String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getSaveDir(String tableName) {
        if (tableName == null || tableName.equals("")) {
            return null;
        }
        String saveDir = fileUploadDirByYML.getSaveDir() + "/" + tableName + "/" + fileUploadDirByYML.getSubDir();
        return saveDir;
    }


}
