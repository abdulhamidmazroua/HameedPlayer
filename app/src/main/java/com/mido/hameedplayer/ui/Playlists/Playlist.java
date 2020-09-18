package com.mido.hameedplayer.ui.Playlists;

import java.io.Serializable;

public class Playlist implements Serializable {
    private String title;
    private String description;
    private final int ImageResource;

    public Playlist(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        ImageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return ImageResource;
    }
}
