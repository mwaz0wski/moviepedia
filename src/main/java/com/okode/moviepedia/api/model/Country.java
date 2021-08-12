package com.okode.moviepedia.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country {
    @JsonProperty("iso_3166_1")
    private String iso;
    private String name;
}
