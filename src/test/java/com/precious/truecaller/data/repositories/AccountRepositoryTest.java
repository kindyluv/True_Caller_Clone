package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.models.user.Account;
import com.precious.truecaller.data.models.user.UserCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
class AccountRepositoryTest {

    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final MobileNumberRepository mobileNumberRepository;

    @Autowired
    AccountRepositoryTest(AccountRepository accountRepository, ContactRepository contactRepository, MobileNumberRepository mobileNumberRepository) {
        this.accountRepository = accountRepository;
        this.contactRepository = contactRepository;
        this.mobileNumberRepository = mobileNumberRepository;
    }

    private Account account;
    private Contact contact1, contact3;
    private MobileNumber mobileNumber3, mobileNumber4;
    private Set<MobileNumber> blockedNumbers;

    @BeforeEach
    void setUp() {
        mobileNumber3 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("234")
                .number("08097654855")
                .isBlocked(true)
                .build());

        mobileNumber4 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("234")
                .number("09095854325")
                .isBlocked(false)
                .build());

//        List<MobileNumber> listOfMobileNumbers = Arrays.asList(mobileNumber3, mobileNumber4);
//        blockedNumbers = new HashSet<>();
//
//        for (MobileNumber mobileNumber : listOfMobileNumbers) {
//            if (mobileNumber.getIsBlocked().equals(true)) {
//                blockedNumbers.add(mobileNumber);
//            }
//        }

         contact1 = contactRepository.save(Contact.builder()
                .userName("Lois Amarachi")
                .companyName("Semicolon")
                .isBlocked(false)
                .mobileNumber(mobileNumber3)
                .email("toe@gmail.com")
                .build());

        contact3 = contactRepository.save(Contact.builder()
                .userName("Kindness")
                .companyName("Semicolon.Africa.Dev")
                .isBlocked(false)
                .mobileNumber(mobileNumber4)
                .email("ola@gmail.com")
                .build());

        List<Contact> contacts = List.of(contact1, contact3);

        account = accountRepository.save(Account.builder()
                .userName("Delight Chukwuma")
                .password("password")
                .userCategory(UserCategory.FREE)
                .contact(contacts)
                .email("goodnews@gmail.com")
                .isActive(true)
                .blockedNumbers(blockedNumbers)
                .build());
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        contactRepository.deleteAll();
    }



    @Test
    void deleteByEmail() {
        assertThat(account).isNotNull();
        accountRepository.deleteByEmail(account.getEmail());
        assertThat(accountRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void findByEmail() {
        log.info("Account searched for is ---->{}", account);
        Account acc = accountRepository.findByEmail(account.getEmail());
        log.info("********* Account ********* ->\n{}", acc.toString());
        assertThat(acc).isNotNull();
    }
}