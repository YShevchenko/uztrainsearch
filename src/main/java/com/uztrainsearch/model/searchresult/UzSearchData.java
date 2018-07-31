package com.uztrainsearch.model.searchresult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UzSearchData {
    private List<UzSearchEntry> list;
}
