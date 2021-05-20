package com.example.parcial3.entities.products;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    public long insert(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAll();

    @Update()
    public void update(Product... products);

    @Delete()
    public void delete(Product... products);
}
