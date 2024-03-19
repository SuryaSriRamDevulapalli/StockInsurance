package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.InsuranceEntity;
import com.example.repo.InsuranceRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class InsuranceService {
	

	@Autowired
    private InsuranceRepo repo;
	
	public Mono<InsuranceEntity> insertintorepo(InsuranceEntity entity) {
		
		String uniqueid =  String.valueOf(generateid());
		entity.setId(uniqueid);
		entity.setRiskfactor(generateriskfactor(entity));
		entity.setCoverpremiumvlaue(generatecoverpremium(entity));
		entity.setInsurancecalculation(insuranceCalculation(entity));
		entity.setAsNew(true);
		log.info("Inserting InsuranceEntity into the repository: {}", entity);
		return repo.save(entity);
	}
	
	private int generateid() {
		return new Random().nextInt(9000000) + 1000000;
	}
	
	private double generateriskfactor(InsuranceEntity entity) {
		String type = entity.getTypeofstock();
		Double riskfactor = 0.0;
		
		if(type.equalsIgnoreCase("Buliding")) {
			riskfactor = 0.85;
		}else if(type.equalsIgnoreCase("Content")){
			riskfactor = 0.80;
		}else if(type.equalsIgnoreCase("Inventory")){
			riskfactor = 0.75;
		}
		return riskfactor;
	}
	
	private int generatecoverpremium(InsuranceEntity entity) {
		
		int count = entity.getCoverpremiumcount();
		int value = 0 ;
		
		if(count==1) {
			value = 2000;
		}else if(count==2){
			value = 2500;
		}else if(count==3){
			value = 3000;
		}
		else if(count==0){
			value = 0;
		}
		return value;
	}
	
	private double insuranceCalculation(InsuranceEntity entity) {
		
		int premiumvalue = entity.getCoverpremiumvlaue();
		Double riskfactor = entity.getRiskfactor();
		int stock = entity.getStockvalue();
		int premiumcharges = entity.getPremiumcharges();
		
		double insurance = (premiumcharges + (stock* riskfactor) + premiumvalue)/12;
		return insurance;
	}

	public Mono<InsuranceEntity> getbyid(String id) {
		log.info("retreiving insurance entity by id: {}"+id);
		Mono<InsuranceEntity> entity = repo.findById(id);
		return entity;
	}

	public Flux<InsuranceEntity> getAllProducts() {
	Flux<InsuranceEntity> entity = repo.findAll();
		return entity;
	}
}
