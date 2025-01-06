package com.bincher.game_hosting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bincher.game_hosting_service.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{
    boolean existsByServerId(int ServerId);
    PaymentEntity findByServerId(int serverId);
}
