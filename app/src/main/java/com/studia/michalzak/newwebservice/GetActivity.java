package com.studia.michalzak.newwebservice;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetActivity extends AppCompatActivity {

    private static InputStream response;
    private EditText editTextPostId;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        editTextPostId = findViewById(R.id.editTextPostId);
        textViewData = findViewById(R.id.textViewData);
    }

        public void onClickGet(View v) {
            if(editTextPostId.getText().toString().equals("")) {
                Toast.makeText(GetActivity.this, "Id posta jest wymagane!", Toast.LENGTH_SHORT).show();
            } else {
                fetchData();
            }
        }

    private void fetchData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint + "/" + editTextPostId.getText().toString());
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    if(httpsURLConnection.getResponseCode() == 200) {
                        response = httpsURLConnection.getInputStream();
                        setGetMessage();
                        httpsURLConnection.disconnect();
                    }
                } catch (Exception exception) {
                    Log.e("Coś poszło nie tak!: ", exception.getMessage());
                }
            }
        });
    }

    private void setGetMessage() {
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                    InputStreamReader inputStreamReader = new InputStreamReader(response);
                    JsonReader jsonReader = new JsonReader(inputStreamReader);
                    Message responseMessage = new Message(jsonReader);
                    textViewData.setText(responseMessage.toString());
            }
        });
    }
}