package com.bincher.game_hosting_service.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto{
    
    private String email;
    private String id;
    private String profileImage;
    private String role;

    private GetSignInUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.id = userEntity.getUserId();
        this.profileImage = userEntity.getProfileImage();
        this.role = userEntity.getRole();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
