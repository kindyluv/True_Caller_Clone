package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.call.AudioCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioCallRepository extends JpaRepository<AudioCall,Integer> {
}
