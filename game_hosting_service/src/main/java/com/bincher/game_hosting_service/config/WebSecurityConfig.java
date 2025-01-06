package com.bincher.game_hosting_service.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bincher.game_hosting_service.filter.JwtAuthenticationFilter;
import com.bincher.game_hosting_service.handler.OAuth2SuccessHandler;
import com.bincher.game_hosting_service.common.ServerAddress;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DefaultOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity
            .cors(cors -> cors
                .configurationSource(corsConfigurationSource())
            )
            .csrf(CsrfConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/**", "/api/v1/auth/**","/oauth2/**", "api/v1/game/**","/api/v1/user/**", "file/**","api/v1/chat/**",
                "api/v1/message/**","api/v1/support/**","/api/v1/notification/**","/api/v1/payment/**","/swagger-ui/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/", "/api/v1/board/**","/api/v1/user/**","api/v1/chat/**",
                "api/v1/message/**","api/v1/support/**","/api/v1/notification/**","/api/v1/payment/**").permitAll()
                .requestMatchers("api/v1/user/**").hasRole("USER")
                .requestMatchers("api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2"))
                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                .successHandler(oAuth2SuccessHandler)
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(ServerAddress.CLOUDESERVERADDRESS);
        corsConfiguration.addAllowedOrigin(ServerAddress.LOCALSERVERADDRESS);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    } 
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException{
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"code\":\"NP\",\"message\":\"No Permission.\"}");
        }
}
