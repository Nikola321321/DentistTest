package com.example.dentisttest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.AddDentistActivity;
import com.example.dentisttest.DentistDetailActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.rxBusTest.RxBusDentist;
import com.example.dentisttest.rxBusTest.RxBusDentistUpdate;

import java.util.ArrayList;
import java.util.List;

public class DentistAdapter extends RecyclerView.Adapter<DentistViewHolder> {

    private List<DentistAndPatients> allDentistsWithPatients = new ArrayList<>();
    private DentistAndPatients dentistAndPatients;
    private PopupMenu popupMenu;

    @NonNull
    @Override
    public DentistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dentist_card, parent, false);
        return new DentistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DentistViewHolder holder, int position) {
        dentistAndPatients = allDentistsWithPatients.get(position);
        holder.getFirstName().setText(dentistAndPatients.dentist.getFirstName());
        holder.getLastName().setText(dentistAndPatients.dentist.getLastName());
        holder.getAddress().setText(dentistAndPatients.dentist.getAddress());
        showDetails(holder, dentistAndPatients);

        popupMenu = new PopupMenu(holder.itemView.getContext(), holder.itemView);
        popupMenu.inflate(R.menu.long_click_menu);
        longPopupClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return allDentistsWithPatients.size();
    }

    private void longPopupClickListener(@NonNull DentistViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popupMenu.show();
                popupMenuItemClickListener(holder);
                return true;
            }
        });
    }

    private void popupMenuItemClickListener(@NonNull DentistViewHolder holder) {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_update:
                        Intent intent = new Intent(holder.itemView.getContext(),AddDentistActivity.class);
                        intent.putExtra("dentistId", allDentistsWithPatients.get(holder.getAdapterPosition()).dentist.getDentistId());
                        holder.itemView.getContext().startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    private void showDetails(@NonNull DentistViewHolder holder, DentistAndPatients dentist) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusDentist.getBehaviourSubject().onNext(dentist);
                holder.itemView.getContext().startActivity(new Intent(
                        holder.itemView.getContext(), DentistDetailActivity.class
                ));
            }
        });
    }

    public void setAllDentistsWithPatients(List<DentistAndPatients> allDentists) {
        this.allDentistsWithPatients = allDentists;
        notifyDataSetChanged();
    }

    public DentistAndPatients getDentistAndPatients() {
        return dentistAndPatients;
    }

    public List<DentistAndPatients> getAllDentistsAndPatients() {
        return allDentistsWithPatients;
    }
}
