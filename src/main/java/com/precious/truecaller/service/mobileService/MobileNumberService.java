package com.precious.truecaller.service.mobileService;

import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;

import java.util.List;

public interface MobileNumberService {
    MobileNumberResponse addMobileNumber(MobileNumberRequest request);
   Boolean blockedContactByMobileNumber(String mobileNumber);
    Boolean unBlockedMobileNumberByMobileNumber(String mobileNumber);
    List<MobileNumber> findAllBlockedMobileNumbers();
    void deleteAllMobileNumber();
}
