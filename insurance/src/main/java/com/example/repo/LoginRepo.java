package com.example.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.entity.LoginEntity;

import reactor.core.publisher.Mono;

public interface LoginRepo extends ReactiveCrudRepository<LoginEntity, String> {
	
	Mono<LoginEntity> findByUsername(String username);

	Mono<LoginEntity> findByUsernameAndPassword(String username, String password);
}
