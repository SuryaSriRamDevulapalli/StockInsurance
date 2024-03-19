package com.example.repo;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.InsuranceEntity;

@Repository
public interface InsuranceRepo extends ReactiveCrudRepository<InsuranceEntity, String>{
	
}
