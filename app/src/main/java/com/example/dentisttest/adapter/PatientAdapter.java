package com.example.dentisttest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.AddPatientActivity;
import com.example.dentisttest.PatientDetailActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.entityRelations.PatientAndDentist;
import com.example.dentisttest.rxBusTest.RxBusPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientViewHolder> {

    private List<Patient> allPatients = new ArrayList<>();
    private List<PatientAndDentist> allPatientAndDentist = new ArrayList<>();
    private PopupMenu popupMenu;

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_card, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        PatientAndDentist patientAndDentist = allPatientAndDentist.get(position);
        holder.getFirstName().setText(patientAndDentist.patient.getFirstName());
        holder.getLastName().setText(patientAndDentist.patient.getLastName());
        if (patientAndDentist.dentist != null) {
            holder.getChosenDentist().setText(patientAndDentist.dentist.getName());
        } else {
            holder.getChosenDentist().setText("Dentist not selected!");
        }

        popupMenu = new PopupMenu(holder.itemView.getContext(),holder.itemView);
        popupMenu.inflate(R.menu.long_click_menu);
        longPopupClickListener(holder);

        patientDetails(holder, patientAndDentist);
    }

    private void popupMenuItemClickListener(PatientViewHolder holder) {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_update:
                        Intent intent = new Intent(holder.itemView.getContext(), AddPatientActivity.class);
                        intent.putExtra("patientId", allPatientAndDentist.get(holder.getAdapterPosition()).patient.getPatientId());
                        holder.itemView.getContext().startActivity(intent);
                }
                return true;
            }
        });
    }

    private void longPopupClickListener(@NonNull PatientViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popupMenu.show();
                popupMenuItemClickListener(holder);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPatientAndDentist.size();
    }

    public void setAllPatients(List<Patient> allPatients) {
        this.allPatients = allPatients;
        notifyDataSetChanged();
    }

    public void setAllPatientAndDentist(List<PatientAndDentist> allPatientsAndDentist) {
        this.allPatientAndDentist = allPatientsAndDentist;
        notifyDataSetChanged();
    }

    private void patientDetails(PatientViewHolder holder, PatientAndDentist patientAndDentist) {
        holder.itemView.setOnClickListener(v -> {
            RxBusPatient.getBehaviorSubject().onNext(patientAndDentist);
            holder.itemView.getContext().startActivity(new Intent(
                    holder.itemView.getContext(), PatientDetailActivity.class
            ));
        });
    }

}
