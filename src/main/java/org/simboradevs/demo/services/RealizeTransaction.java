package org.simboradevs.demo.services;

import org.simboradevs.demo.modelos.Transaction;
import org.simboradevs.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealizeTransaction {
    private TransactionRepository transactionRepository;

    @Autowired
    public RealizeTransaction(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void realize(Transaction transaction) {
        if(transaction.getAmount() > 0){
            transactionRepository.save(transaction);
        }
    }
}
