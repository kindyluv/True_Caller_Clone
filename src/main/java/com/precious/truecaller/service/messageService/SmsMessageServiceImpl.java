package com.precious.truecaller.service.messageService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precious.truecaller.data.dto.request.SmsMessageRequest;
import com.precious.truecaller.data.dto.response.SmsMessageResponse;
import com.precious.truecaller.data.dto.response.SmsSenderResponse;
import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.message.SmsMessage;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.ContactRepository;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import com.precious.truecaller.data.repositories.SmsMessageRepository;
import com.precious.truecaller.web.exception.MobileNumberNotFoundException;
import com.precious.truecaller.web.exception.contactException.ContactNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SmsMessageServiceImpl implements SmsMessageService {

    private final SmsMessageRepository smsMessageRepository;
    private final MobileNumberRepository mobileNumberRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final SmsSenderService smsSenderService;
    private final ContactRepository contactRepository;

    @Autowired
    public SmsMessageServiceImpl(SmsMessageRepository smsMessageRepository, MobileNumberRepository mobileNumberRepository, ModelMapper modelMapper, ObjectMapper objectMapper, SmsSenderService smsSenderService, ContactRepository contactRepository) {
        this.smsMessageRepository = smsMessageRepository;
        this.mobileNumberRepository = mobileNumberRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.smsSenderService = smsSenderService;
        this.contactRepository = contactRepository;
    }

    @Override
    public SmsMessageResponse createMessage(SmsMessageRequest messageRequest) {
        MobileNumber number = mobileNumberRepository.findByNumber(messageRequest.getSmsSender());
        if (number == null) throw new MobileNumberNotFoundException("Contact with this "+messageRequest.getSmsSender()
                +" can not be found in your contact list");

        log.info("Message sending......");
        SmsSenderResponse smsSenderResponse = smsSenderService.sendSms(messageRequest.getSmsSender(), messageRequest.getMessageBody());

        SmsMessage smsMessage = modelMapper.map(smsSenderResponse, SmsMessage.class);
        SmsMessage savedSmsMessage = smsMessageRepository.save(smsMessage);
        return modelMapper.map(savedSmsMessage, SmsMessageResponse.class);
    }

    @Override
    public SmsMessageResponse receiveMessage(SmsMessageRequest request) {
        Contact senderContact = contactRepository.findByMobileNumberNumber(request.getSmsSender());
        if (senderContact == null) throw new ContactNotFoundException("Contact with this "+request.getSmsSender()
                +" mobile number does not exist");

        Contact receiverContact = contactRepository.findByMobileNumberNumber(request.getSmsReceiver());
        if (receiverContact == null) throw new ContactNotFoundException("Contact with this "+request.getSmsReceiver()
                +" mobile number does not exist");

        List<SmsMessage> smsMessage2 = smsMessageRepository.findById(senderContact.getId()).stream().toList();

        SmsMessage savedSms = modelMapper.map(smsMessage2, SmsMessage.class);
        return modelMapper.map(savedSms, SmsMessageResponse.class);
    }

    @Override
    public SmsMessageResponse findMessageByContact(SmsMessageRequest messageRequest) {
        return null;
    }

    @Override
    public SmsMessageResponse findMessageByMobileNumber(SmsMessageRequest messageRequest) {
        if (messageRequest == null) throw new IllegalArgumentException("Field can not be empty");
        Contact contact = contactRepository.findByMobileNumberNumber(messageRequest.getSmsSender());
        if (contact == null) throw new ContactNotFoundException("Contact with this "+messageRequest.getSmsSender()
                +" mobile number does not exist");

        SmsMessage smsMessage = smsMessageRepository.findById(contact.getId()).orElseThrow(
                ()-> new ContactNotFoundException("Contact with this "+messageRequest.getSmsSender()+" mobile number does not exist")
        );
        return modelMapper.map(smsMessage, SmsMessageResponse.class);
    }

//    @Override
//    public SmsMessageResponse forwardMessage(SmsMessageRequest request) {
//        if (request == null)
//        return null;
//    }

    @Override
    public String deleteMessageByContact(SmsMessageRequest request) {
        if (request == null) throw new IllegalArgumentException("Request can not be empty");
        Contact contact = contactRepository.findByMobileNumberNumber(request.getSmsSender());
        if (contact == null) throw new ContactNotFoundException("Contact with this "+request.getSmsSender()
                +" mobile number does not exist");

        SmsMessage smsMessage = smsMessageRepository.findById(contact.getId()).orElseThrow(
                ()-> new ContactNotFoundException("Contact with this "+request.getSmsSender()+" mobile number does not exist")
        );
        smsMessageRepository.deleteById(smsMessage.getId());
        return "Message successfully deleted";
    }

    @Override
    public String deleteMessageByMobileNumber(SmsMessageRequest request) {
        MobileNumber mobileNumber = mobileNumberRepository.findByNumber(request.getSmsSender());
        if (mobileNumber == null) throw  new ContactNotFoundException("This "+request.getSmsSender()+" mobile number does not exist");

        List<SmsMessage> smsMessage = smsMessageRepository.findBySmsSender(mobileNumber.getNumber());
        for (SmsMessage sm : smsMessage){
            smsMessageRepository.deleteById(sm.getId());
        }
        return "Message successfully deleted";
    }

    @Override
    public String reportSpamMessage(SmsMessageRequest request) {
        return null;
    }
}
