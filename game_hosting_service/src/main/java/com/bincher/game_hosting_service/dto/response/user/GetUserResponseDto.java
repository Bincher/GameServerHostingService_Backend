package com.bincher.game_hosting_service.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto extends ResponseDto{
    
    private String id;
    private String email;
    private String profileImage;
    private String role;

    private GetUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.id = userEntity.getUserId();
        this.role = userEntity.getRole();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity){
        GetUserResponseDto result = new GetUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
