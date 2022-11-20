package com.precious.truecaller.service.callService;

import com.precious.truecaller.data.dto.request.CallRequest;

public interface TwilioCallService {
    void makeCall(CallRequest caller);
}
