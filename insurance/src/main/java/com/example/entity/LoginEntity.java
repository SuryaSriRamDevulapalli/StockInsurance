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
@Table(name = "user")
public class LoginEntity  implements Persistable<String> {

    @Id
    private String id;

    private String firstname;
    
    private String lastname;
    
    private String username;
    
    private String password;
    
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
