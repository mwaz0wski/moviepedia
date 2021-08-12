package com.okode.moviepedia.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageUrlResponse {
    @JsonProperty("image_url")
    private String imageUrl;
}
