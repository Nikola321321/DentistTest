package com.example.dentisttest.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.dentisttest.AddDentistActivity;
import com.example.dentisttest.DentistDetailActivity;
import com.example.dentisttest.MainActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.adapter.DentistAdapter;
import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.entityRelations.DentistAndPatients;
import com.example.dentisttest.rxBusTest.RxBusDentist;
import com.example.dentisttest.viewModel.DentistViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DentistFragment extends Fragment {
    private RecyclerView recyclerView;
    private DentistAdapter adapter;
    private DentistViewModel dentistViewModel;
    private FloatingActionButton btnAddDentist;

    public DentistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dentist, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_dentist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new DentistAdapter();
        recyclerView.setAdapter(adapter);

        dentistViewModel = ViewModelProviders.of(this).get(DentistViewModel.class);

        dentistViewModel.getDentistsWithPatients().observe(getActivity(), new Observer<List<DentistAndPatients>>() {
            @Override
            public void onChanged(List<DentistAndPatients> dentistAndPatients) {
                adapter.setAllDentistsWithPatients(dentistAndPatients);
            }
        });

        btnAddDentist = view.findViewById(R.id.fab_add_dentist);
        btnAddDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddDentistActivity.class));
            }
        });

        ItemTouchHelper.SimpleCallback cardSwipe = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dentistViewModel.delete(adapter.getAllDentistsAndPatients().get(viewHolder.getAdapterPosition()).dentist);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(cardSwipe);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
