package com.studia.michalzak.newwebservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PostActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button buttonPost = findViewById(R.id.buttonPost);
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextMessage = findViewById(R.id.editTextMessage);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextId.getText().toString().equals("")
                        || editTextName.getText().toString().equals("")
                        || editTextMessage.getText().toString().equals("")) {
                    Toast.makeText(PostActivity.this, "Inputs can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    makeRequest();
                }
            }
        });
    }

    private void makeRequest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");
                    Map<String, String> data = new HashMap<>();
                    String id = editTextId.getText().toString();
                    String  name = editTextName.getText().toString();
                    String message = editTextMessage.getText().toString();
                    User user = new User(id, name, message);
                    JSONObject postData = new JSONObject(user);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.getOutputStream().write(postData.toString().getBytes());
                    if(httpsURLConnection.getResponseCode() == 201) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostActivity.this, "Post successfully created", Toast.LENGTH_SHORT).show();
                            }
                        });
                        httpsURLConnection.disconnect();
                    }
                } catch (Exception exception) {
                    Log.e("Something went wrong: ", exception.getMessage());
                }
            }
        });
    }
}