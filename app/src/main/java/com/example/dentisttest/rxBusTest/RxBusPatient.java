package com.example.dentisttest.rxBusTest;

import com.example.dentisttest.person.entityRelations.PatientAndDentist;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class RxBusPatient {

    private static final BehaviorSubject<PatientAndDentist> behaviorSubject = BehaviorSubject.create();

    public static BehaviorSubject<PatientAndDentist> getBehaviorSubject() {
        return behaviorSubject;
    }
}
