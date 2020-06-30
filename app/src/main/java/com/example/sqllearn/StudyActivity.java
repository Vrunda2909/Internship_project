package com.example.sqllearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class StudyActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        Intent intent = getIntent();
        String url = intent.getStringExtra("file");
        webView.loadUrl("file:///android_asset/"+url+".html");
        webView.setWebViewClient(new WebViewClient());

    }

    public void onBackPressed(){
//        Intent intent = new Intent(this, ModuleOneActivity.class);
//        startActivity(intent);
//        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
//            Intent intent = new Intent(this, ModuleOneActivity.class);
//            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
