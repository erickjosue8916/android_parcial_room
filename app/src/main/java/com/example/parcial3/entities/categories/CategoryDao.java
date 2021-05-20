package com.example.parcial3.entities.categories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    public long insert(Category category);

    @Query("SELECT * FROM categories")
    public List<Category> getAll();

    @Query("SELECT * FROM categories WHERE id = :id")
    public Category getById(int id);

    @Update
    public void update(Category... categories);

    @Delete
    public void delete(Category categories);
}
