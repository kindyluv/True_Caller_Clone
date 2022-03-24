package com.precious.truecaller.service.messageService;

import com.precious.truecaller.data.dto.request.MessageRequest;
import com.precious.truecaller.data.dto.response.MessageResponse;
import com.precious.truecaller.data.models.message.Message;

public interface MessageService {
    MessageResponse createMessage(MessageRequest messageRequest);
    Message receiveMessage(String messageBody, String mobileNumber);
    Message forwardMessage(String message, String contactName);
    void deleteMessageByContact(String contactName);
    void deleteMessageByMobileNumber(String mobileNumber);
    void reportSpamMessage(String message);

}
