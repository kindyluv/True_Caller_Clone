package com.precious.truecaller.data.models.notification;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sms_sender")
public class SmsSender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<MobileNumber> mobileNumber;
}
