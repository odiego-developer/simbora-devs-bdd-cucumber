package org.simboradevs.demo.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.simboradevs.demo.modelos.Account;
import org.simboradevs.demo.modelos.TransactionType;
import org.simboradevs.demo.modelos.Transaction;
import org.simboradevs.demo.repositories.TransactionRepository;
import org.simboradevs.demo.services.AccountManager;
import org.simboradevs.demo.services.CalculateBalance;
import org.simboradevs.demo.services.RealizeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@CucumberContextConfiguration
public class TransactionsSteps {

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private RealizeTransaction realizeTransaction;

    @Autowired
    private CalculateBalance calculateBalance;

    @Autowired
    private TransactionRepository transactionRepository;

    private List<Integer> createdAccounts;
    @Before
    public void runBefore(){
        createdAccounts = new ArrayList<>();
    }
    @After
    public void runAfter(){
        transactionRepository.deleteAll();
    }


    @Given("Eu crio uma conta sem saldo de numeração {int}")
    public void euCrioUmaContaSemSaldoDeNumeração(int accountNumber) {
        createAccount(accountNumber);
    }

    private void createAccount(int accountNumber) {
        accountManager.createAccount(new Account(accountNumber));
    }

    @When("Eu realizo um depósito de {double} euros na conta {int}")
    public void euRealizoUmDepósitoDeEurosNaConta(double amount, int accountNumber) {

        createCreditTransaction(amount, accountNumber);

    }


    @Then("o saldo da conta {int} deverá ser de {double} euros")
    public void oSaldoDaContaDeveráSerDeEuros(int accountNumber, double expectedBalance) {

        double actualBalance = calculateBalance.calculateBalanceByAccountNumber(accountNumber);

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Given("Eu crio as seguintes contas abaixo com saldo")
    public void euCrioAsSeguintesContasAbaixoComSaldo(DataTable table) {
        Map<Integer, Double> map = table.asMap(Integer.class, Double.class);
        map.forEach((accountNumber, amount)->{
            createdAccounts.add(accountNumber);
            createAccount(accountNumber);
            createCreditTransaction(amount,accountNumber);

        });
    }

    @When("As contas criadas anteriormente transferem {double} euros para a conta {int}")
    public void asContasCriadasAnteriormenteTransferemEurosParaAConta(double debitAmount, int targetAccountNumber) {
        createdAccounts.forEach(account ->{
            createDebitTransaction(debitAmount, account);
            createCreditTransaction(debitAmount,targetAccountNumber);
        });

    }

    private void createDebitTransaction(double debitAmount, Integer account) {
        realizeTransaction.realize(new Transaction(account, debitAmount, TransactionType.DEBIT));
    }
    private void createCreditTransaction(double amount, int accountNumber) {
        realizeTransaction.realize(new Transaction(accountNumber, amount, TransactionType.CREDIT));
    }

    @Then("O saldo das contas abaixo deverão estar")
    public void oSaldoDasContasAbaixoDeverãoEstar(DataTable table) {
        Map<Integer, Double> map = table.asMap(Integer.class, Double.class);
        map.forEach((accountNumber, expectedAmount)->{
            double actualBalance = calculateBalance.calculateBalanceByAccountNumber(accountNumber);
            Assertions.assertEquals(expectedAmount,actualBalance);
        });
    }
}
