package com.adnstyle.choicafe.oauth2;


import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public class GoogleOAuth2User implements CustomOAuth2User, Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> attributes;

    public GoogleOAuth2User (Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String GetName() {
        return (String) attributes.get("name");
    }
}
