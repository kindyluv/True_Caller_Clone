package com.precious.truecaller.service.mobileService;

import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;

public interface MobileNumberService {
    MobileNumberResponse addMobileNumber(MobileNumberRequest request);
}
