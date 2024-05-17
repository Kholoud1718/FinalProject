package com.example.finalproject.controllers;

import android.content.Intent;

import com.example.finalproject.activities.MainActivity;
import com.example.finalproject.callbacks.EventCallback;
import com.example.finalproject.model.EventModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class UserEventsController {

    private static final String COLLECTION_NAME = "UserEvents";
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private ArrayList<EventModel> events = new ArrayList<>();

    public void save(EventModel event, EventCallback callback) {
        getEvents(new EventCallback() {
            @Override
            public void onSuccess(ArrayList<EventModel> events) {
                for (EventModel eventModel : events) {
                    if (eventModel.getKey().equals(event.getKey())) {
                        callback.onFailure("You are registered before for this event!");
                        return;
                    }
                }

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser != null){
                    String uid = currentUser.getUid();
                    if (!uid.isEmpty())
                        firestore.collection(COLLECTION_NAME)
                                .document(uid)
                                .collection("Events")
                                .document()
                                .set(event)
                                .addOnSuccessListener(aVoid -> {
                                    events.add(event);
                                    callback.onSuccess(events);
                                })
                                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
                }
            }

            @Override
            public void onFailure(String message) {
                callback.onFailure(message);
            }
        });
    }

    public void getEvents(EventCallback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            String uid = currentUser.getUid();
            if (!uid.isEmpty())
                firestore.collection(COLLECTION_NAME)
                        .document(uid)
                        .collection("Events")
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            events = new ArrayList<>();
                            events.addAll(queryDocumentSnapshots.toObjects(EventModel.class));
                            callback.onSuccess(events);
                        })
                        .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
        }


    }
}
