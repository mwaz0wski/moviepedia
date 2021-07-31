package com.okode.moviepedia.controller;

import com.okode.moviepedia.model.Movie;
import com.okode.moviepedia.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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
  public Mono<List<Movie>> queryByTitle(@RequestParam String title) {
    return movieService.queryByTitle(title);
  }
}
