package com.uztrainsearch.model.searchresult;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UzSearchEntry {
    private String num;
    private Integer category;
    private String travelTime;
    private UzSearchResultFrom from;
    private UzSearchResultTo to;
    private List<UzSearchPlaceType> types;
    private Integer allowStudent;
    private Integer allowBooking;
}
