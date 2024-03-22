package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.InsuranceDto;
import com.example.entity.InsuranceEntity;
import com.example.mapper.InsuranceMapper;
import com.example.repo.InsuranceRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class InsuranceServiceImpl implements InsuranceService {
	

	@Autowired
    private InsuranceRepo repo;
	
	public Mono<InsuranceDto> insertintorepo(InsuranceDto dto) {
		
		String uniqueid =  String.valueOf(generateid());
		dto.setId(uniqueid);
		dto.setRiskfactor(generateriskfactor(dto));
		dto.setCoverpremiumvlaue(generatecoverpremium(dto));
		dto.setInsurancecalculation(insuranceCalculation(dto));
		dto.setAsNew(true);
		InsuranceEntity entity = InsuranceMapper.maptoinsuranceentity(dto);
		Mono<InsuranceEntity> inserted = repo.save(entity);
		log.info("Inserting InsuranceEntity into the repository: {}", dto);
		return inserted.map((insurance)->InsuranceMapper.maptoinsurancedto(insurance));
		//return repo.save(entity);
	}
	
	private int generateid() {
		return new Random().nextInt(9000000) + 1000000;
	}
	
	private double generateriskfactor(InsuranceDto dto) {
		String type = dto.getTypeofstock();
		Double riskfactor = 0.0;
		
		if(type.equalsIgnoreCase("Building")) {
			riskfactor = 0.85;
		}else if(type.equalsIgnoreCase("Content")){
			riskfactor = 0.80;
		}else if(type.equalsIgnoreCase("Inventory")){
			riskfactor = 0.75;
		}
		return riskfactor;
	}
	
	private int generatecoverpremium(InsuranceDto dto) {
		
		int count = dto.getCoverpremiumcount();
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
	
	private double insuranceCalculation(InsuranceDto dto) {
		
		int premiumvalue = dto.getCoverpremiumvlaue();
		Double riskfactor = dto.getRiskfactor();
		int stock = dto.getStockvalue();
		int premiumcharges = dto.getPremiumcharges();
		
		double insurance = (premiumcharges + (stock*(1- riskfactor)) + premiumvalue)/12;
		return insurance;
	}

	public Mono<InsuranceDto> getbyid(String id) {
		log.info("Retreiving InsuranceEntity by id: {}",id);
		Mono<InsuranceEntity> entity = repo.findById(id);
		log.info("Retreived InsuranceEntity : {}",entity);
		return entity.map((insurance)->InsuranceMapper.maptoinsurancedto(insurance));
		//return entity;
	}

	public Flux<InsuranceDto> getAllProducts() {
		
		//method 1
		//Flux<InsuranceEntity> entity = repo.findAll();
		//log.info("Retreiving all in InsuranceEntity : {}",entity);
		//return entity.map((insurance)-> InsuranceMapper.maptoinsurancedto(insurance)).switchIfEmpty(Flux.empty());
		
		//we can do either way method 2 gives log for each row retrieved 
		//method 2
		return repo.findAll()
	            .doOnNext(insurance -> log.info("Retrieving InsuranceEntity: {}", insurance))
	            .map(InsuranceMapper::maptoinsurancedto)
	            .switchIfEmpty(Flux.empty());
		
	//return entity;
	}

	public Mono<InsuranceDto> updatebyId(String id, InsuranceDto dto) {
		log.info("Updating InsuranceEntity with id: {}", id);
		
		Mono<InsuranceEntity> newEntity = repo.findById(id);
		
		return newEntity.flatMap(existing->{
			existing.setPremiumcharges(dto.getPremiumcharges());
			existing.setAsNew(false);
			return repo.save(existing);
		}).map(InsuranceMapper::maptoinsurancedto)
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "InsuranceDto not found with id" + id)));
		
		//without mapper class
//		return repo.findById(id)
//				.flatMap(newEntity ->{
//					newEntity.setPremiumcharges(entity.getPremiumcharges());
//					newEntity.setAsNew(false);
//					return repo.save(newEntity);
//				})
//				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
//						"InsuranceEntity not found with id " + id)));	
	}

	public Mono<Void> deleteById(String id) {
		log.info("Deleted InsuranceEntity with id: {}",id);
		return repo.deleteById(id);
	 
	}




}
