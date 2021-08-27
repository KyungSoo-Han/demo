package com.demo.api;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/naver")
public class NaverLoginApiController {

    @GetMapping("/auth")
    public String authNaver(@RequestParam String code,@RequestParam String state) {
        String accessToken = extractAccessToken(requestAccessToken(generateAuthCodeRequest(code,state)).getBody());
        return requestProfile(generateProfileRequest(accessToken)).getBody();
    }

    private HttpEntity<MultiValueMap<String,String>> generateAuthCodeRequest(String code,String state){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "YDDtvwoP3y0CXZIgYHg_");
        params.add("client_secret", "YTV8OWGlll");
        params.add("code", code);
        return new HttpEntity<>(params, headers);
    }

    private ResponseEntity<String> requestAccessToken(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                request,
                String.class
        );
    }
    private ResponseEntity<String> requestProfile(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                request,
                String.class
        );
    }

    private HttpEntity<MultiValueMap<String, String>> generateProfileRequest(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return new HttpEntity<>(headers);
    }

    private String extractAccessToken(String accessTokenResponse) {
        JSONObject jsonObject = new JSONObject(accessTokenResponse);
        return jsonObject.getString("access_token");
    }
}
