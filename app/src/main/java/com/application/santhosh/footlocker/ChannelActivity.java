package com.application.santhosh.footlocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.santhosh.footlocker.model.Channel;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class ChannelActivity extends AppCompatActivity {

    @BindView(R.id.channel_image)
    ImageView channelImage;

    @BindViews({R.id.title, R.id.dj, R.id.dj_email, R.id.listeners_count, R.id.genre})
    List<TextView> nameViews;

    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("channel")) {
            channel = getIntent().getParcelableExtra("channel");
        }
        populateViews();

    }


    private void populateViews() {
        Glide.with(this)
                .load(channel.getImage())
                .into(channelImage);
        nameViews.get(0).setText(channel.getTitle());
        nameViews.get(1).setText(channel.getDj());
        nameViews.get(2).setText(channel.getDjmail());
        nameViews.get(3).setText(channel.getListeners());
        nameViews.get(4).setText(channel.getGenre());
    }


}
