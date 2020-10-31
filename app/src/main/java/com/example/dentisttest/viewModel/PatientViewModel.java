package com.example.dentisttest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.repository.DentistRepository;
import com.example.dentisttest.repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;
    LiveData<List<Patient>> allPatients;
    LiveData<List<PatientAndDentist>> allPatientsAndDentists;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
        allPatientsAndDentists = patientRepository.getPatientsAndDetists();
    }

    public void insert (Patient patient){
        patientRepository.insert(patient);
    }

    public void delete (Patient patient) {
        patientRepository.delete(patient);
    }

    public void update (Patient patient) {
        patientRepository.update(patient);
    }

    public void deleteAllPatients () {
        patientRepository.deleteAllPatients();
    }

    public PatientAndDentist getPatientAndDentistById (int id){
        return patientRepository.getPatientAndDentistById(id);
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public LiveData<List<PatientAndDentist>> getPatientsAndDentists() {
        return allPatientsAndDentists;
    }
}
