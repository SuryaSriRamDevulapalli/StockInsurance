package com.example.dto;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InsuranceDto implements Persistable<String> {

    private String id; 

    private String typeofstock;

    private int stockvalue;
    
    private double riskfactor;
    
    private int damagescovered;
    
    private int premiumcharges;
    
    private int coverpremiumcount;
    
    private int coverpremiumvlaue;
    
    private double insurancecalculation;

    @Transient
    private boolean isNew = true;

    @Override
    @Transient
    public boolean isNew() {
        return isNew;
    }
    
    public void setAsNew(boolean isNew) {
        this.isNew = isNew;
    }
 
}
