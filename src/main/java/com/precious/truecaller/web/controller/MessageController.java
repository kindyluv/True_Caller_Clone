package com.precious.truecaller.web.controller;

import com.precious.truecaller.data.dto.request.EmailMessageRequest;
import com.precious.truecaller.service.messageService.email.GeneralMessageNotification;
import com.precious.truecaller.service.messageService.sms.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    private final SmsSenderService smsSenderService;
    private final GeneralMessageNotification messageNotification;

    @Autowired
    public MessageController(SmsSenderService smsSenderService, GeneralMessageNotification messageNotification) {
        this.smsSenderService = smsSenderService;
        this.messageNotification = messageNotification;
    }

    @PostMapping(path = {"/{mobileNumber}"})
    public ResponseEntity<?> sendSms(@PathVariable String mobileNumber, String message){
        smsSenderService.sendSms(mobileNumber,message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> sendMessageNotification(@RequestBody EmailMessageRequest request){
        messageNotification.sendMessageNotification(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
