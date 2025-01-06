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
public class GetServerListResponseDto extends ResponseDto{
    
    private List<GameServerListItem> serverList;
    
    private GetServerListResponseDto(List<GameServerListViewEntity> gameServerListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.serverList = GameServerListItem.getList(gameServerListViewEntities);
    }

    public static ResponseEntity<GetServerListResponseDto> success(List<GameServerListViewEntity> gameServerListViewEntities){
        GetServerListResponseDto result = new GetServerListResponseDto(gameServerListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
