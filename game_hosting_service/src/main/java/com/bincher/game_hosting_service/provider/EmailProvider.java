package com.bincher.game_hosting_service.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {
    
    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[게임 호스팅 서비스] 인증메일입니다.";

    private final String SUPPORT_SUBJECT = "[게임 호스팅 서비스] 고객 지원 요청";

    private final String SUPPORT_EMAIL = "kimsb000616@gmail.com";

    public boolean sendCertificationMail(String email, String certificationNumber){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

        return true;
    
    }

    public boolean sendSupportEmail(String userId, String userEmail, String serverInformation, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getSupportEmailContent(userId, userEmail, serverInformation, content);

            messageHelper.setTo(SUPPORT_EMAIL);
            messageHelper.setSubject(SUPPORT_SUBJECT);
            messageHelper.setText(htmlContent, true);
            messageHelper.setReplyTo(userEmail); // 답장 시 사용자 이메일로 가도록 설정

            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return true;
    }

    private String getCertificationMessage(String certificationNumber){

        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[게임 호스팅 서비스] 인증메일</h1>";
        certificationMessage += "<h3 style='text-align: center;'>인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>" + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

    private String getSupportEmailContent(String userId, String userEmail, String serverInformation, String content) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<h1 style='text-align: center;'>[게임 호스팅 서비스] 고객 지원 요청</h1>");
        emailContent.append("<p><strong>사용자 ID:</strong> ").append(userId).append("</p>");
        emailContent.append("<p><strong>사용자 이메일:</strong> ").append(userEmail).append("</p>");
        emailContent.append("<h2>서버 정보:</h2>");
        emailContent.append("<pre>").append(serverInformation).append("</pre>");
        emailContent.append("<h2>문의 내용:</h2>");
        emailContent.append("<p>").append(content).append("</p>");
        return emailContent.toString();
    }
}
