package com.example.expensify.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InfoDAO {
    @Insert
    void insert(Info info);

    @Query("SELECT * FROM Info")
    List<Info> getAllInfo();

    @Query("Select * from Info where id = :id")
    Info getInfoById(int id);

    @Delete
    void delete(Info info);
}
