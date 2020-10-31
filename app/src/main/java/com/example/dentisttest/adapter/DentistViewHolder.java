package com.example.dentisttest.adapter;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.DentistDetailActivity;
import com.example.dentisttest.MainActivity;
import com.example.dentisttest.R;

public class DentistViewHolder extends RecyclerView.ViewHolder {

    private TextView firstName, lastName, address;


    public DentistViewHolder(@NonNull View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.tv_dentist_first_name);
        lastName = itemView.findViewById(R.id.tv_dentist_last_name);
        address = itemView.findViewById(R.id.tv_dentist_address);

    }


    public TextView getFirstName() {
        return firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public TextView getAddress() {
        return address;
    }
}
