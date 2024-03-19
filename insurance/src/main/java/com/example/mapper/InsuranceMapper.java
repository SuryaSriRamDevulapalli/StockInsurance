package com.example.mapper;

import com.example.dto.InsuranceDto;
import com.example.entity.InsuranceEntity;

public class InsuranceMapper {

	public static InsuranceDto maptoinsurancedto(InsuranceEntity entity) {
		
		return new InsuranceDto(
				entity.getId(),entity.getTypeofstock(),entity.getStockvalue(),entity.getRiskfactor(),entity.getDamagescovered(),entity.getPremiumcharges(),entity.getCoverpremiumcount(),entity.getCoverpremiumvlaue(),entity.getInsurancecalculation(), true);
	}
	public static InsuranceEntity maptoinsuranceentity(InsuranceDto dto) {
		
		return new InsuranceEntity(
				dto.getId(),dto.getTypeofstock(),dto.getStockvalue(),dto.getRiskfactor(),dto.getDamagescovered(),dto.getPremiumcharges(),dto.getCoverpremiumcount(),dto.getCoverpremiumvlaue(),dto.getInsurancecalculation(), true);
	}
}
