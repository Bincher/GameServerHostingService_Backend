package com.bincher.game_hosting_service.dto.response.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PostGameServerResponseDto extends ResponseDto{
    
    private PostGameServerResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostGameServerResponseDto> success(){
        PostGameServerResponseDto result = new PostGameServerResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
