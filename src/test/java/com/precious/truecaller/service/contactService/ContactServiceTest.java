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
    MobileNumber string, string1, string2;
    MobileNumber savedString, savedString2;

    @BeforeEach
    void setUp() {
        string = MobileNumber.builder()
                .countryCode("+234")
                .number("09023333333")
                .build();
        savedString2 = mobileNumberRepository.save(string);

        string2 = MobileNumber.builder()
                .countryCode("+234")
                .number("09023333789")
                .build();

        string1 = MobileNumber.builder()
                .countryCode("+234")
                .number("09023333789")
                .build();
        savedString = mobileNumberRepository.save(string1);

        contactRequest = ContactRequest.builder()
                .userName("Lois Amara")
                .mobileNumber(string2.getNumber())
                .companyName("Semicolon_Africa")
                .email("lois@gmail.com")
                .build();

        contactRequest2 = ContactRequest.builder()
                .userName("Amara Jesus")
                .mobileNumber(savedString2.getNumber())
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
       assertThat(response.getUserName()).isEqualTo(contactRequest.getUserName());
    }

    @Test
    void blockContactByMobileNumber() {
        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo(contactRequest.getUserName());

        Boolean isBlocked = contactService.blockContactByContactName(response.getUserName());
        response.setIsBlocked(true);
        assertThat(isBlocked).isTrue();
    }

    @Test
    void unBlockContactByMobileNumber() {
        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo(contactRequest.getUserName());

        Boolean isBlocked = contactService.blockContactByContactName(response.getUserName());
        assertThat(isBlocked).isTrue();

        Boolean isUnBlocked = contactService.unBlockContactByContactName(response.getUserName());
        assertThat(isUnBlocked).isFalse();
    }

    @Test
    void editContact() {
//        contact = contactService.editContact(contactResponse2.getName(), );
    }

    @Test
    void findContactByMobileNumber() {
        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo(contactRequest.getUserName());

        ContactResponse response2 = contactService.findContactByMobileNumber(string2.getNumber());
        assertThat(response2).isNotNull();
//        assertThat(response2.getUserName()).isEqualTo(contactRequest2.getUserName());
    }

    @Test
    void findContactByContactName() {
        contactRequest = ContactRequest.builder()
                .userName("Lois Amara")
                .mobileNumber(savedString.getNumber())
                .companyName("Semicolon_Africa")
                .email("lois@gmail.com")
                .build();

        ContactResponse response = contactService.addContact(contactRequest);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo(contactRequest.getUserName());

        ContactResponse res = contactService.findByContactName(response.getUserName());
        assertThat(res).isNotNull();
        assertThat(res.getUserName()).isEqualTo(contactRequest.getUserName());
    }

    @Test
    void findAllContact() {
        List<ContactResponse> response = contactService.findAllContact();
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void deleteContactByContactName() {
        ContactResponse response = contactService.findByContactName(contactResponse2.getUserName());
        assertThat(response).isNotNull();
//        assertThat(response.getUserName()).isEqualTo(contactRequest2.getUserName());

        contactService.deleteContactByContactName(response.getUserName());
        List<ContactResponse> responses = contactService.findAllContact();
        assertThat(responses.size()).isEqualTo(0);
    }

    @Test
    void deleteContactById() {
    }
}