package id.ac.umn.uts_27458;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {
    Button closeErrorBtn;
    private final ClickButton clickButton = new ClickButton();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        closeErrorBtn = view.findViewById(R.id.closeErrorBtn);
        closeErrorBtn.setOnClickListener(clickButton);

        return view;
    }

    private class ClickButton implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Fragment loginFragment = getActivity().getSupportFragmentManager().findFragmentByTag("loginFragment");
            getActivity().getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
        }
    }
}