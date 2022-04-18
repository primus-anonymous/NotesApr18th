package com.neocaptainnemo.notesapr18th.di;

import com.neocaptainnemo.notesapr18th.domain.InMemoryNotesRepository;
import com.neocaptainnemo.notesapr18th.domain.NotesRepository;

public class Dependencies {

    public static final NotesRepository NOTES_REPOSITORY = new InMemoryNotesRepository();
}
