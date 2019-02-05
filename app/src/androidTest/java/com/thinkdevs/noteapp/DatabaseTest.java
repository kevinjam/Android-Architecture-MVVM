package com.thinkdevs.noteapp;

import android.content.Context;
import android.util.Log;

import com.thinkdevs.noteapp.database.AppDatabase;
import com.thinkdevs.noteapp.database.NoteDao;
import com.thinkdevs.noteapp.database.NoteEntity;
import com.thinkdevs.noteapp.utilities.SampleData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG ="Junit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public  void createDb(){
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.i(TAG   , "CREATE DB");
    }
    void closeDb(){
        mDb.close();
        Log.i(TAG   , "close DB");

    }

    @Test
    public void createAndRetriveNotes(){
        mDao.insertAll(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG   , "createAndRetriveNotes " + count);

        assertEquals(SampleData.getNotes().size(), count);

    }


    @Test
    public void compareString(){
        mDao.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDao.getNotesById(1);
        assertEquals(original.getText(), fromDb.getText());
        assertEquals(1, fromDb.getId());

    }
}
