package id.ac.umn.week08b_27458;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private int mWarna;
    private TextView tvCounter;
    private Context context;
    private final String COUNTER_KEY = "counter";
    private final String WARNA_KEY = "warna";
    private SharedPreferences mPreferences;
    private String sharedPrefFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        sharedPrefFile = context.getPackageName();
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        mCount = mPreferences.getInt(COUNTER_KEY, 0);
        tvCounter.setText(String.valueOf(mCount));
        mWarna = mPreferences.getInt(WARNA_KEY, mWarna);
        tvCounter.setBackgroundColor(mWarna);
    }

    public void gantiBackground(View view){
        int warna = ((ColorDrawable)view.getBackground()).getColor();
        mWarna = warna;
        tvCounter.setBackgroundColor(warna);
    }
    public void tambahCounter(View view){
        mCount++;
        tvCounter.setText(String.valueOf(mCount));
    }
    public void reset(View view){
        mCount = 0;
        tvCounter.setText(String.valueOf(mCount));
        mWarna = ContextCompat.getColor(context,R.color.default_background);
        tvCounter.setBackgroundColor(mWarna);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNTER_KEY, mCount);
        preferencesEditor.putInt(WARNA_KEY, mWarna);
        preferencesEditor.apply();
    }


}