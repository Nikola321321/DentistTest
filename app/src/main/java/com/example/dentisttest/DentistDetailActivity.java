package com.example.dentisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.rxBusTest.RxBusDentist;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class DentistDetailActivity extends AppCompatActivity {


    private TextView firstName, lastName, address;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_detail);

        firstName = findViewById(R.id.tv_detail_first_name);
        lastName = findViewById(R.id.tv_detail_last_name);
        address = findViewById(R.id.tv_detail_address);

        disposable = RxBusDentist.getBehaviourSubject().subscribeWith(new DisposableObserver<DentistAndPatients>() {
            @Override
            public void onNext(@NonNull DentistAndPatients dentistAndPatients) {
                firstName.setText(dentistAndPatients.dentist.getFirstName());
                lastName.setText(dentistAndPatients.dentist.getLastName());
                address.setText(dentistAndPatients.dentist.getAddress());
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

                Intent intent = new Intent(DentistDetailActivity.this, GoogleMapsActivity.class);
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

