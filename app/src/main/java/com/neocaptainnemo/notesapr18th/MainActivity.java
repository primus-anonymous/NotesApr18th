package com.neocaptainnemo.notesapr18th;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.neocaptainnemo.notesapr18th.ui.InfoFragment;
import com.neocaptainnemo.notesapr18th.ui.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new NotesListFragment())
                    .commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_notes:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new NotesListFragment())
                                .commit();

                        return true;

                    case R.id.action_info:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new InfoFragment())
                                .commit();

                        return true;

                }
                return false;
            }
        });
    }
}