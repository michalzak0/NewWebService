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

public class PutActivity extends AppCompatActivity {

    private EditText editUserId;
    private EditText editTextTitle;
    private EditText editTextBody;
    private EditText editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        editUserId = findViewById(R.id.editTextIdPut);
        editTextTitle = findViewById(R.id.editTextTitlePut);
        editTextBody = findViewById(R.id.editTextBodyPut);
        editTextId = findViewById(R.id.editTextPostId);
    }

    public void onClickPut(View v) {
        if(fieldsEmpty()) {
            Toast.makeText(PutActivity.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
        } else {
            makeRequest();
        }
    }

    private void makeRequest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint + "/" + editTextId.getText().toString());
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("PUT");

                    Message message = getMessageFromBoxes();

                    JSONObject postData = new JSONObject(message.toJson());
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.getOutputStream().write(postData.toString().getBytes());
                    if(httpsURLConnection.getResponseCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PutActivity.this, "Post zakończony sukcesem!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        httpsURLConnection.disconnect();
                    }
                } catch (Exception exception) {
                    Log.e("Coś poszło nie tak!: ", exception.getMessage());
                }
            }
        });
    }

    private Message getMessageFromBoxes(){
        String userId = editUserId.getText().toString();
        String title = editTextTitle.getText().toString();
        String body = editTextBody.getText().toString();
        String id = editTextId.getText().toString();
        return new Message(title, body, id, userId);
    }

    private boolean fieldsEmpty(){
        return editUserId.getText().toString().equals("")
                || editTextTitle.getText().toString().equals("")
                || editTextBody.getText().toString().equals("")
                || editTextId.getText().toString().equals("");
    }
}