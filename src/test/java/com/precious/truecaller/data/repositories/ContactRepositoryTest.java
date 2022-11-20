package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Slf4j
class ContactRepositoryTest {

    private final ContactRepository contactRepository;
    private final MobileNumberRepository mobileNumberRepository;

    @Autowired
    ContactRepositoryTest(ContactRepository contactRepository, MobileNumberRepository mobileNumberRepository) {
        this.contactRepository = contactRepository;
        this.mobileNumberRepository = mobileNumberRepository;
    }

    Contact contact, savedContact, contact1;
    MobileNumber string, string1;
    private MobileNumber savedString, mobileNumber1, mobileNumber2, mobileNumber3, mobileNumber4;



    @BeforeEach
    void setUp(){
        mobileNumber1 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("234")
                .number("08097654325")
                .isBlocked(false)
                .build());

        mobileNumber2 = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("234")
                .number("09097654325")
                .isBlocked(true)
                .build());

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

        string = MobileNumber.builder()
                .countryCode("+234")
                .number("09023333333")
                .build();

        string1 = MobileNumber.builder()
                .countryCode("+234")
                .number("09023333789")
                .build();

        savedString = mobileNumberRepository.save(string1);

        contact1 = contactRepository.save(Contact.builder()
                .userName("Lois Amarachi")
                .companyName("Semicolon")
                .isBlocked(false)
                .mobileNumber(mobileNumber1)
                .email("amaralois@gmail.com")
                .build());

        contact = Contact.builder()
                .userName("Lois Amara")
                .mobileNumber(savedString)
                .companyName("Semicolon")
                .email("lois@gmail.com")
                .isBlocked(false)
                .build();
        savedContact = contactRepository.save(contact);
    }

    @AfterEach()
    void tearDown() {
        contactRepository.delete(contact);
    }

    @Test
    void findByName() {
        Contact contact1 = contactRepository.findByUserName(savedContact.getUserName()).orElse(null);
        assertThat(contact1).isNotNull();
        assertThat(contact1.getUserName()).isEqualTo(savedContact.getUserName());
    }

    @Test
    void deleteByName() {
        Contact contact1 = contactRepository.findByUserName(savedContact.getUserName()).orElse(null);
        assertThat(contact1).isNotNull();
        assertThat(contact1.getUserName()).isEqualTo(savedContact.getUserName());

        contactRepository.removeByUserName(contact1.getUserName());
        assertThat(contactRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void findByMobileNumber() {
        Contact contact2 = contactRepository.findByMobileNumberNumber(contact.getMobileNumber().getNumber());
        assertThat(contact2).isNotNull();
//        assertThat(contact1.get().getUserName()).isEqualTo("Lois Amara");
    }
}