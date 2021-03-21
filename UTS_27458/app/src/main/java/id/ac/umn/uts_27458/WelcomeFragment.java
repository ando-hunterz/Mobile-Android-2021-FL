package id.ac.umn.uts_27458;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class WelcomeFragment extends Fragment {
    Button welcomeBtn;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_welcome, container, false);
        welcomeBtn = view.findViewById(R.id.welcomeBtn);

        welcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment welcomeFragment = getActivity().getSupportFragmentManager().findFragmentByTag("welcomeFragment");
                getActivity().getSupportFragmentManager().beginTransaction().remove(welcomeFragment).commit();
            }
        });
        return view;
    }
}