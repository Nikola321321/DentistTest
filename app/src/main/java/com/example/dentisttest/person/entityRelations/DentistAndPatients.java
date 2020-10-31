package com.example.dentisttest.person.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.Patient;

import java.util.List;

public class DentistAndPatients {

    @Embedded
    public Dentist dentist;

    @Relation(
            parentColumn = "dentistId",
            entityColumn = "patientDentistId")
    public List<Patient> patients;
}
