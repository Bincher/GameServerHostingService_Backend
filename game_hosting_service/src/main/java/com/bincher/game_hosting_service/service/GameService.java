package com.bincher.game_hosting_service.service;

import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.dto.request.game.PatchGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PatchAdminGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameServerRequestDto;
import com.bincher.game_hosting_service.dto.response.game.DeleteGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetGameListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetUserServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchPaymentStatusResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchAdminGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameServerResponseDto;

public interface GameService {
    ResponseEntity<? super PostGameResponseDto> postGame(PostGameRequestDto dto);
    ResponseEntity<? super PostGameServerResponseDto> postGameServer(PostGameServerRequestDto dto, String userId);
    ResponseEntity<? super GetGameListResponseDto> getGameList(); 
    ResponseEntity<? super GetUserServerListResponseDto> getUserServerList(String userId);
    ResponseEntity<? super PatchGameServerResponseDto> patchGameServer(PatchGameServerRequestDto dto, Integer id, String userId);
    ResponseEntity<? super DeleteGameServerResponseDto> deleteGameServer(Integer id, String userId);
    ResponseEntity<? super GetServerListResponseDto> getServerList();
    ResponseEntity<? super PatchAdminGameServerResponseDto> patchAdminGameServer(PatchAdminGameServerRequestDto dto, Integer id);
    ResponseEntity<? super PatchPaymentStatusResponseDto> patchPaymentStatus(Integer id);
}
