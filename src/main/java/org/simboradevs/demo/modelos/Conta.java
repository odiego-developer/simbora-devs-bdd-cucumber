package org.simboradevs.demo.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Conta {

    @Id
    private Integer numero;

    public Conta(){
        this(0);
    }


    public Conta(Integer numero){
        this.numero = numero;
    }

    public Integer getNumero(){
        return numero;
    }
}
