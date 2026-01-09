package com.player.service;

import com.player.dao.PlayerDAO;
import com.player.entity.Player;
import com.player.entity.Indexer;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PlayerService {

    private PlayerDAO playerDAO;
    
    public PlayerService() {
        this.playerDAO = new PlayerDAO();  // Manual instantiation
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public Player getPlayerById(Integer playerId) {
        return playerDAO.findById(playerId);
    }

    public void createPlayer(Player player) {
        playerDAO.create(player);
    }

    public void updatePlayer(Player player) {
        playerDAO.update(player);
    }

    public void deletePlayer(Integer playerId) {
        playerDAO.delete(playerId);
    }
    
    public List<Indexer> getAllIndexers() {
        return playerDAO.findAllIndexers();
    }
    
    public Indexer getIndexerById(Integer id) {
        return playerDAO.findIndexerById(id);
    }
}