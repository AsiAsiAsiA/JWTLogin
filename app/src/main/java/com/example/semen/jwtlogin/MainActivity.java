package com.example.semen.jwtlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semen.jwtlogin.api.Api;
import com.example.semen.jwtlogin.controller.Controller;
import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.managers.PreferencesManager;
import com.example.semen.jwtlogin.model.Login;
import com.example.semen.jwtlogin.model.Pet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String token = null;
    public Button login;
    public Button list;
    public Api api = Controller.getApi();
    public List<Pet> pets;

    public EditText login_form;
    public EditText password;
    public String loginString;
    public String passwordString;

    public PreferencesManager preferencesManager = DataManager.getInstance().getPreferencesManager();
    public DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        list = findViewById(R.id.list);
        login_form = findViewById(R.id.login_form);
        password = findViewById(R.id.password);

        pets = new ArrayList<>();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }

    void list() {
        Call<List<Pet>> call = api.petList(token);

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                pets.addAll(response.body());

                Intent intent = new Intent(getApplicationContext(), PetActivity.class);
                intent.putExtra("start", (Serializable) pets);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void login() {
        loginString = login_form.getText().toString();
        passwordString = password.getText().toString();

        Login login = new Login(loginString, passwordString);
        System.out.println(loginString);

        Call<ResponseBody> call = api.login(login);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    token = response.headers().get("access-token");
                    preferencesManager.setAuthToken(token);
                    System.out.println(preferencesManager.getAuthToken());
                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
