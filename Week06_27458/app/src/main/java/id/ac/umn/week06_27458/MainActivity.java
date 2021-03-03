package id.ac.umn.week06_27458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button buttonKuda;
    private Button btnSpring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonKuda = findViewById(R.id.ButtonKuda);
        btnSpring = findViewById(R.id.ButtonSpring);

        buttonKuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKuda = new Intent(MainActivity.this, KudaActivity.class);
                startActivity(intentKuda);
            }
        });

        btnSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSpring = new Intent(MainActivity.this, SpringActivity.class);
                startActivity(intentSpring);
            }
        });
    }
}