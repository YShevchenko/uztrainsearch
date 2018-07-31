package com.uztrainsearch.service;

import com.uztrainsearch.model.UzSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UzSearchRestService {

    private static final Logger log = LoggerFactory.getLogger(UzSearchRestService.class);

    @Value("${uz.train.search.station.from}")
    private String from;

    @Value("${uz.train.search.station.to}")
    private String to;

    @Value("${uz.train.search.date}")
    private String date;

    @Value("${uz.train.search.time}")
    private String time;

    @Value("${uz.train.search.url}")
    private String searchUrl;

    private RestTemplate restTemplate;

    public UzSearchRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UzSearchResult getUzSearchResults() {
        log.info("Sending request to {} with body {}", searchUrl, getRequestBody());
        ResponseEntity<UzSearchResult> searchResult = restTemplate.exchange(searchUrl, HttpMethod.POST,
                new HttpEntity<>(getRequestBody(), getHeaders()), UzSearchResult.class);
        return searchResult.getBody();
    }

    private String getRequestBody() {
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("from").append("=").append(from).append("&")
                .append("to").append("=").append(to).append("&")
                .append("date").append("=").append(date).append("&")
                .append("time").append("=").append(time);
        return requestBody.toString();
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        return headers;
    }
}
