package com.bincher.game_hosting_service.service;

import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.dto.request.notification.PatchFcmTokenRequestDto;
import com.bincher.game_hosting_service.dto.request.user.CheckCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.EmailCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.IsPasswordRightRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchEmailRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchPasswordRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchProfileImageRequestDto;
import com.bincher.game_hosting_service.dto.response.notification.PatchFcmTokenResponseDto;
import com.bincher.game_hosting_service.dto.response.user.CheckCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.EmailCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetSignInUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.IsPasswordRightResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchEmailResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchPasswordResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchProfileImageResponseDto;

public interface UserService {
    
    ResponseEntity<? super GetUserResponseDto> getUser(String id);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id);
    ResponseEntity<? super PatchFcmTokenResponseDto> patchFcmToken(PatchFcmTokenRequestDto dto, String id);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String id);
    ResponseEntity<? super PatchEmailResponseDto> patchEmail(PatchEmailRequestDto dto, String id);
    ResponseEntity<? super PatchPasswordResponseDto> patchPassword(PatchPasswordRequestDto dto, String id);
    ResponseEntity<? super IsPasswordRightResponseDto> isPasswordRight(IsPasswordRightRequestDto dto, String id);
    ResponseEntity<? super EmailCertificationForChangeResponseDto> emailCertificationForChange(EmailCertificationForChangeRequestDto dto, String id);
    ResponseEntity<? super CheckCertificationForChangeResponseDto> checkCertificationForChange(CheckCertificationForChangeRequestDto dto, String id);

}