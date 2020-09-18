package com.mido.hameedplayer.ui.songs;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mido.hameedplayer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {
    private ArrayList<Song> songs = new ArrayList<>();
    private RecyclerView songRecycler;
    private SongAdapter songAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_songs, container, false);

        // Get the songs from the media content provider
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        try (Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null)){
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            long id;
            String title;
            while (cursor.moveToNext()) {
                id = cursor.getLong(idColumn);
                title = cursor.getString(titleColumn);
                Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                songs.add(new Song(contentUri, title));
            }
        }
        songRecycler = root.findViewById(R.id.song_recycler);
        songAdapter = new SongAdapter(songs, getActivity());
        songRecycler.setAdapter(songAdapter);
        songRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Inflate the layout for this fragment
        return root;
    }
}
