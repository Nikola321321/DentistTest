package com.example.dentisttest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.repository.DentistRepository;

import java.util.List;

public class DentistViewModel extends AndroidViewModel {

    private DentistRepository dentistRepository;
    private LiveData<List<Dentist>> allDentists;
    private LiveData<List<DentistAndPatients>> allDentistsWithPatients;


    public DentistViewModel(@NonNull Application application) {
        super(application);
        dentistRepository = new DentistRepository(application);
        allDentists = dentistRepository.getAllDentists();
        allDentistsWithPatients = dentistRepository.getDentistsWithPatients();
    }

    public void insert(Dentist dentist) {
        dentistRepository.insert(dentist);
    }

    public void delete(Dentist dentist) {
        dentistRepository.delete(dentist);
    }

    public void deleteAllDentists() {
        dentistRepository.deleteAllDentists();
    }

    public void update(Dentist dentist) {
        dentistRepository.update(dentist);
    }

    public DentistAndPatients getDentistAndPatientsById(int id) {
        return dentistRepository.getDentistAndPatientsById(id);
    }

    public LiveData<List<Dentist>> getAllDentists() {
        return allDentists;
    }

    public LiveData<List<DentistAndPatients>> getDentistsWithPatients() {
        return allDentistsWithPatients;
    }
}
