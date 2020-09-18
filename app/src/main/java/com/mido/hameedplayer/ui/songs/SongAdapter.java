package com.mido.hameedplayer.ui.songs;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mido.hameedplayer.R;

import java.io.IOException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private ArrayList<Song> songs;
    private Context context;
    private int counter = 0;
    private TypedArray songsImageResources;
    MediaPlayer mediaPlayer;
    public SongAdapter(ArrayList<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
        songsImageResources = context.getResources().obtainTypedArray(R.array.songs_images);
    }

    @NonNull
    @Override
    public SongAdapter.SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song currentSong = songs.get(position);
        if  (counter > 6)
            counter = 0;
        int currentImage = songsImageResources.getResourceId(counter, 0);
        counter++;
        holder.bindTo(currentSong, currentImage);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView songsName;
        private ImageView songsImage;
        private int counter = 0;
        public SongHolder(@NonNull View itemView) {
            super(itemView);
            this.songsName = itemView.findViewById(R.id.song_title);
            this.songsImage = itemView.findViewById(R.id.random_music_pic);
            itemView.setOnClickListener(this);
        }
        void bindTo(Song currentSong, int imageId) {
            songsName.setText(currentSong.getTitle());
            Glide.with(context).load(imageId).into(songsImage);
        }

        @Override
        public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(context, songs.get(getLayoutPosition()).getUri());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    mediaPlayer = MediaPlayer.create(context, songs.get(getLayoutPosition()).getUri());
                }
            mediaPlayer.start();
        }
    }
}
