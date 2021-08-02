package com.okode.moviepedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryResponse {
  private long page;
  @JsonProperty("results")
  private List<Movie> movieList;
  @JsonProperty("total_pages")
  private long totalPages;
  @JsonProperty("total_results")
  private long totalResults;
}
