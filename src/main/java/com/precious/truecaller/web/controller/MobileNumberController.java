package com.precious.truecaller.web.controller;


import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.service.mobileService.MobileNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/mobileNumber")
@RestController
public class MobileNumberController {

    private final MobileNumberService mobileNumberService;

    @Autowired
    public MobileNumberController(MobileNumberService mobileNumberService) {
        this.mobileNumberService = mobileNumberService;
    }

    @PostMapping
    public MobileNumberResponse addMobileNumber(@Valid @RequestBody MobileNumberRequest request){
        return mobileNumberService.addMobileNumber(request);
    }

    @GetMapping(path = "/block/{mobileNumber}")
    public Boolean blockedContactByMobileNumber(@PathVariable String mobileNumber){
        return mobileNumberService.blockedContactByMobileNumber(mobileNumber);
    }

    @GetMapping(path = "/unblock/{mobileNumber}")
    public Boolean unBlockedMobileNumberByMobileNumber(@PathVariable String mobileNumber){
        return mobileNumberService.unBlockedMobileNumberByMobileNumber(mobileNumber);
    }

    @GetMapping
    public List<MobileNumber> findAllBlockedMobileNumbers(){
        return mobileNumberService.findAllBlockedMobileNumbers();
    }
}
