package id.ac.umn.uts_27458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SongPlayerActivity extends AppCompatActivity {

    static MediaPlayer mediaPlayer;
    private ArrayList<Song> SongList;
    private int position;
    private TextView songTitleName, songArtistName;
    private ImageView songPlayBtn, songPauseBtn, songNextBtn, songPrevBtn;
    private SeekBar songSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);
        Intent songPlayerIntent = getIntent();
        Bundle songBundle = songPlayerIntent.getBundleExtra("SongProp");
        SongList = songBundle.getParcelableArrayList("SongList");
        position = songBundle.getInt("Position");
        Log.i("SongPlayerActivity", String.valueOf(position));
        songSeekBar = findViewById(R.id.songSeekBar);
        songTitleName = findViewById(R.id.playerSongTitle);
        songArtistName = findViewById(R.id.songArtistName);
        songPlayBtn = findViewById(R.id.songPlayBtn);
        songPauseBtn = findViewById(R.id.songPauseBtn);
        songNextBtn = findViewById(R.id.nextSong);
        songPrevBtn = findViewById(R.id.prevSong);
        songTitleName.setText(SongList.get(position).getName());
        songArtistName.setText(SongList.get(position).getArtist());
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(SongList.get(position).getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        songPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                songPauseBtn.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);

                SongPlayerActivity.this.runOnUiThread(new Runnable() {
                    private Handler mHandler = new Handler();
                    @Override
                    public void run() {
                        if(mediaPlayer != null){
                            int songCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                            songSeekBar.setProgress(songCurrentPosition);
                        }
                        mHandler.postDelayed(this, 1000);
                    }
                });
            }
        });

        songPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                songPlayBtn.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);
            }
        });

        songPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Toast.makeText(SongPlayerActivity.this, "This Is the first Song",Toast.LENGTH_LONG);
                } else {
                    Intent newSongIntent = new Intent(SongPlayerActivity.this, SongPlayerActivity.class);
                    Bundle newSongBundle = new Bundle();
                    newSongBundle.putParcelableArrayList("SongList",SongList);
                    newSongBundle.putInt("Position", position-1);
                    newSongIntent.putExtra("SongProp",newSongBundle);
                    startActivity(newSongIntent);
                    finish();
                }
            }
        });

        songNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == (SongList.size()-1)){
                    position = -1;
                }
                Intent newSongIntent = new Intent(SongPlayerActivity.this, SongPlayerActivity.class);
                Bundle newSongBundle = new Bundle();
                newSongBundle.putParcelableArrayList("SongList",SongList);
                newSongBundle.putInt("Position", position+1);
                newSongIntent.putExtra("SongProp",newSongBundle);
                startActivity(newSongIntent);
                finish();
            }
        });


        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(mediaPlayer.getDuration() / 1000);
                if(fromUser)
                {
                    mediaPlayer.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



}