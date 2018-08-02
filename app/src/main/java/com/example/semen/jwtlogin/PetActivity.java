package com.example.semen.jwtlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.semen.jwtlogin.adapter.PetAdapter;
import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

public class PetActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        pets = (List<Pet>) getIntent().getSerializableExtra("start");

        recyclerView = findViewById(R.id.pets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PetAdapter(pets));
    }
}
