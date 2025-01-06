package com.bincher.game_hosting_service.dto.response.game;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.object.GameListItem;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.entity.GameEntity;

import lombok.Getter;

@Getter
public class GetGameListResponseDto extends ResponseDto{
    private List<GameListItem> gameList;

    private GetGameListResponseDto(List<GameEntity> boardEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.gameList = GameListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetGameListResponseDto> success(List<GameEntity> boardEntities){
        GetGameListResponseDto result = new GetGameListResponseDto(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } 
}
