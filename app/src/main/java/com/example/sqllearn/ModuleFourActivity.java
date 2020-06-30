package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ModuleFourActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_four);

        //Toolbar appbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(appbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        recyclerView = findViewById(R.id.recyclerview);

        String[]title = new String[]
                {
                        "Wildcard Characters ","Aliases","GROUP BY Statement","ANY and ALL Operators ","ALTER TABLE Statement "
                };
        String[]htmlFile = new String[]
                {
                        "module4_1","module4_2","module4_3","module4_4","module4_5"
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
