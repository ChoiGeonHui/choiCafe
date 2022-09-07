package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.common.FileUploadDirByYML;
import com.adnstyle.choicafe.domain.GhAttach;
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
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class GhAttachService {

    private final FileUploadDirByYML fileUploadDirByYML;

    private final GhAttachRepository ghAttachRepository;

    @Transactional
    public void save(Long seq, String tableName, MultipartFile file) {

        if (file == null) {
            return;
        }
        if (file.isEmpty()) {
            return;
        }

        GhAttach ghAttach = new GhAttach();

        String saveDir = getSaveDir(tableName);

        createDirectory(saveDir);

        String fileExt = getFileExt(file.getOriginalFilename());

        String saveFileName = RandomStringUtils.randomAlphanumeric(20);

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
    public int insertAttach(GhAttach ghAttach){
        return ghAttachRepository.insertAttach(ghAttach);
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
