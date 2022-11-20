package com.precious.truecaller.service.userService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.precious.truecaller.data.dto.request.AccountRequest;
import com.precious.truecaller.data.dto.response.AccountResponse;
import com.precious.truecaller.data.models.user.Account;
import com.precious.truecaller.data.repositories.AccountRepository;
import com.precious.truecaller.web.exception.AccountAlreadyExistsException;
import com.precious.truecaller.web.exception.AccountNotFoundException;
import com.precious.truecaller.web.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final ObjectMapper mapper;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(ObjectMapper mapper, ModelMapper modelMapper, AccountRepository accountRepository) {
        this.mapper = mapper;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createContactAccount(AccountRequest request) {
        Account foundAccount =  accountRepository.findByEmail(request.getEmail());
        if (foundAccount != null) throw new AccountAlreadyExistsException("Account with this email "
                +request.getEmail() +" already exist");
        Account switched = modelMapper.map(request, Account.class);
        Account savedAccount = accountRepository.save(switched);
        return modelMapper.map(savedAccount, AccountResponse.class);
    }

    @Override
    public AccountResponse editAccountDetailsByEmail(String email, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Account account = accountRepository.findByEmail(email);
        if(account == null) throw new AccountNotFoundException("Account with this email"+ email+ " does not exist");
        Account foundAccount = account;
       try {foundAccount = jsonPatcher(patch, account);
           log.info("Account after patch {}", foundAccount);
       }catch (JsonPatchException | JsonProcessingException e){
           throw new BusinessLogicException("Account update failed");
       }
        return mapper.convertValue(foundAccount, AccountResponse.class);
    }

    private Account jsonPatcher(JsonPatch patch, Account account) throws JsonPatchException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = patch.apply(mapper.convertValue(account, JsonNode.class));
        return mapper.treeToValue(node, Account.class);
    }

    @Override
    public String deleteAccountDetailsByEmail(String email) {
        Account account = findAccountDetailsByEmail(email);
        accountRepository.deleteByEmail(account.getEmail());
        return "Account "+ account.getFirstName() + " has been successfully deleted";
    }

    @Override
    public String deleteAllAccount() {
        accountRepository.deleteAll();
        return "All account has been successfully deleted";
    }

    @Override
    public Account findAccountDetailsByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) throw new AccountNotFoundException("Account with this email"+ email+ " does not exist");
        return account;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Boolean deactivateAccountByAccountEmail(String email) {
        Boolean status = false;
        Account account = findAccountDetailsByEmail(email);
        if(account.getIsActive().equals(true)) account.setIsActive(status);
        return status;
    }
}
