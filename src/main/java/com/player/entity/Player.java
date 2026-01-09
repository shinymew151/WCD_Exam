package com.player.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player")
public class Player implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;
    
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    
    @Column(name = "full_name", nullable = false, length = 128)
    private String fullName;
    
    @Column(name = "age", nullable = false, length = 10)
    private String age;
    
    @ManyToOne
    @JoinColumn(name = "index_id", nullable = false)
    private Indexer indexer;
    
    public Player() {}
    
    public Player(String name, String fullName, String age, Indexer indexer) {
        this.name = name;
        this.fullName = fullName;
        this.age = age;
        this.indexer = indexer;
    }
    
    // Getters and Setters
    public Integer getPlayerId() {
        return playerId;
    }
    
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public Indexer getIndexer() {
        return indexer;
    }
    
    public void setIndexer(Indexer indexer) {
        this.indexer = indexer;
    }
}