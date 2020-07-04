package com.bidiptoroy.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
EditText artistName , songName;
Button searchbtn;
TextView lyrics;
@Override
protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = findViewById(R.id.artistName);
        songName = findViewById(R.id.songName);
        searchbtn = findViewById(R.id.searchLyrics);
        lyrics = findViewById(R.id.lyrics);

       searchbtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                       String url ="https://api.lyrics.ovh/v1/" + artistName.getText().toString() + "/" + songName.getText().toString();
                       url.replace(" ","%20");
                       RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                       JsonObjectRequest jasonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response)
                               {
                                       try{
                                               lyrics.setText(response.getString("lyrics"));
                                       }catch (JSONException e){
                                               e.getMessage();
                                       }

                               }
                       }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                       });
                       requestQueue.add(jasonObjectRequest);
               }
       });

        }


}