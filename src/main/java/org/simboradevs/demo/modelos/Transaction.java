package org.simboradevs.demo.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private int accountNumber;

    private OffsetDateTime dateAndTime;

    private double amount;

    private TransactionType type;

    public Transaction(){
        this(0,0,null);
    }

    public Transaction(int accountNumber, double amount, TransactionType tipo){

        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = tipo;
        this.dateAndTime = OffsetDateTime.now();
    }

    public Integer getId(){
        return id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public OffsetDateTime getDateAndTime() {
        return dateAndTime;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
