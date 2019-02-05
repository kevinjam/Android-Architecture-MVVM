package com.thinkdevs.noteapp.viewmodel;

import android.app.Application;

import com.thinkdevs.noteapp.database.AppRepository;
import com.thinkdevs.noteapp.database.NoteEntity;
import com.thinkdevs.noteapp.utilities.SampleData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {


    public LiveData<List<NoteEntity>> mNotes;

    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());

        mNotes = mRepository.mNotes;
    }

    public void addSampleData() {

        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();

    }
}
