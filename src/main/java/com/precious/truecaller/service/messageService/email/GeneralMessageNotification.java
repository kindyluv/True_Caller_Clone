package com.precious.truecaller.service.messageService.email;

import com.precious.truecaller.data.dto.request.EmailMessageRequest;

public interface GeneralMessageNotification {
    void sendMessageNotification(EmailMessageRequest request);
}
