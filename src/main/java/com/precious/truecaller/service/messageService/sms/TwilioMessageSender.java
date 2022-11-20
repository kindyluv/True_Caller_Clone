package com.precious.truecaller.service.messageService.sms;

public interface TwilioMessageSender {
    void sendSms(String sender, String message);
}
