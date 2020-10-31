package com.example.dentisttest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.R;

public class PatientViewHolder extends RecyclerView.ViewHolder {

    private TextView firstName, lastName, chosenDentist;

    public PatientViewHolder(@NonNull View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.tv_patient_first_name);
        lastName = itemView.findViewById(R.id.tv_patient_last_name);
        chosenDentist = itemView.findViewById(R.id.tv_chosen_dentist);
    }

    public TextView getFirstName() {
        return firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public TextView getChosenDentist() {
        return chosenDentist;
    }
}
