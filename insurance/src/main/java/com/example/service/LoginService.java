package com.example.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.LoginEntity;
import com.example.repo.LoginRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class LoginService {
	
	@Autowired
	LoginRepo repo;

	public Mono<LoginEntity> registerUser(LoginEntity entity) {
		log.info("Registering user: {}", entity.getUsername());
		entity.setAsNew(true);
		entity.setId(generateUserID());
		return repo.save(entity);
	}
	
	
	
	public Mono<Boolean> checkUsername(String username) {
		log.info("Checking username availability for: {}", username);
		return repo.findByUsername(username)
                .map(user -> false)
                .defaultIfEmpty(true);
	}
	
    public Mono<Boolean> validateUser(String id,String username, String password) {
    	log.info("Validating user login for username: {}", username);
    	return repo.findByUsernameAndPassword(username, password)
                .map(user -> true)
                .defaultIfEmpty(false);
    }
	
	public String generateUserID() {
	    return String.format("%07d", new Random().nextInt(9979999));
	}




	public Flux<LoginEntity> getAllUsers() {
		Flux<LoginEntity> entity = repo.findAll();
				log.info("Retreiving all in LoginEntity : {}",entity);
				return entity;
	}



	public Mono<LoginEntity> getbyid(String id) {
		log.info("Retreiving InsuranceEntity by id: {}",id);
		Mono<LoginEntity> entity = repo.findById(id);
		log.info("Retreived InsuranceEntity : {}",entity);
		return entity;
	}


}
