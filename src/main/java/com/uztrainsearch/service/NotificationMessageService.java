package com.uztrainsearch.service;

import com.uztrainsearch.model.UzSearchResult;
import com.uztrainsearch.model.searchresult.UzSearchEntry;
import com.uztrainsearch.model.searchresult.UzSearchPlaceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationMessageService {

    private RestTemplate restTemplate;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    @Value("${telegram.sendmessage.url}")
    private String telegramSendMessageUrl;

    public NotificationMessageService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendTicketsFoundNotification(UzSearchResult result) {
        restTemplate.exchange(telegramSendMessageUrl, HttpMethod.POST, new HttpEntity<>(getRequestBody(result), getHeaders()),
                String.class, botToken);
    }

    private String getRequestBody(UzSearchResult result) {
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("chat_id").append("=").append(chatId).append("&")
                .append("text").append("=").append(getMessage(result));
        return requestBody.toString();
    }

    private String getMessage(UzSearchResult result) {
        UzSearchEntry entry = result.getData().getList().get(0);
        String fromStation = entry.getFrom().getStation();
        String fromDate = entry.getFrom().getDate();
        String toStation = entry.getTo().getStation();
        Integer numOfPlaces = entry.getTypes().stream().mapToInt(UzSearchPlaceType::getPlaces).sum();

        return String.format("Знайдено %s квитків зі станціі %s до станції %s на %s", numOfPlaces, fromStation, toStation, fromDate);
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        return headers;
    }
}
