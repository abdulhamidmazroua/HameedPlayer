package com.mido.hameedplayer.ui.Playlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mido.hameedplayer.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder> {
    private Context context;
    private ArrayList<Playlist> playlists;
    private ClickListener listener;

    PlaylistAdapter(Context context, ArrayList<Playlist> playlists, ClickListener listener) {
        this.context = context;
        this.playlists = playlists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PlaylistHolder(LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        Playlist currentPlaylist = playlists.get(position);
        holder.bindTo(currentPlaylist);
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    class PlaylistHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private ImageView playlistImage;
        private TextView playlistTitle;
        private TextView playlistDescription;
        private ImageButton popupDelete;
        private WeakReference<ClickListener> refListener;
        PlaylistHolder(@NonNull final View itemView, ClickListener listener) {
            super(itemView);
            this.refListener = new WeakReference<>(listener);
            this.playlistImage = itemView.findViewById(R.id.playlist_image);
            this.playlistTitle = itemView.findViewById(R.id.playlist_title);
            this.playlistDescription = itemView.findViewById(R.id.playlist_description);
            this.popupDelete = itemView.findViewById(R.id.popupDelete);
            popupDelete.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bindTo(Playlist currentPlaylist) {
            playlistTitle.setText(currentPlaylist.getTitle());
            playlistDescription.setText(currentPlaylist.getDescription());

            Glide.with(context).load(currentPlaylist.getImageResource()).into(playlistImage);
        }

        @Override
        public void onClick(View view) {
            if (view == itemView) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                // delegating the handling task for the home fragment
                refListener.get().onViewHolderClickedListener(getAdapterPosition());
            } else if (view == popupDelete) {
                PopupMenu popup = new PopupMenu(context, view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(this);
            }

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.delete_action:
                    // delegating the handling task for the home fragment
                    refListener.get().onDeleteClicked(getAdapterPosition());
                    return true;
            }
            return false;
        }
    }
    // This interface is used to define the callback methods for delegating the handling of clicks on any view in the ViewHandler
    // to the HomeFragment
    interface ClickListener{
        void onViewHolderClickedListener(int position);
        void onDeleteClicked(int position);
    }
}
