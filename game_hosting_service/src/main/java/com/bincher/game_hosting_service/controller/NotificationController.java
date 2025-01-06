package com.bincher.game_hosting_service.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bincher.game_hosting_service.dto.request.notification.SendNotificationRequestDto;
import com.bincher.game_hosting_service.dto.request.notification.PatchFcmTokenRequestDto;
import com.bincher.game_hosting_service.dto.response.notification.PatchFcmTokenResponseDto;
import com.bincher.game_hosting_service.dto.response.notification.SendNotificationResponseDto;
import com.bincher.game_hosting_service.service.NotificationService;
import com.bincher.game_hosting_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;
    private final UserService userService;

    @PatchMapping("/fcm")
    public ResponseEntity<? super PatchFcmTokenResponseDto> patchFcmToken(
            @RequestBody @Valid PatchFcmTokenRequestDto requestBody,
            @AuthenticationPrincipal String id
        ){
            ResponseEntity<? super PatchFcmTokenResponseDto> response = userService.patchFcmToken(requestBody, id);
            return response;
        }
    

    // 특정 버튼 클릭 시 관리자에게 알림 전송 API
    @PostMapping(value = "/send-to-admins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? super SendNotificationResponseDto> sendNotificationToAdmins(@RequestBody SendNotificationRequestDto notificationRequest) {
        ResponseEntity<? super SendNotificationResponseDto> response = notificationService.sendNotificationToAdmins(notificationRequest);
        return response;
    }

    // 특정 버튼 클릭 시 관리자에게 알림 전송 API
    @PostMapping(value = "/send-to-user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? super SendNotificationResponseDto> sendNotificationToUser(
        @RequestBody SendNotificationRequestDto notificationRequest,
        @PathVariable("userId") String userId) {
        ResponseEntity<? super SendNotificationResponseDto> response = notificationService.sendNotificationToUser(notificationRequest, userId);
        return response;
    }
}