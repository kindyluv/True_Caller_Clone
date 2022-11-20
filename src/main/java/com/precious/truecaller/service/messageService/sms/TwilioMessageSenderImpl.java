package com.precious.truecaller.service.messageService.sms;

import com.precious.truecaller.web.exception.NotificationException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;

public class TwilioMessageSenderImpl implements TwilioMessageSender{
   @Value("${TWILIO_ACCOUNT_SID}")
    private String accountSid;
   @Value("${TWILIO_AUTH_TOKEN}")
   private String authToken;
   @Value("${TWILIO_PHONE_NUMBER}")
   private String senderPhoneNumber;

    @Override
    public void sendSms(String sender, String message) {
        if(message == null) throw new NotificationException("Sms message body can not be empty");
        if (sender == null) throw new NotificationException("Sms sender can not be empty");
        try{
            Twilio.init(accountSid, authToken);
            Message.creator(
                    new com.twilio.type.PhoneNumber(senderPhoneNumber),
                    new com.twilio.type.PhoneNumber(sender),
                    message
            ).create();
        }catch (RuntimeException e){
            throw new NotificationException("Failed to send sms", e.getCause());
        }
    }
}
