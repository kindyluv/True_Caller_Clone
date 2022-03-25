package com.precious.truecaller.web.controller;


import com.github.fge.jsonpatch.JsonPatch;
import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.service.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ContactResponse addContact(@RequestBody ContactRequest request){
        return contactService.addContact(request);
    }

    @GetMapping(path = "/{mobileNumber}")
    public ContactResponse findByMobileNumber(@PathVariable String mobileNumber){
        return contactService.findContactByMobileNumber(mobileNumber);
    }

    @GetMapping(path = "/{contactName}")
    public ContactResponse findByContactName(@PathVariable String contactName){
        return contactService.findByContactName(contactName);
    }

    @GetMapping
    public List<ContactResponse> findAllContact(){
        return contactService.findAllContact();
    }

    @PatchMapping(path = "/{mobileNumber}")
    public Boolean blockContactByMobileNumber(@PathVariable String mobileNumber){
        return contactService.blockContactByMobileNumber(mobileNumber);
    }
    @PatchMapping(path = "/{contactName}", consumes = "application/json-patch+json")
    public ContactResponse editContact(@PathVariable String contactName, @RequestBody JsonPatch jsonPatch){
        return  contactService.editContact(contactName, jsonPatch);
    }

    @PatchMapping(path = "/{phoneNumber}")
    public Boolean unBlockContactByMobileNumber(@PathVariable String phoneNumber){
        return contactService.unBlockContactByMobileNumber(phoneNumber);
    }

    @DeleteMapping(path = "/{contactName}")
    public String deleteContactByContactName(@PathVariable String contactName){
        return contactService.deleteContactByContactName(contactName);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteContactById(@PathVariable Integer id){
        return contactService.deleteContactById(id);
    }

    @DeleteMapping
    public String deleteAllContact(){
        return contactService.deleteAllContact();
    }
}
