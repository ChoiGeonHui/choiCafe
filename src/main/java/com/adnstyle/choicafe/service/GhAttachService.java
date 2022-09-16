package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.common.FileUploadDirByYML;
import com.adnstyle.choicafe.domain.GhAttach;
import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.repository.GhAttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Service
@RequiredArgsConstructor
public class GhAttachService {

    private final FileUploadDirByYML fileUploadDirByYML;

    private final GhAttachRepository ghAttachRepository;

    private static final Logger log = LoggerFactory.getLogger(GhAttachService.class);


    public GhAttach selectAttach(String tableType, Long tableSeq) {
        GhAttach ghAttach = new GhAttach();
        ghAttach.setTableType(tableType);
        ghAttach.setTableSeq(tableSeq);
        return ghAttachRepository.selectAttach(ghAttach);
    }


    public void download(GhAttach ghAttach) throws IOException {

        //DB에서 파일 데이터 가져오기
        ghAttach = ghAttachRepository.selectAttach(ghAttach);

        File f = new File(fileUploadDirByYML.getSaveDir() + ghAttach.getSavedDir() + "/" + ghAttach.getSavedName());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        response.setContentType(ghAttach.getType());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(ghAttach.getDisplayName(), "UTF-8") + "\"");
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
     * @param seq
     * @param tableName
     * @param file
     */
    @Transactional
    public void save(Long seq, String tableName, MultipartFile file) {

        if (file == null) {
            return;
        }
        if (file.isEmpty()) {
            return;
        }

        GhAttach ghAttach = new GhAttach();

        String saveDir = getSaveDir(tableName);// 파일 경로 설정

        createDirectory(saveDir); // 파일 폴더 생성 (없을경우)

        String fileExt = file.getContentType(); //파일 타입 생성

        String saveFileName = RandomStringUtils.randomAlphanumeric(20); //파일에 저장할 저장명 설정 (랜덤한 문자 20자)

        try {
            //난수화된 이름으로 파일 업로드
            Path path = Paths.get(saveDir, saveFileName);
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ghAttach.setTableType(tableName);
        ghAttach.setTableSeq(seq);
        ghAttach.setDisplayName(file.getOriginalFilename());
        ghAttach.setSavedName(saveFileName);
        ghAttach.setSavedDir(StringUtils.removeStart(saveDir, fileUploadDirByYML.getSaveDir()));
        ghAttach.setType(fileExt);
        ghAttach.setSize(file.getSize());

        insertAttach(ghAttach);
    }

    @Transactional
    public void update(GhBoard ghBoard, MultipartFile file) {

        /**
         * 파일 업로드시 파일 존재 여부
         *
         *  upload       db         method
         * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
         *  false       false       save
         *  false       ture        save
         *  ture        false       save
         *  ture        ture        delete DB -> insert UploadFile
         *
         * dbDelete     ---->       status = _delete  -> delete DB
         *
         */
        //기존 db에 등록된 파일
        GhAttach ghAttach = ghAttachRepository.selectAttach(ghBoard.getGhAttach());

        //db에 저장된 파일 상태
        String status = ghBoard.getGhAttach().getStatus();

        if (ghAttach != null) { //기존에 등록된 파일 여부

            if (status.equals("_delete") || !file.isEmpty()){
                //DB에 저장된 파일 삭제 요청 or 신규 파일 등록시 -> 기존파일 삭제

                File f = new File(fileUploadDirByYML.getSaveDir() + ghAttach.getSavedDir() + "/" + ghAttach.getSavedName());

                if (f.exists()) {
                    f.delete();
                }
                deleteAttach(ghAttach);

            }
        }
        save(ghBoard.getSeq(), ghBoard.getGhAttach().getTableType(), file);
    }

    @Transactional
    public int insertAttach(GhAttach ghAttach) {
        return ghAttachRepository.insertAttach(ghAttach);
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
