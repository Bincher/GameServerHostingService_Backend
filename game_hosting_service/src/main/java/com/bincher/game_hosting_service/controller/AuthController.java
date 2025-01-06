package com.bincher.game_hosting_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bincher.game_hosting_service.dto.request.auth.CheckCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.EmailCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.IdCheckRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignInRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignUpRequestDto;
import com.bincher.game_hosting_service.dto.response.auth.CheckCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.DeleteUserResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.EmailCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.IdCheckResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.SignInResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.SignUpResponseDto;
import com.bincher.game_hosting_service.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.SignUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> SignIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.SignIn(requestBody);
        return response;
    }

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        
        return response;
    }
    
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
        @RequestBody @Valid EmailCertificationRequestDto requestBody
        ) {
            ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
            return response;
    }
    
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super DeleteUserResponseDto> response = authService.deleteUser(userId);
        return response;
    }
    
}
