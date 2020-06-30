package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudyFragment extends Fragment {
    private Button module1btn,module2btn,module3btn,module4btn,module5btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_study,container,false);

        module1btn = root.findViewById(R.id.module1);
        module1btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ModuleOneActivity.class);
                startActivity(intent);
            }
        });

        module2btn = root.findViewById(R.id.module2);
        module2btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ModuleTwoActivity.class);
                startActivity(intent);
            }
        });

        module3btn = root.findViewById(R.id.module3);
        module3btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ModuleThreeActivity.class);
                startActivity(intent);
            }
        });

        module4btn = root.findViewById(R.id.module4);
        module4btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ModuleFourActivity.class);
                startActivity(intent);
            }
        });

        module5btn = root.findViewById(R.id.module5);
        module5btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ModuleFiveActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
