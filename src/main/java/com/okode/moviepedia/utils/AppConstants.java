package com.okode.moviepedia.utils;

public class AppConstants {
  // api constants
  public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3";
  public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
  public static final String API_KEY_PROPERTY = "${moviepedia.app.apiKey}";
  // security constants
  public static final String SECRET_PROPERTY = "${moviepedia.app.jwtSecret}";
  public static final String EXPIRATION_MS_PROPERTY = "${moviepedia.app.jwtExpirationMs}";
}
