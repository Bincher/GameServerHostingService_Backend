package com.bincher.game_hosting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bincher.game_hosting_service.entity.GameServerEntity;

@Repository
public interface GameServerRepository extends JpaRepository<GameServerEntity, Integer>{
    boolean existsByUserId(String userId);
}
