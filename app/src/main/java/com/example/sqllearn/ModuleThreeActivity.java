package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ModuleThreeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_three);

        //Toolbar appbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(appbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        recyclerView = findViewById(R.id.recyclerview);

        String[]title = new String[]
                {
                        "IN and BETWEEN Operator ","JOIN Keyword","INNER JOIN Keyword","LEFT JOIN Keyword","RIGHT JOIN Keyword","UNION Operator"
                };
        String[]htmlFile = new String[]
                {
                        "module3_1","module3_2","module3_3","module3_4","module3_5","module3_6"
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
