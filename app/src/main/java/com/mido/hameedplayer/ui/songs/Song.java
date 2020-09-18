package com.mido.hameedplayer.ui.songs;

import android.net.Uri;

public class Song {
    Uri uri;
    String Title;

    public Song(Uri uri, String title) {
        this.uri = uri;
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public Uri getUri() {
        return uri;
    }
}
