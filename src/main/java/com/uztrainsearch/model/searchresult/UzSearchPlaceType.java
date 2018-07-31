package com.uztrainsearch.model.searchresult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UzSearchPlaceType {
    private String id;
    private String title;
    private String letter;
    private Integer places;
}
