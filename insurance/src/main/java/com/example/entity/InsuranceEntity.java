package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
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
@Table(name = "details")
public class InsuranceEntity implements Persistable<String> {

    @Id
    private String id;  //UUID.randomUUID().toString();

    private String typeofstock;

    private int stockvalue;
    
    private int damagescovered;
    
    private int premiumcharges;
    
    private int insurancecalculation;

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
