package com.bincher.game_hosting_service.dto.response.game;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.object.GameServerListItem;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.entity.GameServerListViewEntity;

import lombok.Getter;

@Getter
public class GetUserServerListResponseDto extends ResponseDto{
    
    private List<GameServerListItem> userServerList;
    
    private GetUserServerListResponseDto(List<GameServerListViewEntity> gameServerListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userServerList = GameServerListItem.getList(gameServerListViewEntities);
    }

    public static ResponseEntity<GetUserServerListResponseDto> success(List<GameServerListViewEntity> gameServerListViewEntities){
        GetUserServerListResponseDto result = new GetUserServerListResponseDto(gameServerListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
