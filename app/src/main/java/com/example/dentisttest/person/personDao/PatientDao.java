package com.example.dentisttest.person.personDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Delete
    void delete(Patient patient);

    @Update
    void update (Patient patient);

    @Query("SELECT * FROM patient_table WHERE patientId = :id")
    @Transaction
    PatientAndDentist getPatientAndDentistById (int id);

    @Query("DELETE FROM patient_table")
    void deleteAllPatients();

    @Query("SELECT * FROM patient_table ORDER BY firstName ASC")
    LiveData<List<Patient>> getAllPatients();

    @Transaction
    @Query("select * from patient_table")
    LiveData<List<PatientAndDentist>> getPatientsAndDentists();
}
