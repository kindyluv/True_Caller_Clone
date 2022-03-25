package com.precious.truecaller.service.contactService;

import com.github.fge.jsonpatch.JsonPatch;
import com.precious.truecaller.data.dto.request.ContactRequest;
import com.precious.truecaller.data.dto.response.ContactResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;

import java.util.List;

public interface ContactService {
    ContactResponse addContact(ContactRequest contactRequest);
    Boolean unBlockContactByMobileNumber(String mobileNumber);
    Boolean blockContactByMobileNumber(String mobileNumber);
    ContactResponse editContact(String contactName, JsonPatch jsonPatch);
    ContactResponse findContactByMobileNumber(String mobileNumber);
    ContactResponse findContactById(Integer id);
    ContactResponse findByContactName(String contactName);
    List<ContactResponse> findAllContact();
    String deleteContactByContactName(String contactName);
    String deleteContactById(Integer id);
    String deleteAllContact();
}
