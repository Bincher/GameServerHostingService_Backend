package com.bincher.game_hosting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bincher.game_hosting_service.entity.CertificationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {
    
    CertificationEntity findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);

} 