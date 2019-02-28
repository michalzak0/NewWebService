package com.studia.michalzak.newwebservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostActivity extends AppCompatActivity {

    private EditText editTextUserId;
    private EditText editTextTitle;
    private EditText editTextBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editTextUserId = findViewById(R.id.editTextUserId);
        editTextTitle = findViewById(R.id.editTextName);
        editTextBody = findViewById(R.id.editTextMessage);
    }

    public void onClickPost(View v) {
        if(fieldsEmpty()) {
            Toast.makeText(PostActivity.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
        } else {
            makeRequest();
        }
    }

    private void makeRequest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");

                    String userId = editTextUserId.getText().toString();
                    String  title = editTextTitle.getText().toString();
                    String body = editTextBody.getText().toString();
                    Message message = new Message(title, body, "101", userId);

                    JSONObject postData = new JSONObject(message.toJson());
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.getOutputStream().write(postData.toString().getBytes());
                    if(httpsURLConnection.getResponseCode() == 201) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostActivity.this, "Post zakończony sukcesem!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        httpsURLConnection.disconnect();
                    }
                }
                catch (Exception exception) {
                    Log.e("Coś poszło nie tak!: ", exception.getMessage());
                }
            }
        });
    }

    private boolean fieldsEmpty(){
        return editTextUserId.getText().toString().equals("")
                || editTextTitle.getText().toString().equals("")
                || editTextBody.getText().toString().equals("");
    }
}