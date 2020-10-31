package com.example.dentisttest.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dentisttest.AddPatientActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.adapter.PatientAdapter;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.viewModel.PatientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment {

    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private PatientViewModel patientViewModel;
    private FloatingActionButton fabAddNewPatient;

    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_patient);
        adapter = new PatientAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        fabAddNewPatient = view.findViewById(R.id.fab_add_patient);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patientViewModel.getPatientsAndDentists().observe(this, new Observer<List<PatientAndDentist>>() {
            @Override
            public void onChanged(List<PatientAndDentist> patientAndDentists) {
                adapter.setAllPatientAndDentist(patientAndDentists);
            }
        });

        fabAddNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddPatientActivity.class));
            }
        });
    }
}
