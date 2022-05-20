package com.example.vehiclepool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
Button register;
Button login;
EditText username ;
EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=findViewById(R.id.button2);
        login = findViewById(R.id.submitlog);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),register.class);
            startActivity(intent);

        }
    });

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"http://192.168.0.118:9000/api/login?Username="+username.getText().toString()+"&Password="+password.getText().toString(),null,new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response){
                    try {
                        if(response.getString("value").equals("yes")){
                            SharedPreferences sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit= sharedPreferences.edit();
                            myEdit.putString("UserName",username.getText().toString());
                            myEdit.commit();
                            Log.d("loging", "onResponse: not done ");
                            Intent intent=new Intent(getApplicationContext(),Landing.class);
                          startActivity(intent);
                        }
                        else{
                            Log.d("loging", "onResponse: not doneee ");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("loging", error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    });

    }
}