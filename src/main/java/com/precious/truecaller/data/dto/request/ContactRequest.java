package com.precious.truecaller.data.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    @NotNull(message = "pls provide your last name, this field cannot be null")
    private String name;
    @NotNull(message = "pls provide a email, this cannot be null")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "")
    private String email;
    @Size(min = 11, max = 14, message = "Phone number should be 11 character")
    @NotNull(message = "pls provide a password, this cannot be null")
    private MobileNumber mobileNumber;
    private String countryCode;
    private String companyName;
}
