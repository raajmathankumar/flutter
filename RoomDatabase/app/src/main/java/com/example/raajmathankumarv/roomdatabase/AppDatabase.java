package com.example.raajmathankumarv.roomdatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database( version = 3,
        entities = {Task.class}
        )

public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}



