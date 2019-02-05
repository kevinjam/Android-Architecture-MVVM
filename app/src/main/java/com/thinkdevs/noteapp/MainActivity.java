package com.thinkdevs.noteapp;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.thinkdevs.noteapp.database.NoteEntity;
import com.thinkdevs.noteapp.ui.NoteAdapter;
import com.thinkdevs.noteapp.utilities.SampleData;
import com.thinkdevs.noteapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<NoteEntity> notesData = new ArrayList<>();
    private NoteAdapter madapter;

    private MainViewModel mViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView= findViewById(R.id.recycler_view);


//        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);

            }
        });

        notesData.addAll(mViewModel.mNotes);

        for(NoteEntity note:notesData){
            Log.i("Notes----", note.toString());
        }

    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        madapter = new NoteAdapter(notesData, this);
        recyclerView.setAdapter(madapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampledata();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //region ADD SAMPLE DATA
    private void addSampledata() {
        mViewModel.addSampleData();

    }
    //endregion
}
