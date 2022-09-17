package com.example.newsbulletin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsListAdapter mNewsListAdapter;
    //"https://newsapi.org/v2/top-headlines?country=in&category=";
    String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=2c80b95cd2384627adc499fe8bb7d586";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //LinearLayoutManager used for displaying the data items in a horizontal or vertical scrolling List
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        mNewsListAdapter = new NewsListAdapter(MainActivity.this);

        recyclerView.setAdapter(mNewsListAdapter); // set the Adapter to RecyclerView


        //API = 2c80b95cd2384627adc499fe8bb7d586
    }
    private void fetchData(){

        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //jsonRootObject = null;
                        try {
                            // Create a newsArray using News arraylist
                            ArrayList newsArray = new ArrayList<News>();

                            // Initializing the JSON object and extracting the information
                            JSONArray newsJsonArray = response.getJSONArray("articles");
                            //JSONObject jsonRootObject = new JSONObject(strJson);
                            //JSONArray newsJsonArray = jsonRootObject.getJSONArray("articles");
                            for (int i = 0; i < newsJsonArray.length(); i++) {
                                JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                                News news = new News(newsJsonObject.getString("title"),
                                        newsJsonObject.getString("author"),
                                        newsJsonObject.getString("url"),
                                        newsJsonObject.getString("urlToImage"));
                                newsArray.add(news);
                            }
                            mNewsListAdapter.updateNews(newsArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                })

                {
                    @Override
                    public Map getHeaders() throws AuthFailureError
                    {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        headers.put("User-agent", "Mozilla/5.0");
                        headers.put("apiKey", "2c80b95cd2384627adc499fe8bb7d586");
                        return headers;
                    }
                };

        //requestQueue.add(jsonObjectRequest);
        // Add StringRequest to the RequestQueue
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}