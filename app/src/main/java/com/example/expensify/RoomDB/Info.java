package com.example.expensify.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Info {
    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "date")
    public String date;

    @ColumnInfo (name = "name")
    public String name;

    @ColumnInfo (name = "amount")
    public String amount;

    @ColumnInfo (name = "category")
    public String category;

    @ColumnInfo (name = "address")
    public String address;

    @ColumnInfo (name = "paid")
    public boolean paid;

}
