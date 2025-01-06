package com.bincher.game_hosting_service.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePk implements Serializable {
    @Column(name="user_id")
    private String userId;

}
