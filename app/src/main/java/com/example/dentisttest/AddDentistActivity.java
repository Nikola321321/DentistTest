package com.example.dentisttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.viewModel.DentistViewModel;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddDentistActivity extends AppCompatActivity {

    public static final int DEFAULT_VALUE = -1;

    int dentistId;


    private EditText firstName, lastName, address;
    private Button addNewDentistButton;
    private Button btnChoosePhoto;


    private DentistViewModel dentistViewModel;
    private DentistAndPatients dentistAndPatients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dentist);

        firstName = findViewById(R.id.et_dentist_first_name);
        lastName = findViewById(R.id.et_dentist_last_name);
        address = findViewById(R.id.et_dentist_address);

        addNewDentistButton = findViewById(R.id.btn_insert_dentist);

        dentistId = getIntent().getIntExtra("dentistId", DEFAULT_VALUE);


        dentistViewModel = ViewModelProviders.of(this).get(DentistViewModel.class);

        if (dentistId != DEFAULT_VALUE) {
            addNewDentistButton.setText("UPDATE DENTIST INFO");
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    dentistAndPatients = dentistViewModel.getDentistAndPatientsById(dentistId);
                    firstName.setText(dentistAndPatients.dentist.getFirstName());
                    lastName.setText(dentistAndPatients.dentist.getLastName());
                    address.setText(dentistAndPatients.dentist.getAddress());
                }
            });
        }

        addNewDentistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstName.getText().toString().trim().equals("") && !lastName.getText().toString().trim().equals("") && dentistId == DEFAULT_VALUE) {
                    dentistViewModel.insert(new Dentist(firstName.getText().toString(), lastName.getText().toString(), address.getText().toString()));
                    finish();
                } else if (!firstName.getText().toString().trim().equals("") && !lastName.getText().toString().trim().equals("") && dentistId != DEFAULT_VALUE) {
                    dentistAndPatients.dentist.setFirstName(firstName.getText().toString());
                    dentistAndPatients.dentist.setLastName(lastName.getText().toString());
                    dentistAndPatients.dentist.setAddress(address.getText().toString());
                    dentistViewModel.update(dentistAndPatients.dentist);
                    finish();
                }
            }
        });
    }

}
