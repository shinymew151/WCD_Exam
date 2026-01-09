package com.player.dao;

import com.player.entity.Player;
import com.player.entity.Indexer;
import com.player.entity.PlayerIndex;

import javax.persistence.*;
import java.util.List;

public class PlayerDAO {
    
    private EntityManagerFactory emf;
    
    public PlayerDAO() {
        emf = Persistence.createEntityManagerFactory("PlayerPU");
    }
    
    // Create a new player
    public void create(Player player) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    // Find all players
    public List<Player> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p", Player.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Find player by ID
    public Player findById(Integer playerId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Player.class, playerId);
        } finally {
            em.close();
        }
    }
    
    // Update existing player
    public void update(Player player) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(player);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    // Delete player by ID
    public void delete(Integer playerId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Player player = em.find(Player.class, playerId);
            if (player != null) {
                em.remove(player);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    // Find all indexers
    public List<Indexer> findAllIndexers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Indexer> query = em.createQuery("SELECT i FROM Indexer i", Indexer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Find indexer by ID
    public Indexer findIndexerById(Integer indexId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Indexer.class, indexId);
        } finally {
            em.close();
        }
    }
    
    // PlayerIndex methods
    public void createPlayerIndex(PlayerIndex playerIndex) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(playerIndex);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public List<PlayerIndex> findPlayerIndexesByPlayerId(Integer playerId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<PlayerIndex> query = em.createQuery(
                "SELECT pi FROM PlayerIndex pi WHERE pi.player.playerId = :playerId", 
                PlayerIndex.class);
            query.setParameter("playerId", playerId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<PlayerIndex> findAllPlayerIndexes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<PlayerIndex> query = em.createQuery("SELECT pi FROM PlayerIndex pi", PlayerIndex.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Close the EntityManagerFactory
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}