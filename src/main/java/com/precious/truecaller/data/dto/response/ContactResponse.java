package com.precious.truecaller.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private String userName;
    private String countryCode;
    private String mobileNumber;
    private Boolean isBlocked;
    private LocalDateTime dateCreated;
}
