package com.example.dentisttest.rxBusTest;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class RxBusDentistUpdate {

    private static final BehaviorSubject<DentistAndPatients> behaviourSubject = BehaviorSubject.create();

    public static BehaviorSubject<DentistAndPatients> getBehaviourSubject() {
        return behaviourSubject;
    }
}
