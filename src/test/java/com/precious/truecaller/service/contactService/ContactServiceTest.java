package com.precious.truecaller.service.contactService;

import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
//@Sql(scripts = {"/db/insert.sql"})
class ContactServiceTest {

    private final MobileNumberRepository mobileNumberRepository;
    private final ContactService contactService;


    @Autowired
    ContactServiceTest(MobileNumberRepository mobileNumberRepository,
                       ContactService contactService) {
        this.mobileNumberRepository = mobileNumberRepository;
        this.contactService = contactService;
    }

    ContactResponse contactResponse, contactResponse2;
    ContactRequest contactRequest, contactRequest2;
    Contact contact, savedContact;
    MobileNumber mobileNumber, mobileNumber1;
    MobileNumber savedMobileNumber, savedMobileNumber2;

    @BeforeEach
    void setUp() {
        mobileNumber = MobileNumber.builder()
                .countryCode("+234")
                .mobileNumber("09023333333")
                .build();
        savedMobileNumber2 = mobileNumberRepository.save(mobileNumber);

        mobileNumber1 = MobileNumber.builder()
                .countryCode("+234")
                .mobileNumber("09023333789")
                .build();
        savedMobileNumber = mobileNumberRepository.save(mobileNumber1);

        contactRequest = ContactRequest.builder()
                .name("Lois Amara")
                .mobileNumber(savedMobileNumber)
                .companyName("Semicolon_Africa")
                .email("lois@gmail.com")
                .build();

        contactRequest2 = ContactRequest.builder()
                .name("Amara Jesus")
                .mobileNumber(savedMobileNumber2)
                .companyName("Semicolon")
                .email("amara@gmail.com")
                .build();
        contactResponse2 = contactService.addContact(contactRequest2);
    }

    @AfterEach
    void afterEach(){
        contactService.deleteAllContact();
    }

    @Test
    void addContact() {
       ContactResponse response = contactService.addContact(contactRequest);
       assertThat(response).isNotNull();
       assertThat(response.getName()).isEqualTo(contactRequest.getName());
    }

    @Test
    void blockContactByMobileNumber() {
        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(contactRequest.getName());

        Boolean isBlocked = contactService.blockContactByMobileNumber(response.getMobileNumber());
        assertThat(isBlocked).isTrue();
    }

    @Test
    void unBlockContactByMobileNumber() {
        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(contactRequest.getName());

        Boolean isBlocked = contactService.blockContactByMobileNumber(response.getMobileNumber());
        assertThat(isBlocked).isTrue();

        Boolean isUnBlocked = contactService.unBlockContactByMobileNumber(response.getMobileNumber());
        assertThat(isUnBlocked).isFalse();
    }

    @Test
    void editContact() {
//        contact = contactService.editContact(contactResponse2.getName(), );
    }

    @Test
    void findContactByMobileNumber() {
        ContactResponse response = contactService.findContactByMobileNumber(contactResponse2.getMobileNumber());
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(contactRequest2.getName());
    }

//    @Test
//    void findContactById() {
//        Optional<Contact> response = contactService.findContactById(contactResponse2.get);
//    }

    @Test
    void findContactByContactName() {
//        ContactResponse res = contactService.findByContactName();
//        ContactResponse response = contactService.findByContactName(contactResponse2.getName());
//        assertThat(response).isNotNull();
//        assertThat(response.getName()).isEqualTo(contactResponse2.getName());
    }

    @Test
    void findAllContact() {
        List<ContactResponse> response = contactService.findAllContact();
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void deleteContactByContactName() {
        ContactResponse response = contactService.findByContactName(contactResponse2.getName());
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(contactRequest2.getName());

        contactService.deleteContactByContactName(response.getName());
        List<ContactResponse> responses = contactService.findAllContact();
        assertThat(responses.size()).isEqualTo(0);
    }

    @Test
    void deleteContactById() {
    }
}