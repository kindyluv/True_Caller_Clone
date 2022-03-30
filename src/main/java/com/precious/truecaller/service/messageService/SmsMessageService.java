package com.precious.truecaller.service.messageService;

import com.precious.truecaller.data.dto.request.SmsMessageRequest;
import com.precious.truecaller.data.dto.response.SmsMessageResponse;
import com.precious.truecaller.data.models.message.SmsMessage;

public interface SmsMessageService {
    SmsMessageResponse createMessage(SmsMessageRequest messageRequest);
    SmsMessageResponse receiveMessage(SmsMessageRequest messageRequest);
//    SmsMessageResponse forwardMessage(SmsMessageRequest messageRequest);
    SmsMessageResponse findMessageByContact(SmsMessageRequest messageRequest);
    SmsMessageResponse findMessageByMobileNumber(SmsMessageRequest messageRequest);
    String deleteMessageByContact(SmsMessageRequest messageRequest);
    String deleteMessageByMobileNumber(SmsMessageRequest messageRequest);
    String reportSpamMessage(SmsMessageRequest messageRequest);

}
