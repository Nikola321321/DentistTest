package com.example.dentisttest.person.personDao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;

import java.util.List;

@Dao
public interface DentistDao {

    @Insert
    void insert(Dentist dentist);

    @Delete
    void delete(Dentist dentist);

    @Update
    void update (Dentist dentist);

    @Query("DELETE FROM dentist_table")
    void deleteAllDentists();

    @Query("SELECT * FROM dentist_table WHERE dentistId = :id")
    @Transaction
    DentistAndPatients getDentistAndPatientsById(int id);

    @Query("select * from dentist_table where dentistId in (:ids)")
    List<Dentist> getDentists(List<Integer> ids);

    @Transaction
    @Query("select * from dentist_table")
    LiveData<List<DentistAndPatients>> getDentistsWithPatients();

    @Query("select * from dentist_table order by firstName asc")
    LiveData<List<Dentist>> getAllDentists();
}
