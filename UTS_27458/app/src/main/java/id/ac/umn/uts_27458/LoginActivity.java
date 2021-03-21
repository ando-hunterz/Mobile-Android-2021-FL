package id.ac.umn.uts_27458;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    ImageView playerImg;
    Button submitBtnLogin;
    EditText passwordET, usernameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        playerImg = findViewById(R.id.playerImg);
        playerImg.setImageResource(R.drawable.ic_spotmefy);
        submitBtnLogin = findViewById(R.id.submitLoginBtn);
        passwordET = findViewById(R.id.passwordEditText);
        usernameET = findViewById(R.id.usernameEditText);



        submitBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                if(username.equals("uasmobile") && password.equals("uasmobilegenap") ) {
                    Intent listSongIntent = new Intent(LoginActivity.this, ListSongActivity.class);
                    startActivity(listSongIntent);
                    setResult(RESULT_OK, null);
                    finish();
                } else {
                    Fragment loginFragment = new LoginFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction
                            .setCustomAnimations(
                                    R.anim.fade_in,
                                    R.anim.fade_out
                            )
                            .add(R.id.loginFragment, loginFragment, "loginFragment")
                            .addToBackStack("loginFragment").commit();
                }
            }

        });
    }

}