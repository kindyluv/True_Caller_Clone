package com.precious.truecaller.data.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessageResponse {
    private String messageBody;
    private String smsSender;
    private LocalDateTime dateCreated;
}
