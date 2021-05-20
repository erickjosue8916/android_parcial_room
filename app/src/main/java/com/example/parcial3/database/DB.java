package com.example.parcial3.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.parcial3.entities.categories.Category;
import com.example.parcial3.entities.categories.CategoryDao;
import com.example.parcial3.entities.products.Product;
import com.example.parcial3.entities.products.ProductDao;
import com.example.parcial3.entities.providers.Provider;
import com.example.parcial3.entities.providers.ProviderDao;

@Database(entities = {Category.class, Product.class, Provider.class}, version = 2, exportSchema = true)
public abstract class DB extends RoomDatabase{
    public abstract CategoryDao categories();
    public abstract ProductDao products();
    public abstract ProviderDao provider();
}
