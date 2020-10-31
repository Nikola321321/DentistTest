package com.example.dentisttest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dentisttest.appDatabase.AppDatabase;
import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;
import com.example.dentisttest.person.personDao.AssistantDao;

import java.util.List;

public class AssistantRepository {

    private AssistantDao assistantDao;
    private LiveData<List<Assistant>> allAssistants;

    public AssistantRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        assistantDao = db.assistantDao();
        allAssistants = assistantDao.getAllAssistants();

    }


    public void insert(Assistant assistant) {
        AppDatabase.dbExecutor.execute(() -> assistantDao.insert(assistant));
    }

    public void delete(Assistant assistant) {
        AppDatabase.dbExecutor.execute(() -> assistantDao.delete(assistant));
    }

    public void deleteAllAssistants() {
        AppDatabase.dbExecutor.execute(() -> assistantDao.deleteAllAssistants());
    }

    public LiveData<List<Assistant>> getAllAssistants() {
        return allAssistants;
    }

    public LiveData<List<AssistantAndDentist>> getAssistantsAndDentists(){
        return assistantDao.getAssistantsAndDentists();
    }
}
