package com.example.finalproject.callbacks;

import com.example.finalproject.model.EventModel;

import java.util.ArrayList;

public interface EventCallback {
    void onSuccess(ArrayList<EventModel> events);
    void onFailure(String message);
}
