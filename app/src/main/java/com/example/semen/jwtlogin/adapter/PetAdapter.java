package com.example.semen.jwtlogin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.semen.jwtlogin.R;
import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private List<Pet> pets;

    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public PetAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);

        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.PetViewHolder holder, int position) {
        holder.id.setText("id: " + String.valueOf(pets.get(position).getId()));
        holder.name.setText("name: " + pets.get(position).getName());
        holder.age.setText("age: " + String.valueOf(pets.get(position).getAge()));
        holder.userId.setText("userId: " + String.valueOf(pets.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView age;
        TextView userId;

        public PetViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            userId = itemView.findViewById(R.id.userId);
        }
    }
}
