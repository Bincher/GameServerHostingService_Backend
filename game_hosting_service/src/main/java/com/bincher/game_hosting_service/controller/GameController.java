package com.bincher.game_hosting_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bincher.game_hosting_service.dto.request.game.PostGameRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PatchGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PatchAdminGameServerRequestDto;
import com.bincher.game_hosting_service.dto.response.game.DeleteGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetGameListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetUserServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchPaymentStatusResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchAdminGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameServerResponseDto;
import com.bincher.game_hosting_service.service.GameService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    
    private final GameService gameService;


    @PostMapping("")
    public ResponseEntity<? super PostGameResponseDto> postGame(
        @RequestBody @Valid PostGameRequestDto requestBody
    ){
        ResponseEntity<? super PostGameResponseDto> response = gameService.postGame(requestBody);
        return response;
    }
    
    @GetMapping("/game-list")
    public ResponseEntity<? super GetGameListResponseDto> getGameList(){
        ResponseEntity<? super GetGameListResponseDto> response = gameService.getGameList();
        return response;
    }

    @PostMapping("/server")
    public ResponseEntity<? super PostGameServerResponseDto> postGameServer(
        @RequestBody @Valid PostGameServerRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PostGameServerResponseDto> response = gameService.postGameServer(requestBody, userId);
        return response;
    }

    @GetMapping("/user-server-list/{id}")
    public ResponseEntity<? super GetUserServerListResponseDto> getUserGameServerList(
        @PathVariable("id") String id
    ){
        ResponseEntity<? super GetUserServerListResponseDto> response = gameService.getUserServerList(id);
        return response;
    }

    @PatchMapping("/server/{id}")
    public ResponseEntity<? super PatchGameServerResponseDto> patchServer(
        @RequestBody @Valid PatchGameServerRequestDto requestBody,
        @PathVariable("id") Integer id,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PatchGameServerResponseDto> response = gameService.patchGameServer(requestBody, id, userId);
        return response;
    }

    @DeleteMapping("/server/{id}")
    public ResponseEntity<? super DeleteGameServerResponseDto> deleteServer(
        @PathVariable("id") Integer id,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super DeleteGameServerResponseDto> response = gameService.deleteGameServer(id, userId);
        return response; 
    }

    @GetMapping("/admin/server-list")
    public ResponseEntity<? super GetServerListResponseDto> getServerList(
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetServerListResponseDto> response = gameService.getServerList();
        return response;
    }

    @PatchMapping("/admin/server/{id}")
    public ResponseEntity<? super PatchAdminGameServerResponseDto> patchAdminServer(
        @RequestBody @Valid PatchAdminGameServerRequestDto requestBody,
        @PathVariable("id") Integer id,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PatchAdminGameServerResponseDto> response = gameService.patchAdminGameServer(requestBody, id);
        return response;
    }

    @PatchMapping("/server/payment/{id}")
    public ResponseEntity<? super PatchPaymentStatusResponseDto> patchPaymentStatus(
        @PathVariable("id") Integer id,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PatchPaymentStatusResponseDto> response = gameService.patchPaymentStatus(id);
        return response;
    }
}
