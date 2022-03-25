package com.precious.truecaller.service.mobileService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import com.precious.truecaller.web.exception.mobileNumberException.MobileNumberAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileNumberServiceImpl implements MobileNumberService{

    private final MobileNumberRepository mobileNumberRepository;
    private final ObjectMapper mapper;

    @Autowired
    public MobileNumberServiceImpl(MobileNumberRepository mobileNumberRepository, ObjectMapper mapper) {
        this.mobileNumberRepository = mobileNumberRepository;
        this.mapper = mapper;
    }

    @Override
    public MobileNumberResponse addMobileNumber(MobileNumberRequest request) {
        MobileNumber number = mobileNumberRepository.findByMobileNumber(request.getMobileNumber()).orElseThrow(
                ()-> new MobileNumberAlreadyExistException("Mobile number already exist")
        );
        return mapper.convertValue(number, MobileNumberResponse.class);
    }
}
