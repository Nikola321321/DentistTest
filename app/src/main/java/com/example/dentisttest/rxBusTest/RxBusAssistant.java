package com.example.dentisttest.rxBusTest;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class RxBusAssistant {

    private static final BehaviorSubject<AssistantAndDentist> behaviourSubject = BehaviorSubject.create();

    public static BehaviorSubject<AssistantAndDentist> getBehaviourSubject() {
        return behaviourSubject;
    }
}
