package com.example.raajmathankumarv.roomdatabase;

import android.content.Context;
import android.content.Intent;

public class Constants {
    public static final String DB_NAME = "MyToDos";
    public static final String INTENT_TASK = "task";
    public static final String UPDATED="Updated";


    public static void goToUpdateTask(Context mCtx, Task task ) {
        Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
        intent.putExtra(Constants.INTENT_TASK, task);
        mCtx.startActivity(intent);
    }

}

