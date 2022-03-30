package com.precious.truecaller.service.messageService;

import com.precious.truecaller.data.dto.request.SmsSenderRequest;
import com.precious.truecaller.data.dto.response.SmsSenderResponse;

public interface SmsSenderService {
    SmsSenderResponse sendSms(String mobileNumber, String message);
}
