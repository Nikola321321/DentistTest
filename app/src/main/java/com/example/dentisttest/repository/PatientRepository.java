package com.example.dentisttest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dentisttest.appDatabase.AppDatabase;
import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.person.personDao.PatientDao;

import java.util.List;

public class PatientRepository {

    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;

    public PatientRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        patientDao = db.patientDao();
        allPatients = patientDao.getAllPatients();

    }

    public void insert(Patient patient) {
        AppDatabase.dbExecutor.execute(() -> patientDao.insert(patient));
    }

    public void update (Patient patient){
        AppDatabase.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                patientDao.update(patient);
            }
        });
    }

    public void delete(Patient patient) {
        AppDatabase.dbExecutor.execute(() -> patientDao.delete(patient));
    }

    public void deleteAllPatients() {
        AppDatabase.dbExecutor.execute(() -> patientDao.deleteAllPatients());
    }

    public PatientAndDentist getPatientAndDentistById (int id){
        return patientDao.getPatientAndDentistById(id);
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }



    public LiveData<List<PatientAndDentist>> getPatientsAndDetists() {
        return patientDao.getPatientsAndDentists();
    }
}
