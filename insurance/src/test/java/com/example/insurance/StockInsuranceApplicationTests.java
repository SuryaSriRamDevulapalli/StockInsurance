package com.example.insurance;

import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.dto.InsuranceDto;
import com.example.service.InsuranceService;
import com.example.repo.InsuranceRepo;

import reactor.core.publisher.Mono;

@SuppressWarnings("unused")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StockInsuranceApplicationTests {
	@Autowired
	private  InsuranceService insuranceservice;
	
	@Autowired
	private InsuranceRepo repo;
	
	@Autowired
	private WebTestClient webtestclient;

//	  @BeforeEach
//	  public void beforeeach() {
//	  System.out.println("before each test"); 
//	  repo.deleteAll().subscribe(); }
//	 
	private int generateid() {
		return new Random().nextInt(9000000) + 1000000;
	}
	private double generateriskfactor(InsuranceDto dto) {
		String type = dto.getTypeofstock();
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
		
		double insurance = (premiumcharges + (stock* riskfactor) + premiumvalue)/12;
		return insurance;
	}
//	@Test
//	void createproduct() {
//		InsuranceDto dto = new InsuranceDto();
//		dto.setAsNew(true);
//		dto.setTypeofstock("content");
//		dto.setStockvalue(353000);
//		dto.setDamagescovered(353000);
//		dto.setPremiumcharges(2500);
//		dto.setCoverpremiumcount(3);
//		
//		String uniqueid =  String.valueOf(generateid());
//		dto.setId(uniqueid);
//		dto.setRiskfactor(generateriskfactor(dto));
//		dto.setCoverpremiumvlaue(generatecoverpremium(dto));
//		dto.setInsurancecalculation(insuranceCalculation(dto));
//		
//		
//		
//		webtestclient.post().uri("/api/vi/stock").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//		.body(Mono.just(dto),InsuranceDto.class).exchange().expectStatus().isCreated().expectBody().consumeWith(System.out::println)
//		.jsonPath("$.stockvalue").isEqualTo(dto.getStockvalue());
//	}
//	@Test
//	void getbyid() {
//		InsuranceDto dto = new InsuranceDto();
//		dto.setTypeofstock("content");
//		dto.setStockvalue(353000);
//		dto.setDamagescovered(353000);
//		dto.setPremiumcharges(2500);
//		dto.setCoverpremiumcount(3);
//		
//		dto.setRiskfactor(generateriskfactor(dto));
//		dto.setCoverpremiumvlaue(generatecoverpremium(dto));
//		dto.setInsurancecalculation(insuranceCalculation(dto));
//		
//		InsuranceDto inserteddto = insuranceservice.insertintorepo(dto).block();
		
//		webtestclient.get().uri("/api/vi/stock/{id}",Collections.singletonMap("id","2036189")).exchange().expectStatus().isOk().expectBody()
//		.jsonPath("$.id").isEqualTo("2036189").jsonPath("$.typeofstock").exists().jsonPath("$.riskfactor").exists().jsonPath("$.damagescovered").exists()
//		.jsonPath("$.premiumcharges").exists().jsonPath("$.coverpremiumcount").exists().jsonPath("$.coverpremiumvlaue").exists()
//		.jsonPath("$.insurancecalculation").exists().consumeWith(System.out::println);
//	}
//	
//	
//	@Test
//	void updateproduct() {
//		
//		InsuranceDto dto = new InsuranceDto();
//		dto.setAsNew(false);
//		dto.setTypeofstock("content");
//		dto.setStockvalue(353000);
//		dto.setDamagescovered(353000);
//		dto.setPremiumcharges(2500);
//		dto.setCoverpremiumcount(3);
//		
//		String uniqueid =  String.valueOf(generateid());
//		dto.setId(uniqueid);
//		dto.setRiskfactor(generateriskfactor(dto));
//		dto.setCoverpremiumvlaue(generatecoverpremium(dto));
//		dto.setInsurancecalculation(insuranceCalculation(dto));
//	
//	
//		
//		InsuranceDto inserteddto = insuranceservice.insertintorepo(dto).block();
//		
//		InsuranceDto updated =new InsuranceDto();
//		updated.setAsNew(false);
//		updated.setPremiumcharges(3000);
//		
//		
//		webtestclient.put().uri("/api/vi/stock/{id}",Collections.singletonMap("id","1079119")).contentType(MediaType.APPLICATION_JSON)
//		.body(Mono.just(updated),InsuranceDto.class).exchange().expectStatus().isAccepted().expectBody()
//		.jsonPath("$.typeofstock").isEqualTo(inserteddto.getTypeofstock()).jsonPath("$.riskfactor").isEqualTo(inserteddto.getRiskfactor()).jsonPath("$.damagescovered").isEqualTo(inserteddto.getDamagescovered())
//		.jsonPath("$.premiumcharges").isEqualTo(updated.getPremiumcharges()).jsonPath("$.coverpremiumcount").isEqualTo(inserteddto.getCoverpremiumcount()).jsonPath("$.coverpremiumvlaue").isEqualTo(inserteddto.getCoverpremiumvlaue())
//		.consumeWith(System.out::println);
//	}
//	
//	@Test
//	void deletebyid() {
//		
//		InsuranceDto dto = new InsuranceDto();
//		dto.setTypeofstock("content");
//		dto.setStockvalue(353000);
//		dto.setDamagescovered(353000);
//		dto.setPremiumcharges(2500);
//		dto.setCoverpremiumcount(3);
//		
//		InsuranceDto inserteddto = insuranceservice.insertintorepo(dto).block();
		
		
//		webtestclient.delete().uri("/api/vi/stock/{id}",Collections.singletonMap("id","8719208")).exchange()
//		.expectStatus().isOk().expectBody().consumeWith(System.out::println);
//		}
	
	@Test
	void getallproducts() {
//		InsuranceDto dto = new InsuranceDto();
//		dto.setTypeofstock("content");
//		dto.setStockvalue(353000);
//		dto.setDamagescovered(35300);
//		dto.setPremiumcharges(2500);
//		dto.setCoverpremiumcount(3);
//		
//		insuranceservice.insertintorepo(dto).block();
//		
//		InsuranceDto dto1 = new InsuranceDto();
//		dto1.setTypeofstock("Inventory");
//		dto1.setStockvalue(253000);
//		dto1.setDamagescovered(253000);
//		dto1.setPremiumcharges(3000);
//		dto1.setCoverpremiumcount(1);
//		
//		insuranceservice.insertintorepo(dto1).block();
		
		webtestclient.get().uri("/api/vi/stock").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isAccepted().expectBodyList(InsuranceDto.class).consumeWith(System.out::println);
		
	}
	

}