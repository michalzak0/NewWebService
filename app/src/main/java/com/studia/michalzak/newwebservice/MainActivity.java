package com.studia.michalzak.newwebservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    static final String apiEndpoint = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGet(View view) {
        Intent intent = new Intent(MainActivity.this, GetActivity.class);
        startActivity(intent);
    }


    public void onClickPost(View v) {
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        startActivity(intent);
    }

    public void onClickPut(View v) {
        Intent intent = new Intent(MainActivity.this, PutActivity.class);
        startActivity(intent);
    }


    public void onClickDelete(View v) {
        Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(intent);
    }
}