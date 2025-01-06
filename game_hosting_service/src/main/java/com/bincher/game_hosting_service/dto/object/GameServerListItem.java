package com.bincher.game_hosting_service.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.bincher.game_hosting_service.entity.GameServerListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameServerListItem {
    private int id;
    private String name;
    private String content;
    private String location;
    private String performance;
    private String disk;
    private Boolean backup;
    private String billingAmount;
    private String requestDetails;
    private int modeCount;
    private String gameTitle;
    private String gameImage;
    private String status;
    private String serverAddress;
    private String amountLevel;
    private String userId;
    private boolean paymentStatus;

    public GameServerListItem(GameServerListViewEntity gameServerListViewEntity){
        this.id = gameServerListViewEntity.getServerId();
        this.name = gameServerListViewEntity.getName();
        this.content = gameServerListViewEntity.getContent();
        this.location = gameServerListViewEntity.getLocation(); // 수정됨
        this.performance = gameServerListViewEntity.getPerformance(); // 수정됨
        this.disk = gameServerListViewEntity.getDisk();
        this.backup = gameServerListViewEntity.getBackup(); // boolean으로 가정
        this.billingAmount = gameServerListViewEntity.getBillingAmount(); // 수정됨
        this.requestDetails = gameServerListViewEntity.getRequestDetails();
        this.modeCount = gameServerListViewEntity.getModeCount();
        this.gameTitle = gameServerListViewEntity.getGameTitle();
        this.gameImage = gameServerListViewEntity.getGameImage();
        this.status = gameServerListViewEntity.getStatus();
        this.serverAddress = gameServerListViewEntity.getServerAddress();
        this.amountLevel = gameServerListViewEntity.getAmountLevel();
        this.userId = gameServerListViewEntity.getUserId();
        this.paymentStatus = gameServerListViewEntity.isPaymentStatus();
    }

    public static List<GameServerListItem> getList(List<GameServerListViewEntity> gameServerListViewEntities){
        List<GameServerListItem> list = new ArrayList<>();
    
        for(GameServerListViewEntity boardListViewEntity: gameServerListViewEntities){
            GameServerListItem boardListItem = new GameServerListItem(boardListViewEntity);
            list.add(boardListItem);
        }
        return list;
    }
}
