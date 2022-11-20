package com.precious.truecaller.service.callService;

import com.precious.truecaller.data.dto.request.CallRequest;
import com.precious.truecaller.web.exception.NotificationException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class TwilioCallServiceImpl implements TwilioCallService{
    @Value("${TWILIO_ACCOUNT_SID}")
    private String accountSid;
    @Value("${TWILIO_AUTH_TOKEN}")
    private String authToken;
    @Value("${TWILIO_PHONE_NUMBER}")
    private String callerPhoneNumber;
    @Value("${TWILIO_CALL_URL")
    private String callUrl;

    @Override
    public void makeCall(CallRequest receiver) {
        try{
            Twilio.init(accountSid, authToken);
            Call.creator(
                    new PhoneNumber(callerPhoneNumber),
                    new PhoneNumber(receiver.getMobileNumber()),
                    String.valueOf(new URL(callUrl))
            ).create();
        }catch (RuntimeException | MalformedURLException e){
            throw new NotificationException("Failed to put a call through to " + callerPhoneNumber + e.getMessage());
        }
        }
}
