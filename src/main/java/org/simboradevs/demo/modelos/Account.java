package org.simboradevs.demo.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    private Integer number;

    public Account(){
        this(0);
    }


    public Account(Integer number){
        this.number = number;
    }

    public Integer getNumber(){
        return number;
    }
}
