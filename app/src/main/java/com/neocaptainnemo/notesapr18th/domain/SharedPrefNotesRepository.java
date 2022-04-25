package com.neocaptainnemo.notesapr18th.domain;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SharedPrefNotesRepository implements NotesRepository {

    private static final String KEY_SAVED_NOTES = "KEY_SAVED_NOTES";

    private SharedPreferences sharedPreferences;

    public SharedPrefNotesRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("NOTES", Context.MODE_PRIVATE);
    }

    @Override
    public void getAll(Callback<List<Note>> callback) {
        Gson gson = new Gson();

        String savedData = sharedPreferences.getString(KEY_SAVED_NOTES, "[]");

        Type type = new TypeToken<ArrayList<Note>>() {}.getType();

        List<Note> savedNotes = gson.fromJson(savedData, type);


        callback.onSuccess(savedNotes);

    }

    @Override
    public void addNote(String title, String message, Callback<Note> callback) {

        Note note = new Note(UUID.randomUUID().toString(), title, message, new Date());

        Gson gson = new Gson();

        String savedData = sharedPreferences.getString(KEY_SAVED_NOTES, "[]");

        Type type = new TypeToken<ArrayList<Note>>() {}.getType();

        List<Note> savedNotes = gson.fromJson(savedData, type);

        savedNotes.add(note);

        String toWrite = gson.toJson(savedNotes, type);

        sharedPreferences
                .edit()
                .putString(KEY_SAVED_NOTES, toWrite)
                .apply();

        callback.onSuccess(note);
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

    }

    @Override
    public void updateNote(Note note, String title, String message, Callback<Note> callback) {

    }
}
