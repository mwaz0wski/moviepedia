package com.okode.moviepedia.api.service;

import com.okode.moviepedia.api.model.ImageUrlResponse;
import com.okode.moviepedia.api.model.Movie;
import com.okode.moviepedia.api.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.okode.moviepedia.utils.AppConstants.*;

@Service
public class MovieService {

  private final WebClient webClient;

  @Value(API_KEY_PROPERTY)
  private String apiKey;

  @Autowired
  public MovieService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Movie> getById(long movieId) {
    StringBuilder uri =
        new StringBuilder(MOVIE_BASE_URL)
            .append("/movie/")
            .append(movieId)
            .append("?api_key=")
            .append(this.apiKey);
    return this.webClient.get().uri(uri.toString()).retrieve().bodyToMono(Movie.class);
  }

  public Mono<QueryResponse> queryByTitle(String title, Long page) {
    /*
      If a page parameter was included in the request, add the parameter to the
      composed url
    */
    StringBuilder pageValue = new StringBuilder();
    if (page != null) {
      pageValue.append("&page=").append(page);
    }
    StringBuilder uriWithoutPage =
        new StringBuilder(MOVIE_BASE_URL)
            .append("/search/movie?api_key=")
            .append(this.apiKey)
            .append("&query=")
            .append(title);
    String finalUri = uriWithoutPage.append(pageValue).toString();
    return this.webClient
        .get()
        .uri(finalUri)
        .retrieve()
        .bodyToMono(QueryResponse.class)
        .flatMap(response -> handleQueryResponse(response, page, uriWithoutPage));
  }

  private Mono<QueryResponse> handleQueryResponse(
      QueryResponse response, Long page, StringBuilder uriWithoutPage) {
    if (page != null && response.getTotalPages() < page) {
      return this.webClient
          .get()
          .uri(uriWithoutPage.append("&page=").append(response.getTotalPages()).toString())
          .retrieve()
          .bodyToMono(QueryResponse.class);
    } else {
      return Mono.just(response);
    }
  }

  public Mono<ImageUrlResponse> getImageUrl(long movieId, long size) {
    return getById(movieId).flatMap(movie -> generateImageUrl(movie, size));
  }

  private Mono<ImageUrlResponse> generateImageUrl(Movie movie, long size) {
    String imagePath = movie.getPosterPath();
    ImageUrlResponse imageUrl = new ImageUrlResponse();
    if (imagePath != null) {
      imageUrl.setImageUrl(
          new StringBuilder(IMAGE_BASE_URL)
              .append("/w")
              .append(size)
              .append("/")
              .append(imagePath)
              .toString());
    } else {
      imageUrl.setImageUrl("not-found");
    }
    return Mono.just(imageUrl);
  }
}
