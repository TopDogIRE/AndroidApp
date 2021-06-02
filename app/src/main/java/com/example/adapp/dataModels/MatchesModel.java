package com.example.adapp.dataModels;


import com.example.adapp.model.Match;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MatchesModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchesModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatch(Match m) {
        CollectionReference todoItemsRef = db.collection("matches");
        todoItemsRef.add(m);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchById(Match m) {
        DocumentReference todoItemRef = db.collection("matches").document(m.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("name", m.name);
        data.put("liked", m.liked);
        data.put("imageUrl", m.imageUrl);
        data.put("lat", m.lat);
        data.put("longitude",m.longitude);
        todoItemRef.update(data);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(ListenerRegistration::remove);
    }
}