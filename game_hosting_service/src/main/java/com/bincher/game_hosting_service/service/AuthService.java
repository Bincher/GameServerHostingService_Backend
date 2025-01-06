package com.bincher.game_hosting_service.service;

import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.dto.response.auth.CheckCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.DeleteUserResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.EmailCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.IdCheckResponseDto;
import com.bincher.game_hosting_service.dto.request.auth.CheckCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.EmailCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.IdCheckRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignInRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignUpRequestDto;
import com.bincher.game_hosting_service.dto.response.auth.SignInResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> SignUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> SignIn(SignInRequestDto dto);
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId);
}
