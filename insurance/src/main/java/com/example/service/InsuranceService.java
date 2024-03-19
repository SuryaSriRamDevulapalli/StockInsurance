package com.example.service;



import com.example.dto.InsuranceDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InsuranceService {
	
	Mono<InsuranceDto> insertintorepo(InsuranceDto dto);

	Mono<InsuranceDto> getbyid(String modelid);

	Mono<InsuranceDto> updatebyId(String id, InsuranceDto dto);

	Flux<InsuranceDto> getAllProducts();
	
	Mono<Void> deleteById(String id);


}
