package com.bincher.game_hosting_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bincher.game_hosting_service.entity.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    boolean existsByTitle(String title);

    Optional<GameEntity> findByTitle(String title);

    List<GameEntity> findByOrderByTitleDesc();

    
}
