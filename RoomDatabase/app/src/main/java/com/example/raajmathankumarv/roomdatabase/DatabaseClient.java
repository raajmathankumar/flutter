package com.example.raajmathankumarv.roomdatabase;

import android.content.Context;

import androidx.room.Room;

import static com.example.raajmathankumarv.roomdatabase.Constants.DB_NAME;


class DatabaseClient {
    private static DatabaseClient mInstance;

    //our app database object
    private final AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, DB_NAME)
                .build();
    }
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
