package com.precious.truecaller.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsSenderResponse {
    private String mobileNumber;
    private String message;
    private String userName;
    private LocalDateTime dateCreated;
}
