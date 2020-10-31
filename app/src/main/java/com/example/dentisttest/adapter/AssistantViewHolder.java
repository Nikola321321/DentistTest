package com.example.dentisttest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.R;

class AssistantViewHolder extends RecyclerView.ViewHolder {
    private TextView firstName, lastName, chosenDentist;


    public AssistantViewHolder(@NonNull View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.tv_assistant_first_name);
        lastName = itemView.findViewById(R.id.tv_assistant_last_name);
        chosenDentist = itemView.findViewById(R.id.tv_assistant_chosen_dentist);
    }

    public TextView getFirstName() {
        return firstName;
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public TextView getChosenDentist() {
        return chosenDentist;
    }

    public void setChosenDentist(TextView chosenDentist) {
        this.chosenDentist = chosenDentist;
    }
}
