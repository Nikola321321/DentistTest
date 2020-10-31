package com.example.dentisttest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;
import com.example.dentisttest.repository.AssistantRepository;
import com.example.dentisttest.repository.DentistRepository;

import java.util.List;

public class AssistantViewModel extends AndroidViewModel {

    AssistantRepository assistantRepository;
    LiveData<List<Assistant>> allAssistants;
    LiveData<List<AssistantAndDentist>> allAssistantsAndDentists;

    public AssistantViewModel(@NonNull Application application) {
        super(application);
        assistantRepository = new AssistantRepository(application);
        allAssistants = assistantRepository.getAllAssistants();
        allAssistantsAndDentists = assistantRepository.getAssistantsAndDentists();
    }

    public void insert (Assistant assistant) {
        assistantRepository.insert(assistant);
    }

    public void delete (Assistant assistant) {
        assistantRepository.delete(assistant);
    }

    public void deleteAllAssistants () {
        assistantRepository.deleteAllAssistants();
    }

    public LiveData<List<Assistant>> getAllAssistants() {
        return allAssistants;
    }

    public LiveData<List<AssistantAndDentist>> getAssistantsAndDentists(){
        return allAssistantsAndDentists;
    }
}
