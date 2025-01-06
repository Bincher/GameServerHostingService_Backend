package com.bincher.game_hosting_service.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.bincher.game_hosting_service.entity.GameEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameListItem {
    private String title;
    private String description;
    private String gameImage;
    private String amountLevel;

    public GameListItem(GameEntity gameEntity){
        this.title = gameEntity.getTitle();
        this.description = gameEntity.getDescription();
        this.gameImage = gameEntity.getGameImage();
        this.amountLevel = gameEntity.getAmountLevel();
    }

    public static List<GameListItem> getList(List<GameEntity> boardEntities){
        List<GameListItem> list = new ArrayList<>();

        for(GameEntity gameEntity: boardEntities){
            GameListItem gameListItem = new GameListItem(gameEntity);
            list.add(gameListItem);
        }

        return list;
    }
}
