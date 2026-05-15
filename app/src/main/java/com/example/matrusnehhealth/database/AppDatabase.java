package com.example.matrusnehhealth.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {KickData.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract KickDao kickDao();
}