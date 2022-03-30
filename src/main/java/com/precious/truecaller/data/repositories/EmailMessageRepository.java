package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.message.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailMessage, Integer> {
}
