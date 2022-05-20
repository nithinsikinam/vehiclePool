package com.example.vehiclepool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OfferRide extends AppCompatActivity {
    EditText startLocation;
    EditText routeCheckpoints;
    EditText startTime;
    EditText seatsAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);
        startLocation = findViewById(R.id.startloc);
        routeCheckpoints = findViewById(R.id.checkpoints);
        startTime = findViewById(R.id.starttime);
        seatsAvailable = findViewById(R.id.seats);
        SharedPreferences sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String UserName=sharedPreferences.getString("UserName","");

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"http://192.168.0.118:9000/api/addOffer?StartLocation="+startLocation.getText().toString()+"&RouteCheckpoints="+routeCheckpoints.getText().toString()+"&StartingTime="+startTime.getText().toString()+"&SeatsAvailable="+seatsAvailable.getText().toString()+"&Username="+UserName,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){


            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("loging", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    }
