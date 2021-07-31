package com.okode.moviepedia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Query {
  private long page;
  private List<Movie> results;
}
