package id.ac.umn.week04a_27458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button buttonChange1, buttonChange2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonChange1 = findViewById(R.id.main_button_change_1);
        buttonChange2 = findViewById(R.id.main_button_change_2);

        buttonChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToTwo = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intentToTwo);
            }
        });

        buttonChange2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToThree = new Intent(FirstActivity.this, ThirdActivity.class);
                startActivity(intentToThree);
            }
        });
    }
}