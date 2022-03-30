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
import com.precious.truecaller.data.repositories.MobileNumberRepository;
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
    private final MobileNumberRepository mobileNumberRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, MobileNumberRepository mobileNumberRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.contactRepository = contactRepository;
        this.mobileNumberRepository = mobileNumberRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ContactResponse addContact(ContactRequest contactRequest) {
        Optional<Contact> foundContact = contactRepository.findByUserName(contactRequest.getUserName());
        if (foundContact.isPresent()) throw new ContactAlreadyExistsException("Contact with same mobile number already exist");

        MobileNumber mobileNumber = mobileNumberRepository.save(
                MobileNumber.builder()
                        .isBlocked(false)
                        .number(contactRequest.getMobileNumber())
                        .build());

        Contact contact = modelMapper.map(contactRequest, Contact.class);
        contact.setMobileNumber(mobileNumber);

        Contact savedContact = contactRepository.save(contact);

        return modelMapper.map(savedContact, ContactResponse.class);
    }

    @Override
    public ContactResponse findContactByMobileNumber(String string) {
        Contact contact = contactRepository.findByMobileNumberNumber(string);
        if (contact == null) throw new ContactNotFoundException("Mobile number " + string + " can doesnt exist with a name");

        return modelMapper.map(contact, ContactResponse.class);
    }

    @Override
    public ContactResponse findContactById(Integer id) {
        Contact foundContact = contactRepository.findById(id).orElse(null);
        if (foundContact == null) throw new ContactNotFoundException("Contact with id " + id + " is not found");
        return modelMapper.map(foundContact, ContactResponse.class);
    }

    @Override
    public ContactResponse findByContactName(String contactName) {
        Contact contact = contactRepository.findByUserName(contactName).orElse(null);
        if (contact == null) throw new ContactNotFoundException("Contact with contact name " + contactName + " is not found");

        return modelMapper.map(contact, ContactResponse.class);
    }

    @Override
    public List<ContactResponse> findAllContact() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactResponse> responses = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactResponse response = ContactResponse.builder()
                    .userName(contact.getUserName())
                    .mobileNumber(contact.getMobileNumber().getNumber())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public Boolean blockContactByContactName(String contactName) {
        Contact contact = contactRepository.findByUserName(contactName).orElseThrow(
                () -> new ContactNotFoundException("Mobile number " + contactName + " can doesnt exist with a name")
        );
        if (contact.getIsBlocked().equals(false)) contact.setIsBlocked(true);
        Contact saved = contactRepository.save(contact);
        return saved.getIsBlocked();
    }

    @Override
    public Boolean unBlockContactByContactName(String contactName) {
        Contact contact = contactRepository.findByUserName(contactName).orElseThrow(
                () -> new ContactNotFoundException("Contact with contact name " + contactName + " can doesnt exist with a name")
        );
        if (contact.getIsBlocked().equals(true)) contact.setIsBlocked(false);
        Contact saved = contactRepository.save(contact);
        return saved.getIsBlocked();
    }


    @Override
    public List<Contact> findAllBlockedContacts() {
        List<Contact> allContacts = contactRepository.findAll();
        List<Contact> blockedContacts = new ArrayList<>();
        for (Contact contact: allContacts) {
            if (contact.getIsBlocked().equals(true)) blockedContacts.add(contact);
        }
        return blockedContacts;
    }

    @Override
    public ContactResponse editContact(String contactName, JsonPatch jsonPatch) {
        if (contactName == null) throw new IllegalArgumentException("Contact name can not be null");
        Contact foundContact = contactRepository.findByUserName(contactName).orElseThrow(
                () -> new ContactNotFoundException("Contact With name " + contactName + " is null")
        );
        Contact contact = foundContact;
        try {
            contact = patchContact(jsonPatch, foundContact);
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
    public String deleteContactByContactName(String contactName) {
        ContactResponse contact = findByContactName(contactName);
        contactRepository.removeByUserName(contact.getUserName());
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
