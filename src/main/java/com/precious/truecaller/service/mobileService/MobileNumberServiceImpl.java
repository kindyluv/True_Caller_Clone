package com.precious.truecaller.service.mobileService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import com.precious.truecaller.web.exception.mobileNumberException.MobileNumberAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileNumberServiceImpl implements MobileNumberService{

    private final MobileNumberRepository mobileNumberRepository;
    private final ObjectMapper mapper;
    private final ModelMapper modelMapper;

    @Autowired
    public MobileNumberServiceImpl(MobileNumberRepository mobileNumberRepository, ObjectMapper mapper, ModelMapper modelMapper) {
        this.mobileNumberRepository = mobileNumberRepository;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public MobileNumberResponse addMobileNumber(MobileNumberRequest request) {
        MobileNumber number = mobileNumberRepository.findByNumber(request.getNumber());
        if (number != null) throw new MobileNumberAlreadyExistException("Mobile number already exist");
        MobileNumber mapped = modelMapper.map(request, MobileNumber.class);
        MobileNumber mobileNumber = mobileNumberRepository.save(mapped);
        return modelMapper.map(mobileNumber, MobileNumberResponse.class);
    }

    @Override
    public Boolean blockedContactByMobileNumber(String mobileNumber) {
        MobileNumber foundMobileNumber = mobileNumberRepository.findByNumber(mobileNumber);
        if (foundMobileNumber == null) throw new MobileNumberAlreadyExistException("Mobile number already exist");

        if (foundMobileNumber.getIsBlocked().equals(false)) foundMobileNumber.setIsBlocked(true);
        MobileNumber saved = mobileNumberRepository.save(foundMobileNumber);
        return saved.getIsBlocked();
    }

    @Override
    public Boolean unBlockedMobileNumberByMobileNumber(String mobileNumber) {
        MobileNumber mobile = mobileNumberRepository.findByNumber(mobileNumber);
        if (mobile == null) throw new MobileNumberAlreadyExistException("Mobile number already exist");

        if (mobile.getIsBlocked().equals(true)) mobile.setIsBlocked(false);
        MobileNumber saved = mobileNumberRepository.save(mobile);
        return saved.getIsBlocked();
    }

    @Override
    public List<MobileNumber> findAllBlockedMobileNumbers() {
        List<MobileNumber> numbers = mobileNumberRepository.findAll();
        List <MobileNumber> isBlockedNumbers = new ArrayList<MobileNumber>();
        for (MobileNumber mobileNumber : numbers) {
            if (mobileNumber.getIsBlocked().equals(true)) isBlockedNumbers.add(mobileNumber);
        }
        return isBlockedNumbers;
    }

    @Override
    public void deleteAllMobileNumber() {
        mobileNumberRepository.deleteAll();
    }

}
