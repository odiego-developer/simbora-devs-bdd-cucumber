package org.simboradevs.demo.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private int numeroConta;

    private OffsetDateTime dataHora;

    private double valor;

    private TipoTransacao tipo;

    public Transacao(){
        this(0,0,null);
    }

    public Transacao(int numeroConta, double valor, TipoTransacao tipo){

        this.numeroConta = numeroConta;
        this.valor = valor;
        this.tipo = tipo;
        this.dataHora = OffsetDateTime.now();
    }

    public Integer getId(){
        return id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public double getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }
}
