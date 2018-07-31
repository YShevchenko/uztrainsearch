package com.uztrainsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uztrainsearch.model.searchresult.UzSearchData;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UzSearchResult {
    private UzSearchData data;
}