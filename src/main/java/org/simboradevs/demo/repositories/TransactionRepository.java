package org.simboradevs.demo.repositories;

import org.simboradevs.demo.modelos.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer > {

    List<Transaction> findByAccountNumber(int accountNumber);
}
