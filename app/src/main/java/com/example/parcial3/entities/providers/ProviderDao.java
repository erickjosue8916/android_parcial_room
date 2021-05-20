package com.example.parcial3.entities.providers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProviderDao {
    @Insert
    public long insert(Provider provider);

    @Query("SELECT * FROM providers")
    List<Provider> getAll();

    @Query("SELECT * FROM providers WHERE id = :id")
    Provider getById(int id);

    @Update
    public void update(Provider... providers);

    @Delete void delete(Provider... providers);
}
