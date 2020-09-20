package com.mido.hameedplayer.ui.songs;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mido.hameedplayer.R;

public class PlaybackFragment extends Fragment {
    private LinearLayout linearLayout;
    private SeekBar seekBar;
    private ImageButton playPreviousBtn;
    private ImageButton playBtn;
    private ImageButton playNextBtn;
    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_playback, container, false);
        linearLayout = root.findViewById(R.id.playback_layout);
        imageView = root.findViewById(R.id.image);
        seekBar = root.findViewById(R.id.song_seek_bar);
        playPreviousBtn = root.findViewById(R.id.play_previous);
        playBtn = root.findViewById(R.id.play);
        playNextBtn = root.findViewById(R.id.play_next);
        int imageId = getArguments().getInt("songImageId", 0);
        Glide.with(root.getContext())
                .load(imageId)
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                       linearLayout.setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        return root;
    }
}
