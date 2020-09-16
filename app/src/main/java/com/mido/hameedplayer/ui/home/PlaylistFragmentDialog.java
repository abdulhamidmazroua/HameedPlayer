package com.mido.hameedplayer.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mido.hameedplayer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragmentDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_playlist_dialog, (ViewGroup) getView(), false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("New Playlist");
        builder.setView(view);
        builder.setPositiveButton("Create Playlist", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                EditText nameEditText = view.findViewById(R.id.playlist_name_input);
                EditText descriptionEditText = view.findViewById(R.id.playlist_description_input);
                 HomeFragment homeFragment = (HomeFragment) getParentFragment();
                homeFragment.addPlaylist(nameEditText.getText().toString(), descriptionEditText.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.show();
    }
}
