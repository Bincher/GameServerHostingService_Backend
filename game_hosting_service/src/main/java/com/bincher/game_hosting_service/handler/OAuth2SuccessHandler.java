package com.bincher.game_hosting_service.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bincher.game_hosting_service.common.ServerAddress;
import com.bincher.game_hosting_service.entity.CustomOAuth2User;
import com.bincher.game_hosting_service.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    private final JwtProvider jwtProvider;

    @Override
	public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
		Authentication authentication
        ) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String userId = oAuth2User.getName();
        String token = jwtProvider.create(userId);
        
        response.sendRedirect("http://"+ServerAddress.LOCALSERVERADDRESS+"/auth/oauth-response/"+token+"/3600");
	}
}
