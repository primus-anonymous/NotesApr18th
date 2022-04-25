package com.neocaptainnemo.notesapr18th.di;

import com.neocaptainnemo.notesapr18th.domain.FireStoreNotesRepository;
import com.neocaptainnemo.notesapr18th.domain.NotesRepository;

public class Dependencies {

    private static final NotesRepository NOTES_REPOSITORY = new FireStoreNotesRepository();

    public static NotesRepository getNotesRepository() {
        return NOTES_REPOSITORY;
    }
}
