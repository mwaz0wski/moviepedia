package com.okode.moviepedia.api.controller;

import com.okode.moviepedia.api.payload.request.LoginRequest;
import com.okode.moviepedia.api.payload.request.SignupRequest;
import com.okode.moviepedia.api.payload.response.JwtResponse;
import com.okode.moviepedia.api.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signin")
  public Mono<ResponseEntity<JwtResponse>> authenticateUser(
      @Valid @RequestBody LoginRequest loginRequest) {
    return authService.authenticateUser(loginRequest);
  }

  @PostMapping(path = "/signup")
  public Mono<ResponseEntity<?>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }
}
