package com.thinkdevs.noteapp.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntity);

    @Delete
    void deleteNotes(NoteEntity noteEntity);


    //retrieve single node
    @Query("SELECT * FROM notes WHERE id=:id")
    NoteEntity getNotesById(int id);


    @Query("SELECT * FROM notes ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAll();


    @Query("DELETE FROM notes")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();

}
