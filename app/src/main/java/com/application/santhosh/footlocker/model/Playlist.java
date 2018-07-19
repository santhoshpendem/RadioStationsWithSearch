package com.application.santhosh.footlocker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Playlist implements Parcelable{


    private String url;

    private String format;

    private String quality;

    public Playlist(Parcel in) {
        url = in.readString();
        format = in.readString();
        quality = in.readString();
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(format);
        parcel.writeString(quality);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "url='" + url + '\'' +
                ", format='" + format + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
}