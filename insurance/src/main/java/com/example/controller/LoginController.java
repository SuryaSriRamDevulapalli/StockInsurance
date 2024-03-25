package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.LoginEntity;
import com.example.repo.LoginRepo;
import com.example.service.LoginService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = 
"http://localhost:3000/")

@RestController
@RequestMapping("/api/v2")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@Autowired
	LoginRepo repo;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<LoginEntity> register(@RequestBody LoginEntity entity){
		return service.registerUser(entity);
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Boolean> validateuser(@RequestBody LoginEntity entity){
		return service.validateUser(entity.getId(),entity.getUsername(), entity.getPassword());
	}
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Flux<LoginEntity> getAllInsurances(){
		return service.getAllUsers();
	}
	
	@GetMapping("/register/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<LoginEntity> getinsurancebyid(@PathVariable String id){
	return service.getbyid(id);
	}
	
	@GetMapping("/checkusername/{username}")
	@ResponseStatus(HttpStatus.FOUND)
    public Mono<Boolean> checkusername(@PathVariable String username) {
        return service.checkUsername(username);
    }

}
