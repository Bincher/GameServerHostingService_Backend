package com.bincher.game_hosting_service.dto.response.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PostGameResponseDto extends ResponseDto{
    
    private PostGameResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostGameResponseDto> success(){
        PostGameResponseDto result = new PostGameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
