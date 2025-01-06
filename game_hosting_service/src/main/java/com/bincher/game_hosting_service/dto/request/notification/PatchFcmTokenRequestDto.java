package com.bincher.game_hosting_service.dto.request.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatchFcmTokenRequestDto {
    private String fcmToken; 
}
