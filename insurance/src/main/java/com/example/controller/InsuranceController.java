package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.InsuranceEntity;
import com.example.repo.InsuranceRepo;
import com.example.service.InsuranceService;

import reactor.core.publisher.Flux;
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
	public Mono<InsuranceEntity> createinsurance(@RequestBody InsuranceEntity entity) {
		return service.insertintorepo(entity);
		
	}
	
	@GetMapping("/stock/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<InsuranceEntity> getinsurancebyid(@PathVariable String id){
	return service.getbyid(id);
	}
	
	@GetMapping("/stock")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Flux<InsuranceEntity> getAllInsurances(){
		return service.getAllProducts();
	}
	
	 @PutMapping("/stock/{id}")
	 @ResponseStatus(HttpStatus.ACCEPTED)
	 public Mono<InsuranceEntity> updateInsurance(@PathVariable String id, @RequestBody InsuranceEntity entity){
		return service.updatebyId(id,entity);	 
	 }
	 
		@DeleteMapping("/stock/{id}")
		@ResponseStatus(HttpStatus.OK)
		public Mono<Void> deletebyid(@PathVariable String id) {
			return service.deleteById(id);
		}

}
