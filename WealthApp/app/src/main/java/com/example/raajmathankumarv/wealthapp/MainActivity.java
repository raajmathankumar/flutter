package com.example.raajmathankumarv.wealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextView mPlace,mtvDescription,mtvWind,mtvTemperature,mtvtemp,mtvwind,mtvweather;
EditText metLocation;
Button mbtTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlace =(TextView)findViewById(R.id.tvPlace);
        mbtTemperature=(Button) findViewById(R.id.btTemperature);
        metLocation=(EditText) findViewById(R.id.etLocation);

        mtvtemp = findViewById(R.id.tvtemp);
        mtvwind= findViewById(R.id.tvwind);
       mtvweather=findViewById(R.id.tvweather);


mbtTemperature.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String value = metLocation.getText().toString();

        fetchInstrumentOtdrNumber(value);

        mPlace.setText(value);
       // metLocation.getText().clear();

    }

});

    }
    public void fetchInstrumentOtdrNumber(String value){
//String APPID="f0ac350aee1cba2a691985511b04a145";
        //String API="https://goweather.herokuapp.com/weather/"+value;
        RequestQueue queue = Volley.newRequestQueue(this);

        String API="https://api.openweathermap.org/data/2.5/weather?q="+value+"&APPID=f0ac350aee1cba2a691985511b04a145";

       //RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, API, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("FIBER", "jsonOb--jectRequest : \n" + response.toString());

                        try {
                            JSONObject json= response.getJSONObject("wind");//your json response
                            String wind = json.getString("speed");

                            String Wind="Wind :"+wind;
                            mtvwind.setText(Wind);

                            JSONObject jon= response.getJSONObject("main");//your json response
                            String temp = jon.getString("temp");
                            String temperature="Temperature :"+temp;
                            mtvtemp.setText(temperature);

                            JSONArray weatherArray=response.getJSONArray("weather");

                            for(int i=0; i < weatherArray.length(); i++){
                                JSONObject jsonObject = weatherArray.getJSONObject(i);
                                //int id = Integer.parseInt(jsonObject.optString("id").toString());
                                String name = jsonObject.optString("description").toString();
                                String weathername="Weather :"+name;
                                mtvweather.setText(weathername);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
    }
}