package com.neocaptainnemo.notesapr18th.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InMemoryNotesRepository implements NotesRepository {

    private ArrayList<Note> data = new ArrayList<>();

    public InMemoryNotesRepository() {
        data.add(new Note(UUID.randomUUID().toString(), "Title 1", "Message 1", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 2", "Message 2", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 3", "Message 3", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 4", "Message 4", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 5", "Message 5", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 6", "Message 6", new Date()));
        data.add(new Note(UUID.randomUUID().toString(), "Title 7", "Message 7", new Date()));

        for (int i = 0; i < 3000; i++) {
            data.add(new Note(UUID.randomUUID().toString(), "Title 7", "Message 7", new Date()));

        }
    }

    @Override
    public List<Note> getAll() {
        return data;
    }
}
