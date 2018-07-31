package com.uztrainsearch.model.searchresult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UzSearchResultFrom {
    private String code;
    private String station;
    private String stationTrain;
    private String date;
    private String time;
    private Integer sortTime;
    private String srcDate;
}
