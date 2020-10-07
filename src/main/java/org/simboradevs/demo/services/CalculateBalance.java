package org.simboradevs.demo.services;

import org.simboradevs.demo.modelos.TransactionType;
import org.simboradevs.demo.modelos.Transaction;
import org.simboradevs.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateBalance {

    private TransactionRepository transactionRepository;

    @Autowired
    public CalculateBalance(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public double calculateBalanceByAccountNumber(int accountNumber) {

        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);

        double amountCredit = transactions.stream()
                .filter(transaction -> TransactionType.CREDIT.equals(transaction.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double amountDebit = transactions.stream()
                .filter(transaction -> TransactionType.DEBIT.equals(transaction.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return amountCredit - amountDebit;
    }
}
