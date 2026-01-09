package com.player.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "indexer")
public class Indexer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private Integer indexId;
    
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    
    @Column(name = "valueMin", nullable = false)
    private Float valueMin;
    
    @Column(name = "valueMax", nullable = false)
    private Float valueMax;
    
    // Constructors
    public Indexer() {}
    
    public Indexer(String name, Float valueMin, Float valueMax) {
        this.name = name;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }
    
    // Getters and Setters
    public Integer getIndexId() {
        return indexId;
    }
    
    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Float getValueMin() {
        return valueMin;
    }
    
    public void setValueMin(Float valueMin) {
        this.valueMin = valueMin;
    }
    
    public Float getValueMax() {
        return valueMax;
    }
    
    public void setValueMax(Float valueMax) {
        this.valueMax = valueMax;
    }
    
    @Override
    public String toString() {
        return "Indexer{" +
                "indexId=" + indexId +
                ", name='" + name + '\'' +
                ", valueMin=" + valueMin +
                ", valueMax=" + valueMax +
                '}';
    }
}