package com.precious.truecaller.data.models.message;

import com.precious.truecaller.data.models.contact.Contact;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity(name = "messenger")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String messageBody;
    @OneToMany
    private List<Contact> contact;
}
