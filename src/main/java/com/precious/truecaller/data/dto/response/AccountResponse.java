package com.precious.truecaller.data.dto.response;

import com.precious.truecaller.data.models.user.UserCategory;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String userName;
    private String email;
    private UserCategory userCategory;
}
