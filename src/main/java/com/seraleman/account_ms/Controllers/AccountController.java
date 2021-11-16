package com.seraleman.account_ms.Controllers;

import com.seraleman.account_ms.exceptions.AccountNotFoundException;
import com.seraleman.account_ms.models.Account;
import com.seraleman.account_ms.repositories.AccountRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account/{username}")
    Account getAccount(@PathVariable String username) {
        return accountRepository.findById(username).orElseThrow(
                () -> new AccountNotFoundException("No se encontró una cuenta con el username: ".concat(username)));
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }
}
