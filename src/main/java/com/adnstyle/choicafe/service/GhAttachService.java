package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.common.FileUploadDirByYML;
import com.adnstyle.choicafe.domain.GhAttach;
import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.repository.GhAttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class GhAttachService {

    private final FileUploadDirByYML fileUploadDirByYML;

    private final GhAttachRepository ghAttachRepository;
    public GhAttach selectAttach(String tableType,Long tableSeq){
        GhAttach ghAttach = new GhAttach();
        ghAttach.setTableType(tableType);
        ghAttach.setTableSeq(tableSeq);
        return ghAttachRepository.selectAttach(ghAttach);
    }

    /**
     * 파일 저장
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

        String fileExt = getFileExt(file.getOriginalFilename()); //파일 확장자명 생성

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
        ghAttach.setSavedDir(StringUtils.removeStart(saveDir,fileUploadDirByYML.getSaveDir()));
        ghAttach.setType(fileExt);
        ghAttach.setSize(file.getSize());

        insertAttach(ghAttach);
    }

    @Transactional
    public void update(GhBoard ghBoard, String tableType, MultipartFile file){
        GhAttach ghAttach = ghAttachRepository.selectAttach(ghBoard.getGhAttach());

        if (ghAttach == null){ //기존에 등록된 파일 여부

        } else {
            if (file.isEmpty()){ //새로 등록할 파일 여부
                return;
            } else {

            }
        }

        save(ghBoard.getSeq(),tableType,file);

    }

    @Transactional
    public int insertAttach(GhAttach ghAttach){
        return ghAttachRepository.insertAttach(ghAttach);
    }

    @Transactional
    public int deleteAttach(GhAttach ghAttach){
        return 1;
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
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    public String getSaveDir(String tableName){
        if (tableName == null || tableName.equals("")){
            return null;
        }
        String saveDir = fileUploadDirByYML.getSaveDir() + "/"+ tableName +"/"+ fileUploadDirByYML.getSubDir();
        return saveDir;
    }


}
