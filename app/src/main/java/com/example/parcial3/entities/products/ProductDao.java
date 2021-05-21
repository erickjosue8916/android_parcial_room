package com.example.parcial3.entities.products;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
    public List<Product> getAll();

    @Query("SELECT * FROM products WHERE id = :id")
    public Product getById(int id);

    @Query("SELECT prod.id as id, prod.name, prov.name as provider, cat.name as category FROM products as prod INNER JOIN providers as prov on prov.id = prod.providerId INNER JOIN categories as cat on prod.categoryId = cat.id")
    public List<ProductView> getProductView();

    @Update
    public void update(Product... products);

    @Delete
    public void delete(Product... products);

}
