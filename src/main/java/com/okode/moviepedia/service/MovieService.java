package com.okode.moviepedia.service;

import com.okode.moviepedia.model.ImageUrlResponse;
import com.okode.moviepedia.model.Movie;
import com.okode.moviepedia.model.QueryResponse;
import com.okode.moviepedia.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.okode.moviepedia.utils.AppConstants.*;

@Service
public class MovieService {

  private final WebClient webClient;
  private String apiKey;

  @Autowired
  public MovieService(WebClient webClient, Environment env) {
    this.webClient = webClient;
    this.apiKey = env.getProperty(AppConstants.API_PROPERTY_NAME);
  }

  public Mono<Movie> getById(long movieId) {
    String uriPath = MOVIE_BASE_URL + "/movie/" + movieId + "?api_key=" + this.apiKey;
    return this.webClient.get().uri(uriPath).retrieve().bodyToMono(Movie.class);
  }

  public Mono<QueryResponse> queryByTitle(String title, Long page) {
    /*
      If a page parameter was included in the request, add the parameter to the
      composed url
    */
    String pageValue = "";
    if (page != null) {
      pageValue = "&page=" + page;
    }
    String uriWithoutPage =
        MOVIE_BASE_URL + "/search/movie?api_key=" + this.apiKey + "&query=" + title;
    String finalUri = uriWithoutPage + pageValue;
    return this.webClient
        .get()
        .uri(finalUri)
        .retrieve()
        .bodyToMono(QueryResponse.class)
        .flatMap(response -> handleQueryResponse(response, page, uriWithoutPage));
  }

  private Mono<QueryResponse> handleQueryResponse(
      QueryResponse response, Long page, String uriWithoutPage) {
    if (page != null && response.getTotalPages() < page) {
      return this.webClient
          .get()
          .uri(uriWithoutPage + "&page=" + response.getTotalPages())
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
      imageUrl.setImageUrl(IMAGE_BASE_URL + "/w" + size + "/" + imagePath);
    } else {
      imageUrl.setImageUrl("not-found");
    }
    return Mono.just(imageUrl);
  }
}
