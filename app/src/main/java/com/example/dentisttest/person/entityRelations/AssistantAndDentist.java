package com.example.dentisttest.person.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.Dentist;

public class AssistantAndDentist {

    @Embedded
    public Assistant assistant;

    @Relation(
           parentColumn = "toDentist",
            entityColumn = "dentistId"
    )
    public Dentist dentist;
}
