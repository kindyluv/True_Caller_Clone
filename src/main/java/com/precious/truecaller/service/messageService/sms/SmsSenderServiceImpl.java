package com.precious.truecaller.service.messageService.sms;

import com.precious.truecaller.data.dto.request.SmsSenderRequest;
import com.precious.truecaller.data.dto.response.SmsSenderResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import com.precious.truecaller.service.messageService.sms.SmsSenderService;
import com.precious.truecaller.web.exception.BusinessLogicException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsSenderServiceImpl implements SmsSenderService {

    String USERNAME = "";
    String API_KEY = "";
    private final RestTemplate restTemplate;

    private SmsSenderRequest request;
    private final MobileNumberRepository mobileNumberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SmsSenderServiceImpl(RestTemplate restTemplate, MobileNumberRepository mobileNumberRepository, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.mobileNumberRepository = mobileNumberRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SmsSenderResponse sendSms(String mobileNumber, String message) {
        if (mobileNumber == null || message == null) throw new IllegalArgumentException("Field can not be empty");

        MobileNumber number = mobileNumberRepository.findByNumber(mobileNumber);
        if(number == null) throw  new BusinessLogicException("Contact with this "+mobileNumber+" mobile number does not exist on your contact list");

        request.setUserName(USERNAME);
        request.setMobileNumber(number.getNumber());
        request.setMessage(message);

        String url = "https://api.sendchamp.com/api/v1/sms/send";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(API_KEY);
        HttpEntity<SmsSenderRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<SmsSenderResponse> response = restTemplate.postForEntity(url, httpEntity, SmsSenderResponse.class);
        response.getBody();
        return modelMapper.map(response, SmsSenderResponse.class);
    }
}
