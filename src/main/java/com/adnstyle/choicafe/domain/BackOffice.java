package com.adnstyle.choicafe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Alias("backOffice")
@Data
@EqualsAndHashCode(callSuper = false)
public class BackOffice {

    private Long seq;

    private String menuName;

    private String menuRole;

}
