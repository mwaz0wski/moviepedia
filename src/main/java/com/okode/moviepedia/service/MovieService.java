package com.okode.moviepedia.service;

import com.okode.moviepedia.model.Movie;
import com.okode.moviepedia.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.okode.moviepedia.utils.Util.API_TOKEN;

@Service
public class MovieService {

  private final WebClient webClient;

  @Autowired
  public MovieService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Movie> getById(int movieId) {
    String uriPath = "/movie/" + movieId + "?api_key=" + API_TOKEN;
    return this.webClient.get().uri(uriPath).retrieve().bodyToMono(Movie.class);
  }

  public Mono<List<Movie>> queryByTitle(String title) {
    String uriPath = "/search/movie?api_key=" + API_TOKEN + "&query=" + title;
    return this.webClient
        .get()
        .uri(uriPath)
        .retrieve()
        .bodyToMono(Query.class)
        .map(Query::getResults);
  }
}
