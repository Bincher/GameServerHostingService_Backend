package com.bincher.game_hosting_service.entity;

import com.bincher.game_hosting_service.dto.request.game.PostGameRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="game")
@Table(name="game")
public class GameEntity{
    @Id
    private int id;
    private String title;
    private String description;
    private String gameImage;
    private String amountLevel;

    public GameEntity(PostGameRequestDto dto){
        this.description = dto.getDescription();
        this.title = dto.getTitle();
        this.gameImage = dto.getGameImage();
        this.amountLevel = dto.getAmountLevel();
    }


}