package com.example.dentisttest.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dentisttest.AddAssistantActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.adapter.AssistantAdapter;
import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;
import com.example.dentisttest.viewModel.AssistantViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssistantFragment extends Fragment {

    private RecyclerView recyclerView;
    private AssistantAdapter adapter;
    private AssistantViewModel assistantViewModel;
    private FloatingActionButton fabAddAssistant;


    public AssistantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assistant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_assistant);
        adapter = new AssistantAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        fabAddAssistant = view.findViewById(R.id.fab_add_assistant);

        assistantViewModel = ViewModelProviders.of(this).get(AssistantViewModel.class);

        assistantViewModel.getAssistantsAndDentists().observe(this, new Observer<List<AssistantAndDentist>>() {
            @Override
            public void onChanged(List<AssistantAndDentist> assistantAndDentists) {
                adapter.setAllAssistantsAndDentists(assistantAndDentists);
            }
        });
        fabAddAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddAssistantActivity.class));
            }
        });

    }
}
