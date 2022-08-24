package com.example.newsbulletin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    // ArrayList for person names
    ArrayList list = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //LinearLayoutManager used for displaying the data items in a horizontal or vertical scrolling List
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        NewsListAdapter newsListAdapter = new NewsListAdapter(MainActivity.this, list);
        recyclerView.setAdapter(newsListAdapter); // set the Adapter to RecyclerView
    }
}