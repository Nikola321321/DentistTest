package com.example.dentisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.rxBusTest.RxBusPatient;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class PatientDetailActivity extends AppCompatActivity {

    private TextView firstName, lastName, address, chosenDentistName;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        firstName = findViewById(R.id.tv_patient_first_name);
        lastName = findViewById(R.id.tv_patient_last_name);
        address = findViewById(R.id.tv_patient_address);
        chosenDentistName = findViewById(R.id.tv_patient_chosen_dentist_name);

        disposable = RxBusPatient.getBehaviorSubject().subscribeWith(new DisposableObserver<PatientAndDentist>() {
            @Override
            public void onNext(@NonNull PatientAndDentist patientAndDentist) {
                if (patientAndDentist.dentist != null) {
                    firstName.setText(patientAndDentist.patient.getFirstName());
                    lastName.setText(patientAndDentist.patient.getLastName());
                    address.setText(patientAndDentist.patient.getAddress());
                    chosenDentistName.setText(patientAndDentist.dentist.getName());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        onAddressClick();
    }

    private void onAddressClick() {
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PatientDetailActivity.this, GoogleMapsActivity.class);
                intent.putExtra("address", address.getText().toString());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
