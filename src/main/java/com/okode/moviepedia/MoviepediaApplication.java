package com.okode.moviepedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import static com.okode.moviepedia.utils.Util.BASE_URL;

@SpringBootApplication
public class MoviepediaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviepediaApplication.class, args);
  }

  @Bean
  public WebClient getWebClient() {
    return WebClient.builder().baseUrl(BASE_URL).build();
  }
}
