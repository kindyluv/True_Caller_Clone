package com.precious.truecaller.data.models.user;

import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String firstName;

    @Column(unique = true)
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    @OneToOne
    private MobileNumber mobileNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Contact> contact;

    private Boolean isActive = true;

    private UserCategory userCategory;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<MobileNumber> blockedNumbers;

    @Embedded
    private Address userAddress;

    @CreationTimestamp
    private LocalDate lastAccessed;
}
