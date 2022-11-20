package com.precious.truecaller.service.userService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.precious.truecaller.data.dto.request.AccountRequest;
import com.precious.truecaller.data.dto.response.AccountResponse;
import com.precious.truecaller.data.models.user.Account;

import java.util.List;

public interface AccountService {

    AccountResponse createContactAccount(AccountRequest request);
    AccountResponse editAccountDetailsByEmail(String email, JsonPatch patch) throws JsonPatchException, JsonProcessingException;
    String deleteAccountDetailsByEmail(String email);
    String deleteAllAccount();
    Account findAccountDetailsByEmail(String email);
    List<Account> findAllAccounts();
    Boolean deactivateAccountByAccountEmail(String email);

}
