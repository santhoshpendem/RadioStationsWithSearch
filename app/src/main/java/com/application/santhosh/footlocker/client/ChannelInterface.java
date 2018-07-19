package com.application.santhosh.footlocker.client;

import com.application.santhosh.footlocker.model.Example;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Santhosh Pendem on 12/6/2017.
 */

public interface ChannelInterface {
    @GET("jvanaria/jvanaria.github.io/master/channels.json")
    Observable<Example> fetchChannels();
}



