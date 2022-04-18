package com.neocaptainnemo.notesapr18th.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neocaptainnemo.notesapr18th.R;
import com.neocaptainnemo.notesapr18th.di.Dependencies;
import com.neocaptainnemo.notesapr18th.domain.Note;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotesListFragment extends Fragment {


    public NotesListFragment() {
        super(R.layout.fragment_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        notesList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider));

        notesList.addItemDecoration(dividerItemDecoration);

        NotesAdapter adapter = new NotesAdapter();
        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        notesList.setAdapter(adapter);

        List<Note> notes = Dependencies.NOTES_REPOSITORY.getAll();

        adapter.setData(notes);

        adapter.notifyDataSetChanged();

    }
}
