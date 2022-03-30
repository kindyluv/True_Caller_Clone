package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
   Optional<Contact> findByUserName(String contactName);
    @Transactional
    void removeByUserName(String name);
    Contact findByMobileNumberNumber(String mobileNumber);
    Contact findByMobileNumberId(Integer mobileNumberId);
}
