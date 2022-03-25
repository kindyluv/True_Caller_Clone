package com.precious.truecaller.web.controller;


import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.service.mobileService.MobileNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/mobileNumber")
@RestController
public class MobileNumberController {

    private final MobileNumberService mobileNumberService;

    @Autowired
    public MobileNumberController(MobileNumberService mobileNumberService) {
        this.mobileNumberService = mobileNumberService;
    }

    @PostMapping
    public MobileNumberResponse addMobileNumber(MobileNumberRequest request){
        return mobileNumberService.addMobileNumber(request);
    }
}
