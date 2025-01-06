package com.bincher.game_hosting_service.dto.request.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendNotificationRequestDto {
    private String title;       // 알림 제목
    private String message;     // 알림 내용
}