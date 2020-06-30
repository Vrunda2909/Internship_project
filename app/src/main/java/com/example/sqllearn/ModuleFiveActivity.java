package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ModuleFiveActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_five);

        //Toolbar appbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(appbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        recyclerView = findViewById(R.id.recyclerview);

        String[]title = new String[]
                {
                        "DROP TABLE Statement","NOT NULL Constraint","UNIQUE Constraint","PRIMARY KEY Constraint","FOREIGN KEY Constraint"
                };
        String[]htmlFile = new String[]
                {
                        "module5_1","module5_2","module5_3","module5_4","module5_5"
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
