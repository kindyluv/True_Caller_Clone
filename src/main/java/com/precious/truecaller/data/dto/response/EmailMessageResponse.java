package com.precious.truecaller.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageResponse {
    private String senderEmail;
    private String receiverEmail;
    private String senderName;
    private String message;
    private String subject;
    private LocalDateTime dateCreated;
}
