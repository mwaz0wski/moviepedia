package com.okode.moviepedia.api.repository;

import com.okode.moviepedia.api.model.ERole;
import com.okode.moviepedia.api.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
