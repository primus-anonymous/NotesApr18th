package com.neocaptainnemo.notesapr18th.domain;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FireStoreNotesRepository implements NotesRepository {

    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_CREATED_AT = "createdAt";

    private static final String NOTES = "notes";

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void getAll(Callback<List<Note>> callback) {

        firestore.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<Note> result = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            String id = documentSnapshot.getId();

                            String title = documentSnapshot.getString(KEY_TITLE);
                            String message = documentSnapshot.getString(KEY_MESSAGE);
                            Date createdAt = documentSnapshot.getDate(KEY_CREATED_AT);

                            result.add(new Note(id, title, message, createdAt));
                        }

                        callback.onSuccess(result);

                    }
                });

    }

    @Override
    public void addNote(String title, String message, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        Date createdAt = new Date();

        data.put(KEY_TITLE, title);
        data.put(KEY_MESSAGE, message);
        data.put(KEY_CREATED_AT, createdAt);

        firestore.collection(NOTES)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        callback.onSuccess(new Note(documentReference.getId(), title, message, createdAt));
                    }
                });

    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

        firestore.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);
                    }
                });
    }

    @Override
    public void updateNote(Note note, String title, String message, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, title);
        data.put(KEY_MESSAGE, message);

        firestore.collection(NOTES)
                .document(note.getId())
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Note result = new Note(note.getId(), title, message, note.getCreatedAt());

                        callback.onSuccess(result);
                    }
                });

    }
}
