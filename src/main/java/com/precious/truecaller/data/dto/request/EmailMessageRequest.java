package com.precious.truecaller.data.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageRequest {
    private String senderEmail;
    private String receiverEmail;
    private String senderName;
    private String message;
    private String subject;
}
