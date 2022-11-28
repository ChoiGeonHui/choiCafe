package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.common.ReCaptchaSettings;
import com.adnstyle.choicafe.common.ReCaptchaSettingsV3;
import com.adnstyle.choicafe.domain.RecaptchaV3;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class RecaptchaService {

    private final static String USER_AGENT = "Mozilla/5.0";

    private final ReCaptchaSettings reCaptchaSettings;

    private final ReCaptchaSettingsV3 reCaptchaSettingsV3;

    public boolean verify(String gRecaptchaResponse) {

        final String url = reCaptchaSettings.getUrl();
        final String secret = reCaptchaSettings.getSecret();


        if (gRecaptchaResponse == null || gRecaptchaResponse.equals("")) {
            return false;
        }

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String reCaptchaV3 (String token) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret",reCaptchaSettingsV3.getSecret());
        map.add("response",token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);

        RecaptchaV3 response = restTemplate.postForObject(reCaptchaSettingsV3.getUrl(), request, RecaptchaV3.class);

        if (response.getSuccess().equals("false")) {
            return "fail";
        }

        if (response.getScore() > 0.5) {
            return "success";
        } else {
            return "fail";
        }

    }

}
