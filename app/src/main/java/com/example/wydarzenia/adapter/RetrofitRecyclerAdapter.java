package com.example.wydarzenia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wydarzenia.R;
import com.example.wydarzenia.model.Event;

import java.util.List;

public class RetrofitRecyclerAdapter extends RecyclerView.Adapter<RetrofitRecyclerAdapter.CustomViewHolder>{
    private List<Event> dataList;
    private Context context;

    public RetrofitRecyclerAdapter(Context context, List<Event> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView mTitle;
        TextView mDescription;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mTitle = mView.findViewById(R.id.text_title);
            mDescription = mView.findViewById(R.id.text_description);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_event_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mTitle.setText(dataList.get(position).getTitle());
        holder.mDescription.setText(dataList.get(position).getContent());

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
