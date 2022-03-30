package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.message.SmsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsMessageRepository extends JpaRepository<SmsMessage, Integer> {
    List<SmsMessage> findBySmsSender(String mobileNumber);
}
