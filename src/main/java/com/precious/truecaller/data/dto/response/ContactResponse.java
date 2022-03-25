package com.precious.truecaller.data.dto.response;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private String name;
    private MobileNumber mobileNumber;
}
