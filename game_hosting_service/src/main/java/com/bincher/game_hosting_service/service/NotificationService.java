package com.bincher.game_hosting_service.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.dto.request.notification.SendNotificationRequestDto;
import com.bincher.game_hosting_service.dto.response.notification.SendNotificationResponseDto;
import com.bincher.game_hosting_service.entity.UserEntity;
import com.bincher.game_hosting_service.repository.UserRepository;
import com.google.auth.oauth2.GoogleCredentials;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepository;

    // Firebase 프로젝트 ID (Firebase 콘솔의 프로젝트 설정에서 확인 가능)
    private static final String PROJECT_ID = "gamehostingservice-6f0a9";

    // 서비스 계정 키 파일 경로
    private static final String SERVICE_ACCOUNT_FILE = "C:\\dev\\GameHostingServiceBackend\\game_hosting_service\\src\\main\\java\\com\\bincher\\game_hosting_service\\key\\gamehostingservice-6f0a9-4e43b94672d8.json";

    public ResponseEntity<? super SendNotificationResponseDto> sendNotificationToAdmins(SendNotificationRequestDto notificationRequest) {
        List<UserEntity> admins = userRepository.findByRole("ROLE_ADMIN");

        for (UserEntity admin : admins) {
            if (admin.getFcmToken() != null) {
                sendPushNotification(admin.getFcmToken(), notificationRequest.getTitle(), notificationRequest.getMessage());
            }
        }

        return SendNotificationResponseDto.success();
    }

    public ResponseEntity<? super SendNotificationResponseDto> sendNotificationToUser(SendNotificationRequestDto notificationRequest, String userId) {
        UserEntity user = userRepository.findByUserId(userId);

        if (user.getFcmToken() != null) {
            sendPushNotification(user.getFcmToken(), notificationRequest.getTitle(), notificationRequest.getMessage());
        }

        return SendNotificationResponseDto.success();
    }

    // FCM 푸시 알림 전송 로직 (v1 API 사용)
    private void sendPushNotification(String fcmToken, String title, String message) {
        try {
            // FCM 메시지 본문 작성
            String jsonMessage = createMessage(fcmToken, title, message);
            System.out.println(jsonMessage);
            // 액세스 토큰 가져오기
            String accessToken = getAccessToken();

            // HTTP 요청을 통해 FCM 서버로 메시지 전송
            OkHttpClient client = new OkHttpClient(); System.out.println("메시지 전송");
            RequestBody requestBody = RequestBody.create(jsonMessage, MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send")
                    .post(requestBody)
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FCM 메시지 생성 함수
    private String createMessage(String fcmToken, String title, String message) {
        return "{"
                + "\"message\":{"
                + "\"token\":\"" + fcmToken + "\","
                + "\"notification\":{"
                + "\"title\":\"" + title + "\","
                + "\"body\":\"" + message + "\""
                + "}"
                + "}"
                + "}";
    }

    // 액세스 토큰 가져오기 (GoogleCredentials 사용)
    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_FILE))
                .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}