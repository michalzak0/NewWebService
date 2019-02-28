package com.studia.michalzak.newwebservice;

import android.util.JsonReader;
import android.util.Log;

public class Message {
    String title;
    String body;

    public Message(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Message(JsonReader jsonReader){
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                if (key.equals("title")) {
                    title = jsonReader.nextString();
                } else if (key.equals("body")) {
                    body = jsonReader.nextString();
                } else {
                    jsonReader.skipValue();
                }
            }
        }
        catch (Exception exception) {
            Log.e("Something went wrong: ", exception.getMessage());
        }
    }

    @Override
    public String toString(){
        return "Title: " + title + "\n" + "Message: " + body + "\n";
    }
}
