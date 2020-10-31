package com.example.dentisttest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.viewModel.DentistViewModel;
import com.example.dentisttest.viewModel.PatientViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddPatientActivity extends AppCompatActivity {

    private static final int DEFAULT_VALUE = -1;

    private int extraPatientId;

    private EditText firstName, lastName, address;
    private Button addNewPatientButton;
    private Spinner dentistSpinner;
    private PatientViewModel patientViewModel;
    private DentistViewModel dentistViewModel;

    List<Dentist> dentistList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();

    PatientAndDentist patientAndDentistById;
    Dentist dentist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        firstName = findViewById(R.id.et_patient_first_name);
        lastName = findViewById(R.id.et_patient_last_name);
        address = findViewById(R.id.et_patient_address);
        dentistSpinner = findViewById(R.id.spinner_patient_dentist_id);
        addNewPatientButton = findViewById(R.id.btn_insert_dentist);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        dentistViewModel = ViewModelProviders.of(this).get(DentistViewModel.class);
        extraPatientId = getIntent().getIntExtra("patientId",DEFAULT_VALUE);


        dentistViewModel.getAllDentists().observe(this, new Observer<List<Dentist>>() {
            @Override
            public void onChanged(List<Dentist> dentists) {
                dentistList = dentists;
                nameList = new ArrayList<>();

                for (Dentist dentist : dentistList) {
                    nameList.add(dentist.getFirstName() + " " + dentist.getLastName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, nameList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dentistSpinner.setAdapter(adapter);
            }
        });

        if (extraPatientId != DEFAULT_VALUE){
            addNewPatientButton.setText("UPDATE PATIENT INFO");
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    patientAndDentistById = patientViewModel.getPatientAndDentistById(extraPatientId);
                    firstName.setText(patientAndDentistById.patient.getFirstName());
                    lastName.setText(patientAndDentistById.patient.getLastName());
                    address.setText(patientAndDentistById.patient.getAddress());
                }
            });
        }

        dentistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dentist = dentistList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addNewPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstName.getText().toString().trim().equals("")&&!lastName.getText().toString().trim().equals("") && extraPatientId == DEFAULT_VALUE){
                    patientViewModel.insert(new Patient(firstName.getText().toString(),lastName.getText().toString(), address.getText().toString(),dentist.getDentistId()));
                    finish();
                } else if (!firstName.getText().toString().trim().equals("")&&!lastName.getText().toString().trim().equals("") && extraPatientId != DEFAULT_VALUE){
                    patientAndDentistById.patient.setFirstName(firstName.getText().toString());
                    patientAndDentistById.patient.setLastName(lastName.getText().toString());
                    patientAndDentistById.patient.setPatientDentistId(dentist.getDentistId());
                    patientAndDentistById.patient.setAddress(address.getText().toString());
                    patientViewModel.update(patientAndDentistById.patient);
                    finish();
                }
            }
        });

    }




}
