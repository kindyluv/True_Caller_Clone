package com.precious.truecaller.service.messageService.sms;

import com.precious.truecaller.data.models.message.SmsMessage;
import com.precious.truecaller.data.repositories.SmsMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsReceiver {

    private final SmsMessageRepository smsMessageRepository;

    public SmsReceiver(SmsMessageRepository smsMessageRepository) {
        this.smsMessageRepository = smsMessageRepository;
    }

    public void ReceiveMessage(SmsMessage message) {
        log.info("Received < {} >", message);
        smsMessageRepository.save(message);
    }
}
