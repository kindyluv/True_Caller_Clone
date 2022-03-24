package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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

    Contact contact, savedContact;
    MobileNumber mobileNumber, mobileNumber1;
    MobileNumber savedMobileNumber;


    @BeforeEach
    void setUp(){
        mobileNumber = MobileNumber.builder()
                .countryCode("+234")
                .mobileNumber("09023333333")
                .build();

        mobileNumber1 = MobileNumber.builder()
                .countryCode("+234")
                .mobileNumber("09023333789")
                .build();

        savedMobileNumber = mobileNumberRepository.save(mobileNumber1);

        contact = Contact.builder()
                .name("Lois Amara")
                .mobileNumber(savedMobileNumber)
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
    Contact contact = new Contact();
        Contact contact1 = contactRepository.findByName(savedContact.getName()).orElse(null);
        assertThat(contact1).isNotNull();
        assertThat(contact1.getName()).isEqualTo("Lois Amara");
    }

    @Test
    void deleteByName() {
        Optional<Contact> contact1 = contactRepository.findByName(savedContact.getName());
        assertThat(contact1).isNotNull();
        assertThat(contact1.get().getName()).isEqualTo("Lois Amara");

        contactRepository.deleteByName(contact1.get().getName());
        assertThat(contactRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void findByMobileNumber() {
        Optional<Contact> contact1 = contactRepository.findByMobileNumber(contact.getMobileNumber());
        assertThat(contact1).isNotNull();
        assertThat(contact1.get().getName()).isEqualTo("Lois Amara");
    }
}