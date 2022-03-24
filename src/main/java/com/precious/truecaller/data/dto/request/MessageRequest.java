package com.precious.truecaller.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageRequest {
    private String messageBody;
    private String mobileNumber;
}
