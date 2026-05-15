package com.example.matrusnehhealth.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KickDao {

    @Insert
    void insert(KickData kickData);

    // Get all kick data for History
    @Query("SELECT * FROM KickData")
    List<KickData> getAllData();

    // Get all kicks for Statistics
    @Query("SELECT * FROM KickData")
    List<KickData> getAllKicks();

    // Delete all history
    @Query("DELETE FROM KickData")
    void deleteAll();
}