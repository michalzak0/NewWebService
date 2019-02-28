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
                switch (key) {
                    case "title":
                        title = jsonReader.nextString();
                        break;
                    case "body":
                        body = jsonReader.nextString();
                        break;
                    case "userId":
                        userId = jsonReader.nextString();

                        break;
                    case "id":
                        id = jsonReader.nextString();
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
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
        return  gson.toJson(this);
    }
}
