package com.precious.truecaller.data.models.user;

import com.precious.truecaller.data.models.contact.Contact;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime lastAccessed;
    @OneToMany
    private List<Contact> contact;
    private UserCategory userCategory;
}
