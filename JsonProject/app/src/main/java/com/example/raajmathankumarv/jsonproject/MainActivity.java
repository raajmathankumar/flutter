package com.example.raajmathankumarv.jsonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.reflect.Array.getInt;

public class MainActivity extends AppCompatActivity {

    TextView mreadingView;
   // TextView mcount;
    TextView mappRestart;
    private  int howManyTimeRuns=0;
    private int count=0;
    private static final String NUMBER_OF_TIMES_RUN_KEY="NUMBER_OF_TIMES_RUN_KEY";
    private static final String DATA_FILE="my_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mcount=(TextView)findViewById(R.id.count);

        mreadingView =(TextView) findViewById(R.id.readingView);
        mappRestart=(TextView) findViewById(R.id.appRestart);

        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);

        count=1;
       // mcount.setText(String.valueOf(count));
        //read
        int defaultValue=0;

        howManyTimeRuns=sharedPreferences.getInt(NUMBER_OF_TIMES_RUN_KEY,defaultValue);

        if(howManyTimeRuns==0){
            Toast.makeText(this,"welcome",Toast.LENGTH_LONG).show();

        }
        howManyTimeRuns++;

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(NUMBER_OF_TIMES_RUN_KEY,howManyTimeRuns);
        editor.commit();

        mappRestart.setText(String.valueOf(howManyTimeRuns));

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTextField(mreadingView.getText().toString());
    }


    @Override
    protected void onResume() {
        super.onResume();
        mreadingView.setText(getTextfield());
    }
    private void saveTextField(String content) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=openFileOutput(DATA_FILE,Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());


        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getTextfield() {
        FileInputStream fileInputStream=null;
        String fileData=null;
        try {
            fileInputStream=openFileInput(DATA_FILE);
            int size=fileInputStream.available();
            byte[] buffer=new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            fileData =new String(buffer,"UTF-8");

        } catch (FileNotFoundException e) {
            Log.e("FILE","Could not find");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("FILE","File id error");
            e.printStackTrace();
        }finally {
            try {
                if (fileInputStream!=null){
                    fileInputStream.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }
return fileData;
    }
}