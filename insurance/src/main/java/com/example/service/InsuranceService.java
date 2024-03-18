package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.InsuranceEntity;
import com.example.repo.InsuranceRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class InsuranceService {
	

	@Autowired
    private InsuranceRepo repo;
	
	public Mono<InsuranceEntity> insertintorepo(InsuranceEntity entity) {
		
		String uniqueid =  String.valueOf(generateid());
		entity.setId(uniqueid);
		entity.setAsNew(true);
		log.info("Inserting InsuranceEntity into repository: {}", entity);
		return repo.save(entity);
	}
	
	private int generateid() {
		return new Random().nextInt(9000000) + 1000000;
	}
	
	
	

}
