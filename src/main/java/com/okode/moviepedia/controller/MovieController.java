package com.okode.moviepedia.controller;

import com.okode.moviepedia.model.ImageUrlResponse;
import com.okode.moviepedia.model.Movie;
import com.okode.moviepedia.model.QueryResponse;
import com.okode.moviepedia.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/movies")
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/{movieId}")
  public Mono<Movie> getById(@PathVariable int movieId) {
    return movieService.getById(movieId);
  }

  @GetMapping
  public Mono<QueryResponse> queryByTitle(
      @RequestParam String title, @RequestParam(required = false) Long page) {
    return movieService.queryByTitle(title, page);
  }

  @GetMapping(path = "{movieId}/image-resource")
  public Mono<ImageUrlResponse> getImageUrl(
      @PathVariable long movieId, @RequestParam(required = false, defaultValue = "300") long size) {
    return movieService.getImageUrl(movieId, size);
  }
}
