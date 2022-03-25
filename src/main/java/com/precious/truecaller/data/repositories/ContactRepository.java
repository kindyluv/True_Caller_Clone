package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.contact.Contact;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Integer> {
   Optional<Contact> findByName(String contactName);

    void deleteByName(String name);


    Optional<Contact> findByMobileNumber(MobileNumber string);
}
