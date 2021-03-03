package id.ac.umn.week06_27458;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class KudaActivity extends AppCompatActivity {

    AnimationDrawable animasiKuda;
    ImageView gambarKuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuda);
        gambarKuda = (ImageView) findViewById(R.id.gambarKuda);
        gambarKuda.setBackgroundResource(R.drawable.kuda_lari);
        animasiKuda = (AnimationDrawable) gambarKuda.getBackground();
        gambarKuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animasiKuda.start();
            }
        });
    }

}