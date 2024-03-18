package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.InsuranceEntity;
import com.example.repo.InsuranceRepo;
import com.example.service.InsuranceService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vi")
public class InsuranceController {
	
	@Autowired
	InsuranceService service;
	
	@Autowired
	InsuranceRepo repo;
	
	@PostMapping("/stock")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<InsuranceEntity> createproduct(@RequestBody InsuranceEntity entity) {
		return service.insertintorepo(entity);
		
	}

}
