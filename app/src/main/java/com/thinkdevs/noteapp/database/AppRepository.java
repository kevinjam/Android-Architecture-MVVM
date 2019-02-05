package com.thinkdevs.noteapp.database;

import android.content.Context;

import com.thinkdevs.noteapp.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class AppRepository {
    private static  AppRepository ourInstance;
    public LiveData<List<NoteEntity>> mNotes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();


    public static AppRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNotes();

    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("----- " +SampleData.getNotes());

                mDb.noteDao().insertAll(SampleData.getNotes());

            }
        });

    }

    private LiveData<List<NoteEntity>> getAllNotes(){
        return mDb.noteDao().getAll();
    }

    //region Delete All NOtes
    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(final int notedId) {

        return  mDb.noteDao().getNotesById(notedId);


    }

    public void insertNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.print("Notes ## " +note);
                mDb.noteDao().insertNote(note);
            }
        });
    }

    public void deleteNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteNotes(note);
            }
        });
    }
    //endregion
}
