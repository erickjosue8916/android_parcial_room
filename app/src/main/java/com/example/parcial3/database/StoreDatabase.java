package com.example.parcial3.database;

import android.content.Context;

import androidx.room.Room;

public class StoreDatabase {
    private final String databaseName = "store";
    private static StoreDatabase instance;
    public DB db;

    public static StoreDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new StoreDatabase(context);
        }
        return instance;
    }

    private StoreDatabase (Context context) {
        this.db = Room.databaseBuilder(context, DB.class, this.databaseName)
                .allowMainThreadQueries()
                .build();
    }
}
