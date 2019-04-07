package com.example.wydarzenia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wydarzenia.EventEntryActivity;
import com.example.wydarzenia.R;
import com.example.wydarzenia.model.Event;

import java.util.List;

public class RetrofitRecyclerAdapterForAllEvents extends RecyclerView.Adapter<RetrofitRecyclerAdapterForAllEvents.CustomViewHolder>{
    private List<Event> dataList;
    private Context context;

    public RetrofitRecyclerAdapterForAllEvents(Context context, List<Event> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public int mCurrentPosition;
        TextView mTitle;
        TextView mDescription;
        TextView mLocation;
        TextView mDate;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mTitle = mView.findViewById(R.id.text_title_ae);
            mDescription = mView.findViewById(R.id.text_description_ae);
            mLocation = mView.findViewById(R.id.text_location_ae);
            mDate = mView.findViewById(R.id.text_date_ae);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventEntryActivity.class);
                    intent.putExtra("eid", String.valueOf(dataList.get(mCurrentPosition).getId()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_all_events_list, parent, false);
        return new CustomViewHolder(view);


    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mTitle.setText(dataList.get(position).getTitle());
        holder.mDescription.setText(dataList.get(position).getDescription());
        holder.mLocation.setText(dataList.get(position).getLocation());
        holder.mDate.setText(dataList.get(position).getDate());
        holder.mCurrentPosition = position;






//        holder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, NoteActivity.class);
//                intent.putExtra(NoteActivity.NOTE_POSITION, mCurrentPosition);
//                mContext.startActivity(intent);
//            }
//        });

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(dataList.get(position).getThumbnailUrl())
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.coverImage);

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
