package org.simboradevs.demo.services;

import org.simboradevs.demo.modelos.Account;
import org.simboradevs.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {


    private AccountRepository accountRepository;

    @Autowired
    public AccountManager(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }
}
