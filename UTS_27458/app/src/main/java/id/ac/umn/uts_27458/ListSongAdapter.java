package id.ac.umn.uts_27458;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ListSongViewHolder> {

    private final Context context;
    private  ArrayList<Song> SongList;

    public ListSongAdapter(Context context, ArrayList<Song> songList){
        this.SongList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song, parent, false);
        return new ListSongViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSongViewHolder holder, int position) {
        holder.ArtistName.setText(SongList.get(position).getArtist());
        holder.SongName.setText(SongList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return SongList.size();
    }

    public  class ListSongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ArtistName, SongName;
        final ListSongAdapter lSongAdapter;

        public ListSongViewHolder(@NonNull View itemView,ListSongAdapter lsongAdapter) {
            super(itemView);
            SongName = itemView.findViewById(R.id.SongName);
            ArtistName = itemView.findViewById(R.id.ArtistName);
            this.lSongAdapter = lsongAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int songPosition = getLayoutPosition();
            Bundle songBundle = new Bundle();
            songBundle.putParcelableArrayList("SongList", SongList);
            songBundle.putInt("Position",songPosition);
            Intent songPlayerIntent = new Intent(context, SongPlayerActivity.class);
            songPlayerIntent.putExtra("SongProp", songBundle);
            context.startActivity(songPlayerIntent);

        }
    }
}
