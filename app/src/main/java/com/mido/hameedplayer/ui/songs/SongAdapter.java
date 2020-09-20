package com.mido.hameedplayer.ui.songs;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mido.hameedplayer.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private ArrayList<Song> songs;
    private Context context;
    MediaPlayer mediaPlayer;
    public SongAdapter(ArrayList<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
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
        holder.bindTo(currentSong);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView songsName;
        private ImageView songsImage;
        public SongHolder(@NonNull View itemView) {
            super(itemView);
            this.songsName = itemView.findViewById(R.id.song_title);
            this.songsImage = itemView.findViewById(R.id.random_music_pic);
            itemView.setOnClickListener(this);
        }
        void bindTo(Song currentSong) {
            songsName.setText(currentSong.getTitle());
            Glide.with(context).load(currentSong.getImageResourceId()).into(songsImage);
        }

        @Override
        public void onClick(View view) {
//                if (mediaPlayer != null) {
//                    mediaPlayer.reset();
//                    try {
//                        mediaPlayer.setDataSource(context, songs.get(getLayoutPosition()).getUri());
//                        mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    mediaPlayer = MediaPlayer.create(context, songs.get(getLayoutPosition()).getUri());
//                }
//            mediaPlayer.start();
            Uri uri = songs.get(getLayoutPosition()).getUri();
            String title =  songs.get(getLayoutPosition()).getTitle();
            int imageId = songs.get(getLayoutPosition()).getImageResourceId();
            Bundle bundle = new Bundle();
            bundle.putString("songUri", uri.toString());
            bundle.putString("songTitle", title);
            bundle.putInt("songImageId", imageId);
            Navigation.findNavController(itemView).navigate(R.id.action_nav_songs_to_nav_playback, bundle);
        }
    }
}
