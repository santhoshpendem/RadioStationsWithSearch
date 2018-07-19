package com.application.santhosh.footlocker.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.santhosh.footlocker.R;

/**
 * Created by Santhosh Pendem on 12/6/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
	
	TextView title,dj,description;
	ImageView channel_image;
	LinearLayout linearLayout;
	private MyItemClickListener myItemClickListener;
	
	public MyViewHolder(View itemView) {
		super(itemView);
		channel_image = itemView.findViewById(R.id.channel_image) ;
		title = itemView.findViewById(R.id.title);
		dj = itemView.findViewById(R.id.dj);
		description = itemView.findViewById(R.id.description);
		linearLayout = itemView.findViewById(R.id.channel_card_layout);
	}
	
	@Override
	public void onClick(View v) {
		this.myItemClickListener.onItemClick(v,getPosition());
	}
	
	public void setItemClickListener(MyItemClickListener ic){
		this.myItemClickListener = ic;
	}
	
}
