package com.okode.moviepedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Language {
  @JsonProperty("english_name")
  private String englishName;

  @JsonProperty("iso_639_1")
  private String iso;

  private String name;
}
