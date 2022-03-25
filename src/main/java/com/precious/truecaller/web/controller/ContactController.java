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
@RequestMapping("api/v1/contact")
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

    @GetMapping(path = "/{string}")
    public ContactResponse findByMobileNumber(@PathVariable @RequestBody MobileNumber string){
        return contactService.findContactByMobileNumber(string);
    }

    @GetMapping(path = "/{contactName}")
    public ContactResponse findByContactName(@PathVariable java.lang.String contactName){
        return contactService.findByContactName(contactName);
    }

    @GetMapping
    public List<ContactResponse> findAllContact(){
        return contactService.findAllContact();
    }

    @PatchMapping(path = "/blockContact/{string}")
    public Boolean blockContactByMobileNumber(@PathVariable MobileNumber string){
        return contactService.blockContactByMobileNumber(string);
    }
    @PatchMapping(path = "/{contactName}", consumes = "application/json-patch+json")
    public ContactResponse editContact(@PathVariable java.lang.String contactName, @RequestBody JsonPatch jsonPatch){
        return  contactService.editContact(contactName, jsonPatch);
    }

    @PatchMapping(path = "/unblockContact/{string}")
    public Boolean unBlockContactByMobileNumber(@PathVariable MobileNumber string){
        return contactService.unBlockContactByMobileNumber(string);
    }

    @DeleteMapping(path = "/{contactName}")
    public java.lang.String deleteContactByContactName(@PathVariable java.lang.String contactName){
        return contactService.deleteContactByContactName(contactName);
    }

    @DeleteMapping(path = "/{id}")
    public java.lang.String deleteContactById(@PathVariable Integer id){
        return contactService.deleteContactById(id);
    }

    @DeleteMapping
    public java.lang.String deleteAllContact(){
        return contactService.deleteAllContact();
    }
}
