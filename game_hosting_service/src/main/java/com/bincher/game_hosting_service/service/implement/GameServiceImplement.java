package com.bincher.game_hosting_service.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.dto.request.game.PatchAdminGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PatchGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameServerRequestDto;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.dto.response.game.DeleteGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetGameListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.GetUserServerListResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchAdminGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchGameServerResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PatchPaymentStatusResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameResponseDto;
import com.bincher.game_hosting_service.dto.response.game.PostGameServerResponseDto;
import com.bincher.game_hosting_service.entity.GameEntity;
import com.bincher.game_hosting_service.entity.GameServerEntity;
import com.bincher.game_hosting_service.entity.GameServerListViewEntity;
import com.bincher.game_hosting_service.repository.GameRepository;
import com.bincher.game_hosting_service.repository.GameServerListViewRepository;
import com.bincher.game_hosting_service.repository.GameServerRepository;
import com.bincher.game_hosting_service.repository.UserRepository;
import com.bincher.game_hosting_service.service.GameService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImplement implements GameService{
    
    private final GameRepository gameRepository;
    private final GameServerRepository gameServerRepository;
    private final UserRepository userRepository;
    private final GameServerListViewRepository gameServerListViewRepository;

    public int getGameIdByTitle(String title) {
        return gameRepository.findByTitle(title)
                .map(GameEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with title: " + title));
    }

    @Override
    public ResponseEntity<? super PostGameResponseDto> postGame(PostGameRequestDto dto) {
        try {

            GameEntity gameEntity = new GameEntity(dto);

            gameRepository.save(gameEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return PostGameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetGameListResponseDto> getGameList() {
        List<GameEntity> gameEntities = new ArrayList<>();
        try{
            gameEntities = gameRepository.findByOrderByTitleDesc();
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetGameListResponseDto.success(gameEntities);
    }

    @Override
    public ResponseEntity<? super PostGameServerResponseDto> postGameServer(PostGameServerRequestDto dto, String userId) {
        try {
            int gameId = getGameIdByTitle(String.valueOf(dto.getGameTitle()));
            GameServerEntity gameServerEntity = new GameServerEntity(dto, userId, gameId);
            gameServerRepository.save(gameServerEntity);
        } catch (EntityNotFoundException e) {
            return ResponseDto.notExistedGame();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        } 

        return PostGameServerResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserServerListResponseDto> getUserServerList(String userId) {
        List<GameServerListViewEntity> gameServerListViewEntities = new ArrayList<>();

        try {

            boolean existedUser = userRepository.existsByUserId(userId);
            if(!existedUser) return GetUserServerListResponseDto.noExistUser();
            
            gameServerListViewEntities = gameServerListViewRepository.findByUserIdOrderByNameDesc(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserServerListResponseDto.success(gameServerListViewEntities);
    }

    @Override
    public ResponseEntity<? super PatchGameServerResponseDto> patchGameServer(PatchGameServerRequestDto dto,
            Integer serverId, String userId) {
                try{
                    GameServerEntity gameServerEntity = gameServerRepository.findById(serverId).orElse(null);
                    if(gameServerEntity == null) return PatchGameServerResponseDto.noExistGameServer();
        
                    boolean existedUser = userRepository.existsByUserId(userId);
                    if(!existedUser) return PatchGameServerResponseDto.noExistUser();
        
                    String serverOwner = gameServerEntity.getUserId();
                    boolean isOwner = serverOwner.equals(userId);
                    if (!isOwner) return PatchGameServerResponseDto.noPermission();
        
                    gameServerEntity.patchGameServer(dto);
                    gameServerRepository.save(gameServerEntity);
        
                }catch(Exception exception){
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }
        
                return PatchGameServerResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteGameServerResponseDto> deleteGameServer(Integer serverId, String userId) {
        
        try{
            boolean existedUser = userRepository.existsByUserId(userId);
            if(!existedUser) return DeleteGameServerResponseDto.noExistUser();

            GameServerEntity gameServerEntity = gameServerRepository.findById(serverId).orElse(null);
            if(gameServerEntity == null) return PatchGameServerResponseDto.noExistGameServer();

            String serverOwner = gameServerEntity.getUserId();
            boolean isOwner = serverOwner.equals(userId);
            if (!isOwner) return PatchGameServerResponseDto.noPermission();

            gameServerRepository.delete(gameServerEntity);
            
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteGameServerResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetServerListResponseDto> getServerList() {
        List<GameServerListViewEntity> gameServerListViewEntities = new ArrayList<>();

        try {
            
            gameServerListViewEntities = gameServerListViewRepository.OrderByNameDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetServerListResponseDto.success(gameServerListViewEntities);
    }

    @Override
    public ResponseEntity<? super PatchAdminGameServerResponseDto> patchAdminGameServer(
            PatchAdminGameServerRequestDto dto, Integer id) {
                try{
                    GameServerEntity gameServerEntity = gameServerRepository.findById(id).orElse(null);
                    if(gameServerEntity == null) return PatchGameServerResponseDto.noExistGameServer();
        
                    gameServerEntity.patchAdminGameServer(dto);
                    gameServerRepository.save(gameServerEntity);
        
                }catch(Exception exception){
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }
        
                return PatchAdminGameServerResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchPaymentStatusResponseDto> patchPaymentStatus(Integer id) {
        try{
            GameServerEntity gameServerEntity = gameServerRepository.findById(id).orElse(null);
            if(gameServerEntity == null) return PatchPaymentStatusResponseDto.noExistGameServer();

            gameServerEntity.patchPaymentStatus();
            gameServerRepository.save(gameServerEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchPaymentStatusResponseDto.success();
    }
}
