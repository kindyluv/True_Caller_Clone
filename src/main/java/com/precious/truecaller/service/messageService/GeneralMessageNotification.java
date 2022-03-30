package com.precious.truecaller.service.messageService;

import com.precious.truecaller.data.dto.request.EmailMessageRequest;

public interface GeneralMessageNotification {
    void sendMessageNotification(EmailMessageRequest request);
}
