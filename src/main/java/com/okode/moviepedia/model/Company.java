package com.okode.moviepedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
  private long id;

  @JsonProperty("logo_path")
  private String logoPath;

  private String name;

  @JsonProperty("origin_country")
  private String originCountry;
}
