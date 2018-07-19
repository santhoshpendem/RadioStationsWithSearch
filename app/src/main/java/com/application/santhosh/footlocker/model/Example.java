package com.application.santhosh.footlocker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

@SerializedName("channels")
@Expose
private List<Channel> channels = null;

public List<Channel> getChannels() {
return channels;
}

public void setChannels(List<Channel> channels) {
this.channels = channels;
}

}