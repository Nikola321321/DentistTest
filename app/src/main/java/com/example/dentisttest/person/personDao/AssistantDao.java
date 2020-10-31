package com.example.dentisttest.person.personDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;

import java.util.List;

@Dao
public interface AssistantDao {

    @Insert
    void insert (Assistant assistant);

    @Delete
    void delete (Assistant assistant);

    @Query("DELETE FROM assistant_table")
    void deleteAllAssistants ();

    @Query("SELECT * FROM assistant_table ORDER BY firstName ASC")
    LiveData<List<Assistant>> getAllAssistants();

    @Transaction
    @Query("Select * from assistant_table")
    LiveData<List<AssistantAndDentist>> getAssistantsAndDentists();

}
