package com.example.parcial3.entities.providers;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "providers")
public class Provider {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
}
