package com.precious.truecaller.data.models.message;

import com.precious.truecaller.data.models.contact.Contact;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity(name = "messenger")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String messageBody;
    private String smsSender;
    private String smsReceiver;
    @CreationTimestamp
    private LocalDate dateCreated;
}
