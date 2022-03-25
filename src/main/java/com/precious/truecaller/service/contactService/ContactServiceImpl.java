package com.precious.truecaller.service.contactService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.ContactRepository;
import com.precious.truecaller.web.exception.UpdateExceptionsException;
import com.precious.truecaller.web.exception.contactException.ContactAlreadyExistsException;
import com.precious.truecaller.web.exception.contactException.ContactNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ContactResponse addContact(ContactRequest contactRequest) {
        if (contactRequest == null) throw new IllegalArgumentException("Contact request can not be empty");

        Contact foundContact = contactRepository.findByMobileNumber(contactRequest.getMobileNumber()).orElse(null);

        if (foundContact != null)
            throw new ContactAlreadyExistsException("Contact with same mobile number already exist");

        Contact contact = modelMapper.map(contactRequest, Contact.class);

        Contact savedContact = contactRepository.save(contact);

        return modelMapper.map(savedContact, ContactResponse.class);
    }

    @Override
    public ContactResponse findContactByMobileNumber(MobileNumber string) {
        if (string == null) throw new IllegalArgumentException("Mobile number can not be empty");
        Contact contact = contactRepository.findByMobileNumber(string).orElseThrow(
                () -> new ContactNotFoundException("Mobile number " + string + " can doesnt exist with a name")
        );
        return objectMapper.convertValue(contact, ContactResponse.class);
    }

    @Override
    public ContactResponse findContactById(Integer id) {
        Contact foundContact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException("Contact with id " + id + " is not found"));
        return modelMapper.map(foundContact, ContactResponse.class);
    }

    @Override
    public ContactResponse findByContactName(String contactName) {
        Optional<Contact> contact = contactRepository.findByName(contactName);
        if (contact.isEmpty()) throw new ContactNotFoundException("Contact with contact name " + contactName + " is not found");
        return objectMapper.convertValue(contact, ContactResponse.class);
//        return ContactResponse.builder()
//                .name(contact.get().getName())
//                .mobileNumber(contact.get().getMobileNumber())
//                .build();
    }

    @Override
    public List<ContactResponse> findAllContact() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactResponse> responses = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactResponse response = ContactResponse.builder()
                    .name(contact.getName())
                    .mobileNumber(contact.getMobileNumber())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public Boolean blockContactByMobileNumber(MobileNumber string) {
        if (string == null) throw new IllegalArgumentException("Mobile number can not be empty");
        Contact contact = contactRepository.findByMobileNumber(string).orElseThrow(
                () -> new ContactNotFoundException("Mobile number " + string + " can doesnt exist with a name")
        );
        if (contact.getIsBlocked().equals(false)) contact.setIsBlocked(true);
        return contact.getIsBlocked();
    }

    @Override
    public ContactResponse editContact(String contactName, JsonPatch jsonPatch) {
        if (contactName == null) throw new IllegalArgumentException("Contact name can not be null");
        Optional<Contact> foundContact = Optional.ofNullable(contactRepository.findByName(contactName).orElseThrow(
                () -> new ContactNotFoundException("Contact With name " + contactName + " is null")
        ));
        Contact contact = foundContact.get();
        try {
            contact = patchContact(jsonPatch, foundContact.get());
            log.info("Contact after patch {}", contact);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new UpdateExceptionsException("Update Failed");
        }
        Contact contact2 = contactRepository.save(contact);
        return objectMapper.convertValue(contact2, ContactResponse.class);
    }

    private Contact patchContact(JsonPatch patch, Contact contact) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(contact, JsonNode.class));
        return objectMapper.treeToValue(patched, Contact.class);
    }

    @Override
    public Boolean unBlockContactByMobileNumber(MobileNumber string) {
        if (string == null) throw new IllegalArgumentException("Mobile number can not be empty");
        Contact contact = contactRepository.findByMobileNumber(string).orElseThrow(
                () -> new ContactNotFoundException("Mobile number " + string + " can doesnt exist with a name")
        );
        ;
        if (contact.getIsBlocked().equals(true)) contact.setIsBlocked(false);
        return contact.getIsBlocked();
    }

    @Override
    public String deleteContactByContactName(String contactName) {
        ContactResponse contact = findByContactName(contactName);
        contactRepository.deleteByName(contact.getName());
        return "deleted";
    }

    @Override
    public String deleteContactById(Integer id) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                ()-> new ContactNotFoundException("Contact with id" + id + " not found")
        );
        contactRepository.deleteById(id);
        return "Contact deleted";
    }

    @Override
    public String deleteAllContact() {
        contactRepository.deleteAll();
        return "Contact database cleared";
    }
}
