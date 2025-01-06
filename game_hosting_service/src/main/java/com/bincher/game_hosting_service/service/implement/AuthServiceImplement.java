package com.bincher.game_hosting_service.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.common.CertificationNumber;
import com.bincher.game_hosting_service.dto.request.auth.CheckCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.EmailCertificationRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.IdCheckRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignInRequestDto;
import com.bincher.game_hosting_service.dto.request.auth.SignUpRequestDto;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.CheckCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.DeleteUserResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.EmailCertificationResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.IdCheckResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.SignInResponseDto;
import com.bincher.game_hosting_service.dto.response.auth.SignUpResponseDto;
import com.bincher.game_hosting_service.entity.CertificationEntity;
import com.bincher.game_hosting_service.entity.UserEntity;
import com.bincher.game_hosting_service.provider.EmailProvider;
import com.bincher.game_hosting_service.provider.JwtProvider;
import com.bincher.game_hosting_service.repository.CertificationRepository;
import com.bincher.game_hosting_service.repository.GameServerRepository;
import com.bincher.game_hosting_service.repository.UserRepository;
import com.bincher.game_hosting_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{
    
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final GameServerRepository gameServerRepository;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super SignUpResponseDto> SignUp(SignUpRequestDto dto) {
        
        try{
            
            String userId = dto.getId();
            boolean existedId = userRepository.existsByUserId(userId);
            if(existedId) return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            String certificationNumber = dto.getCertificationNumber();
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched =
                certificationEntity.getEmail().equals(email) &&
                certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return SignUpResponseDto.certificationFail();

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            // certificationRepository.delete(certificationEntity);
            certificationRepository.deleteByUserId(userId);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> SignIn(SignInRequestDto dto) {
        
        String token = null;
        
        try{

            String id = dto.getId();
            UserEntity userEntity = userRepository.findByUserId(id);
            if(userEntity == null) return SignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(id);
            
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto){

        try{

            String userId = dto.getId();
            boolean isExistId = userRepository.existsById(userId);
            if(isExistId) return IdCheckResponseDto.duplicateId();
        
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId) {
        
        try{
            boolean isExistId = userRepository.existsByUserId(userId);
            if(!isExistId) return DeleteUserResponseDto.noExistUser();

            boolean isExistServerByUserId = gameServerRepository.existsByUserId(userId);
            if(isExistServerByUserId) return DeleteUserResponseDto.serverExisted();

            UserEntity userEntity = userRepository.findByUserId(userId);
            userRepository.delete(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteUserResponseDto.success();
    }
}
