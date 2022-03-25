package com.precious.truecaller.data.models.contact;

//import com.precious.truecaller.data.models.mobile.String;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String companyName;
    @Column(unique = true)
    private String email;
    @OneToOne
    private MobileNumber mobileNumber;
    private Boolean isBlocked = false;
}
