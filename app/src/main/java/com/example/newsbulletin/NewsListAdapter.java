package com.example.newsbulletin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private ArrayList<String> items;

    //RecyclerView needs an adapter to populate the views in each item/row with your data.

    // data is passed into the constructor
    public NewsListAdapter(MainActivity mainActivity, ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    //onCreateViewHolder in which we inflate the layout item xml into view
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        // set the view's size, margins, paddings and layout parameters
        NewsViewHolder nvh = new NewsViewHolder(view);
        return nvh;
    }

    @Override
    //onBindViewHolder in which we set the data in the view’s with the help of ViewHolder.
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String currentPosition = items.get(position);
        //Object currentPosition = items.get(position);
        // set the data in items
        holder.textView.setText(currentPosition);
    }

    @Override
    public int getItemCount() {
        //total number of rows
        return items.size();
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        // get the reference of item view's
        textView = (TextView) itemView.findViewById(R.id.title);
    }
}