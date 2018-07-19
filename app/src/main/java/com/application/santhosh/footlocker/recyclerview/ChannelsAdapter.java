package com.application.santhosh.footlocker.recyclerview;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.application.santhosh.footlocker.ChannelActivity;
import com.application.santhosh.footlocker.R;
import com.application.santhosh.footlocker.model.Channel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santhosh Pendem on 12/1/2017.
 */

public class ChannelsAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable{


    private List<Channel> channelList;
    private List<Channel> filteredChannelList;
    private Context context;
    private MyItemClickListener myItemClickListener;

    public ChannelsAdapter(List<Channel> channelList, Context context, MyItemClickListener myItemClickListener) {
        this.channelList = channelList;
        this.context = context;
        this.myItemClickListener = myItemClickListener;
        this.filteredChannelList = channelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Channel channel = filteredChannelList.get(position);
        holder.title.setText(channel.getTitle());
        holder.dj.setText(channel.getDj());
        holder.description.setText(channel.getDescription());

        Glide.with(context)
                .load(channel.getImage())
                .into(holder.channel_image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChannelActivity.class);
                i.putExtra("channel", channel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredChannelList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredChannelList = channelList;
                }else{
                    List<Channel> filteredList = new ArrayList<>();
                    for(Channel channel : channelList){
                        if (channel.getDj().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(channel);
                        }
                    }

                    filteredChannelList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredChannelList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredChannelList = (ArrayList<Channel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
