package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sqllearn.R;

public class QueryFragment extends Fragment {

    private Button module1querybtn,module2querybtn,module3querybtn,module4querybtn,module5querybtn;
    private DrawerLayout drawer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_query,container,false);
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        drawer = root.findViewById(R.id.drawer_layout);

        module1querybtn = root.findViewById(R.id.module1query);
        module1querybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QueryOneActivity.class);
                startActivity(intent);
            }
        });

        module2querybtn = root.findViewById(R.id.module2query);
        module2querybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QueryTwoActivity.class);
                startActivity(intent);
            }
        });

        module3querybtn = root.findViewById(R.id.module3query);
        module3querybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QueryThreeActivity.class);
                startActivity(intent);
            }
        });

        module4querybtn = root.findViewById(R.id.module4query);
        module4querybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QueryFourActivity.class);
                startActivity(intent);
            }
        });

        module5querybtn = root.findViewById(R.id.module5query);
        module5querybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), QueryFiveActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
