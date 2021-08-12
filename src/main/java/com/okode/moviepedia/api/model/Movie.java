package com.okode.moviepedia.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
  private long id;

  private boolean adult;

  @JsonProperty("backdrop_path")
  private String backdropPath;

  @JsonProperty("poster_path")
  private String posterPath;

  @JsonProperty("belongs_to_collection")
  private Collection collection;

  private long budget;

  private List<Genre> genres;

  private String homepage;

  @JsonProperty("imdb_id")
  private String imdbId;

  @JsonProperty("genre_ids")
  private List<Integer> genreIds;

  @JsonProperty("original_language")
  private String originalLanguage;

  @JsonProperty("original_title")
  private String originalTitle;

  private String overview;

  private double popularity;

  @JsonProperty("release_date")
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date releaseDate;

  private String title;

  private boolean video;

  @JsonProperty("vote_average")
  private double voteAverage;

  @JsonProperty("vote_count")
  private long voteCount;

  @JsonProperty("production_companies")
  private List<Company> productinoCompanies;

  @JsonProperty("production_countries")
  private List<Country> productionCountries;

  private long revenue;

  private long runtime;

  @JsonProperty("spoken_languages")
  private List<Language> spokenLanguages;

  private String status;

  private String tagline;
}
