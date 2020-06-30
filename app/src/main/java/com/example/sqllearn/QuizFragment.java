package com.example.sqllearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sqllearn.R;

public class QuizFragment extends Fragment {

    private Button module1quizbtn,module2quizbtn,module3quizbtn,module4quizbtn,module5quizbtn;

    private DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz,container,false);
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        drawer = root.findViewById(R.id.drawer_layout);

        module1quizbtn = root.findViewById(R.id.module1quiz);
        module1quizbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QuizOneActivity.class);
                startActivity(intent);
            }
        });

        module2quizbtn = root.findViewById(R.id.module2quiz);
        module2quizbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QuizTwoActivity.class);
                startActivity(intent);
            }
        });

        module3quizbtn = root.findViewById(R.id.module3quiz);
        module3quizbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QuizThreeActivity.class);
                startActivity(intent);
            }
        });

        module4quizbtn = root.findViewById(R.id.module4quiz);
        module4quizbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QuizFourActivity.class);
                startActivity(intent);
            }
        });

        module5quizbtn = root.findViewById(R.id.module5quiz);
        module5quizbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QuizFiveActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

}
