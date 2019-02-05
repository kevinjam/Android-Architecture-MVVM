package com.thinkdevs.noteapp;

import android.os.Bundle;

import com.thinkdevs.noteapp.database.NoteEntity;
import com.thinkdevs.noteapp.viewmodel.EditorViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.widget.TextView;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewmodel;
    private TextView noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteText = findViewById(R.id.note_text);
        
        initViewModel();
    }

    private void initViewModel() {
        mViewmodel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewmodel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                noteText.setText(noteEntity.getText());
            }
        });
    }
}
