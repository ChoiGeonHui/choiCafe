package com.adnstyle.choicafe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RecaptchaV3 implements Serializable {

    @JsonProperty("success")
    private String success;

    @JsonProperty("challenge_ts")
    private String challengeTs;

    @JsonProperty("hostname")
    private String hostName;

    @JsonProperty("score")
    private double score;

    @JsonProperty("action")
    private String action;


}
