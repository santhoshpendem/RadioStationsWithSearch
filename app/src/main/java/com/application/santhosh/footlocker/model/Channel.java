package com.application.santhosh.footlocker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Channel implements Parcelable {


    private String id;

    private String title;

    private String description;

    private String dj;

    private String djmail;

    private String genre;

    private String image;

    private String largeimage;

    private String xlimage;

    private String twitter;

    private String updated;

    private List<Playlist> playlists = null;

    private String listeners;

    private String lastPlaying;

    public Channel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        dj = in.readString();
        djmail = in.readString();
        genre = in.readString();
        image = in.readString();
        largeimage = in.readString();
        xlimage = in.readString();
        twitter = in.readString();
        updated = in.readString();
        playlists = in.createTypedArrayList(Playlist.CREATOR);
        listeners = in.readString();
        lastPlaying = in.readString();
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getDjmail() {
        return djmail;
    }

    public void setDjmail(String djmail) {
        this.djmail = djmail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLargeimage() {
        return largeimage;
    }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
    }

    public String getXlimage() {
        return xlimage;
    }

    public void setXlimage(String xlimage) {
        this.xlimage = xlimage;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getLastPlaying() {
        return lastPlaying;
    }

    public void setLastPlaying(String lastPlaying) {
        this.lastPlaying = lastPlaying;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(dj);
        parcel.writeString(djmail);
        parcel.writeString(genre);
        parcel.writeString(image);
        parcel.writeString(largeimage);
        parcel.writeString(xlimage);
        parcel.writeString(twitter);
        parcel.writeString(updated);
        parcel.writeList(playlists);
        parcel.writeString(listeners);
        parcel.writeString(lastPlaying);

    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dj='" + dj + '\'' +
                ", djmail='" + djmail + '\'' +
                ", genre='" + genre + '\'' +
                ", image='" + image + '\'' +
                ", largeimage='" + largeimage + '\'' +
                ", xlimage='" + xlimage + '\'' +
                ", twitter='" + twitter + '\'' +
                ", updated='" + updated + '\'' +
                ", playlists=" + playlists +
                ", listeners='" + listeners + '\'' +
                ", lastPlaying='" + lastPlaying + '\'' +
                '}';
    }
}