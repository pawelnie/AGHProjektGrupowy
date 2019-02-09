package com.example.wydarzenia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private ArrayList<EventData> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewContent;
        ImageView imageViewIcon;
        public View view;


        //note that additional variable is passed to constructor in order to handle clicks. This
        //is because it is a static class and we cannot access this directly.
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            this.textViewName = (TextView) itemView.findViewById(R.id.eventName);
            this.textViewContent = (TextView) itemView.findViewById(R.id.eventBrief);

        }
    }

    public CardAdapter (ArrayList<EventData> data){
        this.dataSet = data;
    }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_card, viewGroup, false);

        view.setOnClickListener(EventListActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder myViewHolder, int position) {

        //problems here!
        TextView textViewName = myViewHolder.textViewName;
        TextView textViewContent = myViewHolder.textViewContent;
//        ImageView imageView = myViewHolder.imageViewIcon;
//
        textViewName.setText(dataSet.get(position).getName());
        textViewContent.setText(dataSet.get(position).getBrief());
//        imageView.setImageResource(dataSet.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
