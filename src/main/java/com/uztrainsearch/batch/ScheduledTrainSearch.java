package com.uztrainsearch.batch;

import com.uztrainsearch.model.UzSearchResult;
import com.uztrainsearch.service.NotificationMessageService;
import com.uztrainsearch.service.UzSearchRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTrainSearch {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTrainSearch.class);

    private UzSearchRestService searchRestService;
    private NotificationMessageService messageService;

    public ScheduledTrainSearch(UzSearchRestService searchRestService, NotificationMessageService messageService) {
        this.searchRestService = searchRestService;
        this.messageService = messageService;
    }

    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        UzSearchResult result = searchRestService.getUzSearchResults();
        if (!result.getData().getList().isEmpty()) {
            messageService.sendTicketsFoundNotification(result);
        } else {
            log.info("No results found");
        }
        log.info("{} results found", result.getData().getList().size());
    }
}
