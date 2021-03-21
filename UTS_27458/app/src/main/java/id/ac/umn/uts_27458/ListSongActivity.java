package id.ac.umn.uts_27458;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSongActivity extends AppCompatActivity{
    private static final int PERMISSION_READ_STORAGE = 1;

   ArrayList<Song> listSong = new ArrayList<Song>();
   RecyclerView listSongRV;
   ListSongAdapter listSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        listSongRV = findViewById(R.id.songRecycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listSongRV.setLayoutManager(mLayoutManager);
        listSongAdapter = new ListSongAdapter(this, listSong);
        listSongRV.setAdapter(listSongAdapter);
        listSongRV.addItemDecoration(new DividerItemDecoration(listSongRV.getContext(), DividerItemDecoration.VERTICAL));
        Fragment welcomeFragment = new WelcomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out,
                        0,// enter
                        R.anim.fade_out  // exit
                )
                .add(R.id.welcomeFragment, welcomeFragment, "welcomeFragment")
                .addToBackStack("welcomeFragment").commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==  PERMISSION_READ_STORAGE){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readSong();
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            readSong();
        } else {
            ActivityCompat.requestPermissions(ListSongActivity.this,
                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_READ_STORAGE);
        }
    }

    private void readSong(){

        String proj[] = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
        };

        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";

        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp3");

        String[] selectionArgsMp3 = new String[]{ mimeType };


        try (Cursor cursor = getApplicationContext().getContentResolver().query(
               MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                proj,
                selectionMimeType, selectionArgsMp3, null))
        {
            int pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                String path = cursor.getString(pathColumn);
                String name = cursor.getString(nameColumn);
                String artist = cursor.getString(artistColumn);
                // Stores column values and the contentUri in a local object
                // that represents the media file.
                listSong.add(new Song(path, name, artist));
        }
    }
        for (Song song: listSong){
            Log.i("ListSongActivity","Song:"+song.getPath());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                showProfile();
                return true;
            case R.id.action_logout:
                Intent mainActivityIntent = new Intent(ListSongActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProfile(){
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("PARENT_ACTIVITY", "ListSongActivity");
        startActivity(profileIntent);
    }

}