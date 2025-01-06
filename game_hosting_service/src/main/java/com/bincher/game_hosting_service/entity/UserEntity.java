package com.bincher.game_hosting_service.entity;

import com.bincher.game_hosting_service.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
  
    @Id
    @Column(name = "user_id")
    private String userId;
    private String email;
    private String password;
    private String profileImage;
    private String type;
    private String role;

    @Column(nullable = true)
    private String fcmToken;


    public UserEntity(SignUpRequestDto dto){
        this.userId = dto.getId();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public UserEntity(String userId, String email, String type){
        this.userId = userId;
        this.email = email;
        this.password = "Passw0rd";
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public void setFcmToken(String fcmToken){
        this.fcmToken = fcmToken;
    }

}
