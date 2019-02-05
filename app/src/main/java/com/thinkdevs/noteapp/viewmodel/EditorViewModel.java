package com.thinkdevs.noteapp.viewmodel;

import android.app.Application;

import com.thinkdevs.noteapp.database.AppRepository;
import com.thinkdevs.noteapp.database.NoteEntity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();

    private AppRepository mRepository;
    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }
}
