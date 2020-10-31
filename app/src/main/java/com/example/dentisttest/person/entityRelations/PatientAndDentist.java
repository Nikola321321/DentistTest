package com.example.dentisttest.person.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.Patient;

public class PatientAndDentist {
    @Embedded
    public Patient patient;

    @Relation(
            parentColumn = "patientDentistId",
            entityColumn = "dentistId"
    )
    public Dentist dentist;
}
