package com.bincher.game_hosting_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bincher.game_hosting_service.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

    // boolean isAdmin(String userId);

    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    List<UserEntity> findByRole(String role);

}
