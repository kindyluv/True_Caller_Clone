package com.precious.truecaller.data.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String messageBody;
    private String mobileNumber;
}
