package com.example.semen.jwtlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.semen.jwtlogin.adapter.PetAdapter;
import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.model.DaoSession;
import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

public class PetActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public List<Pet> pets = null;
    public DataManager dataManager;
    public DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

//        pets = (List<Pet>) getIntent().getSerializableExtra("start");

        dataManager = DataManager.getInstance();
        daoSession = dataManager.getDaoSession();
        pets = daoSession.getPetDao().loadAll();

        recyclerView = findViewById(R.id.pets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PetAdapter(pets));
    }
}
