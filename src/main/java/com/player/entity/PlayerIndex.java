package com.player.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_index")
public class PlayerIndex implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    
    @ManyToOne
    @JoinColumn(name = "index_id", nullable = false)
    private Indexer indexer;
    
    @Column(name = "value", nullable = false)
    private Float value;
    
    public PlayerIndex() {}
    
    public PlayerIndex(Player player, Indexer indexer, Float value) {
        this.player = player;
        this.indexer = indexer;
        this.value = value;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Indexer getIndexer() {
        return indexer;
    }
    
    public void setIndexer(Indexer indexer) {
        this.indexer = indexer;
    }
    
    public Float getValue() {
        return value;
    }
    
    public void setValue(Float value) {
        this.value = value;
    }
}