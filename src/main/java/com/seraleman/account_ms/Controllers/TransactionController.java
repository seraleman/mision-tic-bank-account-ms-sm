package com.seraleman.account_ms.Controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.login.AccountNotFoundException;

import com.seraleman.account_ms.exceptions.InsufficientBalanceException;
import com.seraleman.account_ms.models.Account;
import com.seraleman.account_ms.models.Transaction;
import com.seraleman.account_ms.repositories.AccountRepository;
import com.seraleman.account_ms.repositories.TransactionRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction) throws AccountNotFoundException {

        Account accountOrigin = accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);
        Account accountDestinity = accountRepository.findById(transaction.getUsernameDestinity()).orElse(null);

        if (accountOrigin == null) {
            throw new AccountNotFoundException(
                    "No se encontró una cuenta con el username: ".concat(transaction.getUsernameOrigin()));
        }

        if (accountDestinity == null) {
            throw new AccountNotFoundException(
                    "No se encontró una cuenta con el username: ".concat(transaction.getUsernameDestinity()));
        }

        if (accountOrigin.getBalance() < transaction.getValue()) {
            throw new InsufficientBalanceException("Saldo Insuficiente");
        }

        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountOrigin.setLastChange(new Date());
        accountRepository.save(accountOrigin);

        accountDestinity.setBalance(accountDestinity.getBalance() + transaction.getValue());
        accountDestinity.setLastChange(new Date());
        accountRepository.save(accountDestinity);

        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }

    @GetMapping("/transactions/{username}")
    List<Transaction> userTransaction(@PathVariable String username) throws AccountNotFoundException {

        Account userAccount = accountRepository.findById(username).orElse(null);

        if (userAccount == null) {
            throw new AccountNotFoundException("No se encontró una cuenta con el username: ".concat(username));
        }

        List<Transaction> transactionsOrigin = transactionRepository.findByUsernameOrigin(username);

        List<Transaction> transactionsDestinity = transactionRepository.findByUsernameDestinity(username);

        List<Transaction> transactions = Stream.concat(transactionsOrigin.stream(), transactionsDestinity.stream())
                .collect(Collectors.toList());

        return transactions;
    }
}
