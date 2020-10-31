package com.example.dentisttest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dentisttest.appDatabase.AppDatabase;
import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.person.personDao.DentistDao;

import java.util.List;

public class DentistRepository {

    private DentistDao dentistDao;
    private LiveData<List<Dentist>> allDentists;
    private LiveData<List<DentistAndPatients>> allDentistAndPatients;

    public DentistRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dentistDao = db.dentistDao();
        allDentists = dentistDao.getAllDentists();
        allDentistAndPatients = dentistDao.getDentistsWithPatients();
    }


    public void insert(Dentist dentist) {
        AppDatabase.dbExecutor.execute(() -> dentistDao.insert(dentist));
    }

    public void delete(Dentist dentist) {
        AppDatabase.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dentistDao.delete(dentist);
            }
        });
    }

    public void update (Dentist dentist) {
        AppDatabase.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dentistDao.update(dentist);
            }
        });
    }

    public DentistAndPatients getDentistAndPatientsById(int id) {
        return dentistDao.getDentistAndPatientsById(id);
    }

    public void deleteAllDentists() {
        AppDatabase.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dentistDao.deleteAllDentists();
            }
        });
    }

    public LiveData<List<Dentist>> getAllDentists() {
        return allDentists;
    }


    public LiveData<List<DentistAndPatients>> getDentistsWithPatients() {
        return allDentistAndPatients;
    }

    public List<Dentist> getDentists(List<Integer> ids)
    {
        return dentistDao.getDentists(ids);
    }
}
