package com.bincher.game_hosting_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bincher.game_hosting_service.entity.GameServerListViewEntity;

public interface GameServerListViewRepository extends JpaRepository<GameServerListViewEntity, Integer>{
    List<GameServerListViewEntity> findByUserIdOrderByNameDesc(String userId);
    List<GameServerListViewEntity> OrderByNameDesc();
}
