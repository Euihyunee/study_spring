package kh.openapi.jsp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

@RestController
public class OpenApiController {

    @Value("${URL.serviceKey}")
    private String serviceKey;

    @Value("${URL.EndPoint}")
    private String endPoint;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/api/air-info")
    public JsonNode getCtprvnRltmMesureDnsty(@RequestParam(value = "sidoName", defaultValue = "서울") String sidoName) throws IOException, URISyntaxException {
        StringBuilder urlBuilder = new StringBuilder(endPoint); /*URL*/
        urlBuilder.append(serviceKey); /*Service Key*/
        urlBuilder.append("&")
                .append(URLEncoder.encode("returnType", StandardCharsets.UTF_8))
                .append("=").append(URLEncoder.encode("json", StandardCharsets.UTF_8));
        /*한 페이지 결과 수*/
        urlBuilder.append("&")
                .append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8))
                .append("=")
                .append(URLEncoder.encode("100", StandardCharsets.UTF_8));
        /*페이지번호*/
        urlBuilder.append("&")
                .append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8))
                .append("=")
                .append(URLEncoder.encode("1", StandardCharsets.UTF_8));
        /*시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&")
                .append(URLEncoder.encode("sidoName", StandardCharsets.UTF_8))
                .append("=")
                .append(URLEncoder.encode(sidoName, StandardCharsets.UTF_8));
        /*버전별 상세 결과 참고*/
        urlBuilder.append("&")
                .append(URLEncoder.encode("ver", StandardCharsets.UTF_8))
                .append("=")
                .append(URLEncoder.encode("1.0", StandardCharsets.UTF_8));

        URI uri = new URI(urlBuilder.toString());
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //return sb.toString();

        // JSON 파싱
         return objectMapper.readTree(sb.toString());
    }
}


