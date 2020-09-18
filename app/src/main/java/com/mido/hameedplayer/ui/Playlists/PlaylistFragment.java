package com.mido.hameedplayer.ui.Playlists;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mido.hameedplayer.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class PlaylistFragment extends Fragment implements PlaylistAdapter.ClickListener {
    private static final String PLAYLISTS_PREF = "com.mid.hameedPlayer.ui.home.playlist.pref";
    private RecyclerView playlistRecycler;
    private ArrayList<Playlist> playlists;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_FILE = "com.mido.hameedplayer.ui.home";
    private PlaylistAdapter playlistAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_playlists, container, false);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        playlistRecycler = root.findViewById(R.id.playlist_recycler);
        loadPlaylists();
        playlistAdapter = new PlaylistAdapter(getContext(), playlists, this);
        buildRecycler();
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaylistFragmentDialog dialog = new PlaylistFragmentDialog();
                dialog.show(getChildFragmentManager(), "playlistDialog");
            }
        });

        return root;
    }

    // after getting the name and the description from the dialog fragment we add a new playlist

    void addPlaylist(String name, String description) {
        // Generating a random image for the playlist
        Random random = new Random();
        int n = random.nextInt(4);
        TypedArray playlistImageResources = getActivity().getResources().obtainTypedArray(R.array.playlists_images);
        // Adding the playlist object to the ArrayList
        playlists.add(new Playlist(name, description, playlistImageResources.getResourceId(n, 0)));
        playlistAdapter.notifyItemInserted(playlists.size());
        // Save changes to shared preferences
        saveChanges();
    }

    private void buildRecycler() {
        playlistRecycler.setAdapter(playlistAdapter);
        int gridColumns = getActivity().getResources().getInteger(R.integer.grid_column_count);
        playlistRecycler.setLayoutManager(new GridLayoutManager(getContext(), gridColumns));
    }

    // Loading the playlists when first creating the fragment
    private void loadPlaylists() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PLAYLISTS_PREF, null);
        Type type = new TypeToken<ArrayList<Playlist>>() {
        }.getType();
        playlists = gson.fromJson(json, type);
        if (playlists == null) {
            playlists = new ArrayList<>();
        }
    }

    // saving the changes to a shared preferences
    private void saveChanges() {
        Gson gson = new Gson();
        String json = gson.toJson(playlists);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYLISTS_PREF, json);
        editor.apply();
    }
    // handling the click on the playlist
    @Override
    public void onViewHolderClickedListener(int position) {

    }

    // handling the deletion of a playlist
    @Override
    public void onDeleteClicked(int position) {
        playlists.remove(position);
        playlistAdapter.notifyItemRemoved(position);
        saveChanges();
    }
}
