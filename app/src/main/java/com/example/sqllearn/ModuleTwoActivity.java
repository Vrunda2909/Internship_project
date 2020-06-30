package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ModuleTwoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_two);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerview);

        String[]title = new String[]
                {
                        "NULL Values ","UPDATE Statement","DELETE Statement","MIN() and MAX() Functions","COUNT(), AVG() and SUM() Functions ","LIKE Operators"
                };
        String[]htmlFile = new String[]
                {
                        "module2_1","module2_2","module2_3","module2_4","module2_5","module2_6"
                };
        moduleAdapter = new ModuleAdapter(this,title,htmlFile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moduleAdapter);

    }

    public void onBackPressed(){
        Intent intent = new Intent(this, StudyFragment.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this, StudyFragment.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
