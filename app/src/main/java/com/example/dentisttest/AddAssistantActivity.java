package com.example.dentisttest;

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

import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.viewModel.AssistantViewModel;
import com.example.dentisttest.viewModel.DentistViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddAssistantActivity extends AppCompatActivity {

    private EditText firstName, lastName, address;
    private Button addNewAssistantButton;
    private Spinner dentistId;
    private AssistantViewModel assistantViewModel;
    private DentistViewModel dentistViewModel;
    private List<Dentist> allDentists = new ArrayList<>();
    private List<String> dentistNames = new ArrayList<>();
    private Dentist dentist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assistant);

        firstName = findViewById(R.id.et_assistant_first_name);
        lastName = findViewById(R.id.et_assistant_last_name);
        address = findViewById(R.id.et_assistant_address);
        dentistId = findViewById(R.id.spinner_assistant_dentist_id);
        addNewAssistantButton = findViewById(R.id.btn_insert_assistant);
        assistantViewModel = ViewModelProviders.of(this).get(AssistantViewModel.class);
        dentistViewModel = ViewModelProviders.of(this).get(DentistViewModel.class);

        dentistViewModel.getAllDentists().observe(this, new Observer<List<Dentist>>() {
            @Override
            public void onChanged(List<Dentist> dentists) {
                allDentists = dentists;
                for (Dentist dentist : dentists) {
                    dentistNames.add(dentist.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, dentistNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dentistId.setAdapter(adapter);
            }
        });
        addNewAssistantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAssistant(new Assistant(firstName.getText().toString(), lastName.getText().toString(), address.getText().toString(), dentist.getDentistId()));
            }
        });

        dentistId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dentist = allDentists.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void insertAssistant(Assistant assistant) {
        if (!assistant.getFirstName().trim().equals("") && !assistant.getLastName().trim().equals("")) {
            assistantViewModel.insert(assistant);
        } else {
            Toast.makeText(this, "Enter first name and last name!", Toast.LENGTH_SHORT).show();
            return;
        }
        finish();
    }

}
