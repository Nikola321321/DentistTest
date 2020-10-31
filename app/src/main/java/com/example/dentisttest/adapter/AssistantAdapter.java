package com.example.dentisttest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentisttest.AssistantDetailActivity;
import com.example.dentisttest.R;
import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.entityRelations.AssistantAndDentist;
import com.example.dentisttest.rxBusTest.RxBusAssistant;

import java.util.ArrayList;
import java.util.List;

public class AssistantAdapter extends RecyclerView.Adapter<AssistantViewHolder> {

    private List<Assistant> allAssistants = new ArrayList<>();
    private List<AssistantAndDentist> allAssistantsAndDentists = new ArrayList<>();


    @NonNull
    @Override
    public AssistantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assistant_card, parent, false);

        return new AssistantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssistantViewHolder holder, int position) {
        AssistantAndDentist assistantAndDentist = allAssistantsAndDentists.get(position);
        holder.getFirstName().setText(assistantAndDentist.assistant.getFirstName());
        holder.getLastName().setText(assistantAndDentist.assistant.getLastName());
        if (assistantAndDentist.dentist != null) {
            holder.getChosenDentist().setText(assistantAndDentist.dentist.getName());
        } else {
            holder.getChosenDentist().setText("Dentist not assigned!");
        }
        asssistantDetail(holder, assistantAndDentist);

    }

    @Override
    public int getItemCount() {
        return allAssistantsAndDentists.size();
    }

    public void setAllAssistants(List<Assistant> allAssistants) {
        this.allAssistants = allAssistants;
    }

    public void setAllAssistantsAndDentists(List<AssistantAndDentist> allAssistantsAndDentists) {
        this.allAssistantsAndDentists = allAssistantsAndDentists;
        notifyDataSetChanged();
    }

    private void asssistantDetail(@NonNull AssistantViewHolder holder, AssistantAndDentist assistantAndDentist) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusAssistant.getBehaviourSubject().onNext(assistantAndDentist);
                holder.itemView.getContext().startActivity(new Intent(
                        holder.itemView.getContext(), AssistantDetailActivity.class
                ));
            }
        });
    }
}
