package com.precious.truecaller.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.precious.truecaller.data.dto.request.AccountRequest;
import com.precious.truecaller.data.dto.response.AccountResponse;
import com.precious.truecaller.data.models.user.Account;
import com.precious.truecaller.service.userService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createContactAccount(@Valid @RequestBody AccountRequest request){
        return accountService.createContactAccount(request);
    }

    @PatchMapping(path="/{email}")
    public AccountResponse editAccountDetailsByEmail(@PathVariable String email, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        return accountService.editAccountDetailsByEmail(email, patch);
    }

    @DeleteMapping(path="/{email}")
    public String deleteAccountDetailsByEmail(@PathVariable String email){
        return accountService.deleteAccountDetailsByEmail(email);
    }

    @DeleteMapping
    public String deleteAllAccount(){
        return accountService.deleteAllAccount();
    }

    @GetMapping(path="/{email}")
    public Account findAccountDetailsByEmail(@PathVariable @RequestParam(required = false, defaultValue = "1") String email){
        return accountService.findAccountDetailsByEmail(email);
    }

    @GetMapping
    public List<Account> findAllAccounts(){
        return accountService.findAllAccounts();
    }

    @PutMapping(path="/{email}")
    public Boolean deactivateAccountByAccountEmail(@PathVariable String email){
        return accountService.deactivateAccountByAccountEmail(email);
    }
}
