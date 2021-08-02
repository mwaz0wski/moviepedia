package com.okode.moviepedia.service;

import com.okode.moviepedia.model.ImageUrlResponse;
import com.okode.moviepedia.model.Movie;
import com.okode.moviepedia.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.okode.moviepedia.utils.AppConstants.*;

@Service
public class MovieService {

  private final WebClient webClient;

  @Autowired
  public MovieService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Movie> getById(long movieId) {
    String uriPath = MOVIE_BASE_URL + "/movie/" + movieId + "?api_key=" + API_TOKEN;
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
        MOVIE_BASE_URL + "/search/movie?api_key=" + API_TOKEN + "&query=" + title;
    String uriPathWithPage = uriWithoutPage + pageValue;
    return this.webClient
        .get()
        .uri(uriPathWithPage)
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
