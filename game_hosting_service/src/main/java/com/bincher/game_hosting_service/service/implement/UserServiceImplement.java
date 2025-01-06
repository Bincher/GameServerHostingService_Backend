package com.bincher.game_hosting_service.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.common.CertificationNumber;
import com.bincher.game_hosting_service.dto.request.notification.PatchFcmTokenRequestDto;
import com.bincher.game_hosting_service.dto.request.user.CheckCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.EmailCertificationForChangeRequestDto;
import com.bincher.game_hosting_service.dto.request.user.IsPasswordRightRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchEmailRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchPasswordRequestDto;
import com.bincher.game_hosting_service.dto.request.user.PatchProfileImageRequestDto;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.CheckCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.notification.PatchFcmTokenResponseDto;
import com.bincher.game_hosting_service.dto.response.user.CheckCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.EmailCertificationForChangeResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetSignInUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetUserResponseDto;
import com.bincher.game_hosting_service.dto.response.user.IsPasswordRightResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchEmailResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchPasswordResponseDto;
import com.bincher.game_hosting_service.dto.response.user.PatchProfileImageResponseDto;
import com.bincher.game_hosting_service.entity.CertificationEntity;
import com.bincher.game_hosting_service.entity.UserEntity;
import com.bincher.game_hosting_service.provider.EmailProvider;
import com.bincher.game_hosting_service.repository.CertificationRepository;
import com.bincher.game_hosting_service.repository.UserRepository;
import com.bincher.game_hosting_service.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final CertificationRepository certificationRepository;
    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String id) {
        
        UserEntity userEntity = null;

        try{
            userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return GetUserResponseDto.noExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id){
        
        UserEntity userEntity = null;

        try{

            userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return GetSignInUserResponseDto.notExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @SuppressWarnings("null")
    @Override
    public ResponseEntity<? super PatchFcmTokenResponseDto> patchFcmToken(PatchFcmTokenRequestDto dto, String id) {
        try{

            UserEntity userEntity = userRepository.findByUserId(id);
            if(userEntity == null) PatchFcmTokenResponseDto.noExistUser();

            String fcmToken = dto.getFcmToken();

            userEntity.setFcmToken(fcmToken);
            userRepository.save(userEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }


        return PatchFcmTokenResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String id) {
        try {
            System.out.println(id);
            UserEntity userEntity = userRepository.findByUserId(id);

            if(userEntity == null) return PatchProfileImageResponseDto.noExistUser();
            

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return PatchProfileImageResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchEmailResponseDto> patchEmail(PatchEmailRequestDto dto, String id) {
        try {
            UserEntity userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return PatchProfileImageResponseDto.noExistUser();

            String email = dto.getEmail();
            userEntity.setEmail(email);
            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchEmailResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(PatchPasswordRequestDto dto, String id) {
        try{
            UserEntity userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return PatchProfileImageResponseDto.noExistUser();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            userEntity.setPassword(encodedPassword);

            userRepository.save(userEntity);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchPasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super IsPasswordRightResponseDto> isPasswordRight(IsPasswordRightRequestDto dto, String id) {
        try{
            UserEntity userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return IsPasswordRightResponseDto.noExistUser();

            String password = dto.getPassword();

            boolean isMatched = passwordEncoder.matches(password, userEntity.getPassword());
            if(!isMatched) return IsPasswordRightResponseDto.passwordFail();
            
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IsPasswordRightResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationForChangeResponseDto> emailCertificationForChange(EmailCertificationForChangeRequestDto dto, String id) {
        
        try {
            String email = dto.getEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessed) return EmailCertificationForChangeResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(id, email, certificationNumber);
            certificationRepository.save(certificationEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return EmailCertificationForChangeResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationForChangeResponseDto> checkCertificationForChange(CheckCertificationForChangeRequestDto dto, String id) {
        try {
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(id);
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationForChangeResponseDto.success();
    }


}
