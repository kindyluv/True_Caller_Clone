package com.precious.truecaller.service.userService;

import com.precious.truecaller.data.dto.request.AccountRequest;
import com.precious.truecaller.data.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse createContactAccount(AccountRequest request);

}
