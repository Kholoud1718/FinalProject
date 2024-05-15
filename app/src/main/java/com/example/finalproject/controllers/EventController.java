package com.example.finalproject.controllers;

import com.example.finalproject.callbacks.EventCallback;
import com.example.finalproject.model.EventModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;


public class EventController {

    private static final String COLLECTION_NAME = "Events";
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private ArrayList<EventModel> events = new ArrayList<>();

    public void save(EventModel event, EventCallback callback) {
        firestore.collection(COLLECTION_NAME)
                .document()
                .set(event)
                .addOnSuccessListener(aVoid -> {
                    events.add(event);
                    callback.onSuccess(events);
                })
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    public void getEvents(EventCallback callback) {
        firestore.collection(COLLECTION_NAME)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    events = new ArrayList<>();
                    events.addAll(queryDocumentSnapshots.toObjects(EventModel.class));
                    callback.onSuccess(events);
                })
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }
}
