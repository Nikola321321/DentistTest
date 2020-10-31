package com.example.dentisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dentisttest.person.entityRelations.AssistantAndDentist;
import com.example.dentisttest.rxBusTest.RxBusAssistant;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AssistantDetailActivity extends AppCompatActivity {

    private TextView firstName, lastName, address, chosenDentistName;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_detail);

        firstName = findViewById(R.id.tv_assistant_first_name);
        lastName = findViewById(R.id.tv_assistant_last_name);
        address = findViewById(R.id.tv_assistant_address);
        chosenDentistName = findViewById(R.id.tv_assistant_chosen_dentist_name);


        disposable = RxBusAssistant.getBehaviourSubject().subscribeWith(new DisposableObserver<AssistantAndDentist>() {
            @Override
            public void onNext(@NonNull AssistantAndDentist assistantAndDentist) {
                if (assistantAndDentist.dentist != null) {
                    firstName.setText(assistantAndDentist.assistant.getFirstName());
                    lastName.setText(assistantAndDentist.assistant.getLastName());
                    address.setText(assistantAndDentist.assistant.getAddress());
                    chosenDentistName.setText(assistantAndDentist.dentist.getName());
                } else chosenDentistName.setText("Dentist not chosen!");

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

                Intent intent = new Intent(AssistantDetailActivity.this, GoogleMapsActivity.class);
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
