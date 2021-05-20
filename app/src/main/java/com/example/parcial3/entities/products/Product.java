package com.example.parcial3.entities.products;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public int categoryId;
    public int providerId;
}
