package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.call.VideoCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCallRepository extends JpaRepository<VideoCall, Integer> {
}
