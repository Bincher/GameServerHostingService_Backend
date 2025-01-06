package com.bincher.game_hosting_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bincher.game_hosting_service.dto.request.user.CheckCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.EmailCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.IsPasswordRightRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchEmailRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchPasswordRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchProfileImageRequestDto;
import com.bincher.game_hosting_service.dto.response.user.CheckCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.EmailCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetSignInUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.IsPasswordRightResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchEmailResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchPasswordResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchProfileImageResponseDto;
import com.bincher.game_hosting_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @PathVariable("id") String id
    ){
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(id);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
        @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(id);
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
        @RequestBody @Valid PatchProfileImageRequestDto requestBody,
        @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody, id);
        return response;
    }

    @PatchMapping("/email")
    public ResponseEntity<? super PatchEmailResponseDto> patchEmail(
        @RequestBody @Valid PatchEmailRequestDto requestBody,
        @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super PatchEmailResponseDto> response = userService.patchEmail(requestBody, id);
        return response;
    }

    @PatchMapping("/password")
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(
        @RequestBody @Valid PatchPasswordRequestDto requestBody,
        @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super PatchPasswordResponseDto> response = userService.patchPassword(requestBody, id);
        return response;
    }

    @PostMapping("/password/is-right")
    public ResponseEntity<? super IsPasswordRightResponseDto> isPasswordRight(
        @RequestBody @Valid IsPasswordRightRequestDto requestBody,
        @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super IsPasswordRightResponseDto> response = userService.isPasswordRight(requestBody, id);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationForChangeResponseDto> emailCertification(
        @RequestBody @Valid EmailCertificationForChangeRequestDto requestBody, @AuthenticationPrincipal String id
        ) {
            ResponseEntity<? super EmailCertificationForChangeResponseDto> response = userService.emailCertificationForChange(requestBody, id);
            return response;
    }
    
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationForChangeResponseDto> checkCertification(
        @RequestBody @Valid CheckCertificationForChangeRequestDto requestBody, @AuthenticationPrincipal String id
    ) {
        ResponseEntity<? super CheckCertificationForChangeResponseDto> response = userService.checkCertificationForChange(requestBody, id);
        return response;
    }
}
