package com.mido.hameedplayer.ui.songs;

import android.net.Uri;

public class Song {
    Uri uri;
    String title;
    int imageResourceId;

    public Song(Uri uri, String title, int imageResourceId) {
        this.uri = uri;
        this.title = title;
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public Uri getUri() {
        return uri;
    }
}
