package id.ac.umn.uts_27458;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;



public class ProfileActivity extends AppCompatActivity {


    ImageView profileImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileImg = findViewById(R.id.profileImg);
        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = getIntent();
                String parentActivity = intent.getStringExtra("PARENT_ACTIVITY");
                if(parentActivity.equals("MainActivity")){
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return true;
                } else {
                    startActivity(new Intent(this, ListSongActivity.class));
                    finish();
                    return true;
                }

        }
        return super.onOptionsItemSelected(item);
    }
}