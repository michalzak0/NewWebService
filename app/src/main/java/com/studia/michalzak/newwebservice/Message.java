package com.studia.michalzak.newwebservice;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;

public class Message {
    String title;
    String body;
    String id;
    String userId;

    public Message(JsonReader jsonReader){
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                if (key.equals("title")) {
                    title = jsonReader.nextString();
                }
                else if (key.equals("body")) {
                    body = jsonReader.nextString();
                }
                else if (key.equals("userId")) {
                    userId = jsonReader.nextString();

                }
                else if (key.equals("id")) {
                    id = jsonReader.nextString();
                }
                else {
                    jsonReader.skipValue();
                }
            }
        }
        catch (Exception exception) {
            Log.e("Something went wrong: ", exception.getMessage());
        }
    }

    public Message(String title, String body, String id, String userId) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "UserID: " + userId + ", messageId: " + id + "\n" + "Title: " + title + "\n" + "Message: " + body + "\n";
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
