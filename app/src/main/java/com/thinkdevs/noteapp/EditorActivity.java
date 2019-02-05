package com.thinkdevs.noteapp;

import android.os.Bundle;

import com.thinkdevs.noteapp.database.NoteEntity;
import com.thinkdevs.noteapp.viewmodel.EditorViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import static com.thinkdevs.noteapp.utilities.Constants.EDITING_KEY;
import static com.thinkdevs.noteapp.utilities.Constants.NOTE_ID_KEY;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewmodel;
    private TextView noteText;
    private boolean mNewNote, mEditing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteText = findViewById(R.id.note_text);
        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(NOTE_ID_KEY);

        }

        initViewModel();
    }

    private void initViewModel() {
        mViewmodel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewmodel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                if (noteEntity != null && !mEditing){
                    noteText.setText(noteEntity.getText());

                }
            }
        });

        Bundle extrat = getIntent().getExtras();
        if (extrat == null){
            setTitle(R.string.new_note);
            mNewNote =true;
        }else {
            setTitle(R.string.edit_note);
            int notedId = extrat.getInt(NOTE_ID_KEY);
            mViewmodel.loadData(notedId);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewNote){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor, menu);


        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            saveAndReturn();
            return true;
        }else  if (item.getItemId() == R.id.action_delete){
            mViewmodel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
saveAndReturn();    }

    private void saveAndReturn() {
        mViewmodel.saveNote(noteText.getText().toString());
        finish();


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}
