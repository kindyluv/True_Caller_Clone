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
    @NotNull(message = "please provide your last name, this field cannot be null")
    private java.lang.String name;
    @NotNull(message = "please provide a email, this cannot be null")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Invalid email")
    private java.lang.String email;
    @Size(min = 11, max = 14, message = "Phone number should be 11 character")
    @NotNull(message = "please provide a password, this cannot be null")
    private String mobileNumber;
    private java.lang.String countryCode;
    private java.lang.String companyName;
}
