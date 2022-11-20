package com.precious.truecaller.service.userService;

import com.precious.truecaller.data.dto.request.AccountRequest;
import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.AccountResponse;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.models.user.Account;
import com.precious.truecaller.data.models.user.UserCategory;
import com.precious.truecaller.data.repositories.AccountRepository;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import com.precious.truecaller.service.contactService.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class AccountServiceTest {

    private final AccountService accountService;
    private final ContactService contactService;
    private final MobileNumberRepository mobileNumberRepository;
    private final AccountRepository accountRepository;

    @Autowired
    AccountServiceTest(AccountService accountService, ContactService contactService,
                       MobileNumberRepository mobileNumberRepository, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.contactService = contactService;
        this.mobileNumberRepository = mobileNumberRepository;
        this.accountRepository = accountRepository;
    }

    private ContactRequest request1, request2, request3;
    private ContactResponse response1, response2, response3;
    Contact contact, savedContact, contact2;
    MobileNumber mobileNumber, mobileNumber1, string, string1, string2, savedString, savedString2;
    Account account, account2, account3, account4;
    AccountRequest accountRequest;
    List<Contact> contacts;
    Set<MobileNumber> blockedNumbersSet;

    @BeforeEach
    void setUp() {
        accountService.deleteAllAccount();
        request1 = ContactRequest.builder()
                .userName("PreciousLois")
                .email("presh@gmail.com")
                .mobileNumber("09054378908")
                .countryCode("+234")
                .companyName("Semicolon.Africa")
                .build();
        mobileNumber1 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09032364328")
                .isBlocked(false)
                .build());
        mobileNumber = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09032354678")
                .isBlocked(false)
                .build());


        string = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09023333333")
                        .isBlocked(true)
                .build());

        string2 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09027433789")
                .isBlocked(true)
                .build());

        string1 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09023333789")
                .isBlocked(true)
                .build());

        List<MobileNumber> blockedNumbersList = List.of(string2, string, string1, mobileNumber1, mobileNumber);
        blockedNumbersSet = new HashSet<>();
        for(MobileNumber mob: blockedNumbersList){
            if(mob.getIsBlocked().equals(true)){
                blockedNumbersSet.add(mob);
            }
        }
        contact = Contact.builder()
                .userName("Lois Amara")
                .mobileNumber(savedString)
                .companyName("Semicolon_Africa")
                .email("lois@gmail.com")
                .build();

        contact2 = Contact.builder()
                .userName("Amara Jesus")
                .mobileNumber(savedString2)
                .companyName("Semicolon")
                .email("amara@gmail.com")
                .build();
//        contactResponse2 = contactService.addContact(contactRequest2);
        contacts = List.of(contact, contact2);

//        account = Account.builder()
//                .userName("Lois Amara")
//                .password("password")
//                .email("amicoder@gmail.com")
//                .contact(contacts)
//                .isActive(true)
//                .blockedNumbers(blockedNumbersSet)
//                .userCategory(UserCategory.FREE)
//                .build();

        accountRequest = AccountRequest.builder()
                .userName("Lois Amara")
                .password("password")
                .email("amic@gmail.com")
                .userCategory(UserCategory.FREE)
                .build();
    }

    @AfterEach
    void tearDown() {
        accountService.deleteAllAccount();
        contactService.deleteAllContact();
        mobileNumberRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void createContactAccount() {
        AccountResponse response = accountService.createContactAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(accountRequest.getEmail());
    }

    @Test
    void editAccountDetailsByEmail() {
    }

    @Test
    void deleteAccountDetailsByEmail() {
        AccountResponse response = accountService.createContactAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(accountRequest.getEmail());
        accountService.deleteAccountDetailsByEmail(response.getEmail());
        assertThat(accountService.findAllAccounts().size()).isEqualTo(0);
    }

    @Test
    void deleteAllAccount() {
        AccountRequest accountRequest2 = AccountRequest.builder()
                .userName("Lois Amara presh")
                .password("passwordpresh")
                .email("amicody@gmail.com")
                .userCategory(UserCategory.FREE)
                .build();
        AccountResponse response = accountService.createContactAccount(accountRequest2);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(accountRequest2.getEmail());
        List<Account> accz = accountService.findAllAccounts();
        assertThat(accz.size()).isEqualTo(1);
        accountService.deleteAllAccount();
        List<Account> acc = accountService.findAllAccounts();
        assertThat(acc.size()).isEqualTo(0);
    }

    @Test
    void findAccountDetailsByEmail() {
        AccountResponse response = accountService.createContactAccount(accountRequest);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(accountRequest.getEmail());
        Account foundAccount = accountService.findAccountDetailsByEmail(response.getEmail());
        assertThat(foundAccount.getFirstName()).isEqualTo(response.getUserName());
    }

    @Test
    void findAllAccounts() {
        AccountResponse res = accountService.createContactAccount(accountRequest);
        assertThat(res).isNotNull();

        AccountRequest accountRequest2 = AccountRequest.builder()
                .userName("Lois Amara presh")
                .password("passwordpresh")
                .email("amicody@gmail.com")
                .userCategory(UserCategory.FREE)
                .build();

        AccountResponse response = accountService.createContactAccount(accountRequest2);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(accountRequest2.getEmail());

        List<Account> accz = accountService.findAllAccounts();
        assertThat(accz.size()).isEqualTo(2);

    }

//    @Test
//    void deactivateAccountByAccountEmail() {
//        Account account2 = accountService.findAccountDetailsByEmail(savedAccount.getEmail());
//        assertThat(account2).isNotNull();
//    }
}