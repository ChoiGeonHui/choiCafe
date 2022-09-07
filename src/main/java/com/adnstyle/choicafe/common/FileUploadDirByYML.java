package com.adnstyle.choicafe.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "spring.file-upload")
public class FileUploadDirByYML {

    /** Base Dir */
    private String saveDir;

    private String saveEditorDir;

    private String tempDir;

    /**Sub Dir*/
    public String getSubDir(){
        Calendar.getInstance().getTime();
        String dir = DateFormatUtils.format(Calendar.getInstance().getTime(),"YYYY/MM");
        return  dir;
    }
}
