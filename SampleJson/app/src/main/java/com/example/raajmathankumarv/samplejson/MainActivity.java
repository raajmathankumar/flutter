package com.example.raajmathankumarv.samplejson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ListView mListView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s=getJSONFILE();
        String myDataArray[]={};

        try {
            JSONObject rootJSON=new JSONObject(s);
            JSONArray toppingJSON=rootJSON.getJSONArray("topping");
            myDataArray =new String[toppingJSON.length()];

            for(int i=0;i<toppingJSON.length();i++){
                JSONObject jsonObject=toppingJSON.getJSONObject(i);
                myDataArray[i]=jsonObject.getString("type");
            }


        }catch (JSONException e){
            e.printStackTrace();

        }
        mListView=(ListView)findViewById(R.id.myListView);
        ArrayAdapter<String> stringAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.row,myDataArray);
        if(mListView!=null){
            mListView.setAdapter(stringAdapter);
        }
    }

    private String getJSONFILE() {
        String json = null;
        try {
            // Opening data.json file

            InputStream inputStream = getAssets().open("sample.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;

        }
        return json;

    }


}