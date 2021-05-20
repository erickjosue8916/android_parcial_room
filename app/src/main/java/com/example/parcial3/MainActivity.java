package com.example.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.parcial3.database.StoreDatabase;

public class MainActivity extends AppCompatActivity {
    StoreDatabase store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = StoreDatabase.getInstance(this);
    }
}