package com.precious.truecaller.data.db;

import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.ContactRepository;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    private final ContactRepository contactRepository;
    private final RestTemplate restTemplate;
    private final MobileNumberRepository mobileNumberRepository;

    @Autowired
    public DbInit(ContactRepository contactRepository, RestTemplate restTemplate, MobileNumberRepository mobileNumberRepository) {
        this.contactRepository = contactRepository;
        this.restTemplate = restTemplate;
        this.mobileNumberRepository = mobileNumberRepository;
    }

    @PostConstruct
    public void contactConstruct(){
//        String jsonUrl = "https://jsonplaceholder.typicode.com/users";
//        ResponseEntity<String> response = restTemplate.getForEntity(jsonUrl. + "/1", String.class);

        MobileNumber mobileNumber1, mobileNumber2, mobileNumber4, mobileNumber3;
        Contact contact1, contact2, contact3, contact4;
        mobileNumber1 = MobileNumber.builder()
                .countryCode("+234")
                .number("08037271515")
                .build();
        MobileNumber mob1 = mobileNumberRepository.save(mobileNumber1);

        contact1 = Contact.builder()
                .userName("Olatoye David")
                .companyName("semi-wise")
                .email("daramolatoye@gmail.com")
                .mobileNumber(mob1)
                .isBlocked(false)
                .build();
        contactRepository.save(contact1);

        mobileNumber2 = MobileNumber.builder()
                .countryCode("+234")
                .number("08097654322")
                .build();
        MobileNumber mob2 = mobileNumberRepository.save(mobileNumber2);

        contact2 = Contact.builder()
                .userName("David Opeyemi")
                .companyName("semi-Engine")
                .email("opedavid@gmail.com")
                .mobileNumber(mob2)
                .isBlocked(false)
                .build();
        contactRepository.save(contact2);

        MobileNumber mob3 = mobileNumber3 = MobileNumber.builder()
                .countryCode("+234")
                .number("07067871515")
                .build();
        mobileNumberRepository.save(mob3);

        contact3 = Contact.builder()
                .userName("Olaitan David")
                .companyName("semi-Comic")
                .email("olaitandavid@gmail.com")
                .mobileNumber(mobileNumber3)
                .isBlocked(false)
                .build();
        contactRepository.save(contact3);

        mobileNumber4 = MobileNumber.builder()
                .countryCode("+234")
                .number("07037271515")
                .build();
        MobileNumber mob4 = mobileNumberRepository.save(mobileNumber4);

        contact4 = Contact.builder()
                .userName("Joshua David")
                .companyName("semi-prep")
                .email("joshuadavid@gmail.com")
                .mobileNumber(mob4)
                .isBlocked(false)
                .build();
        contactRepository.save(contact4);

    }

}
