package com.example.semen.jwtlogin.api;

import android.content.SharedPreferences;

import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.managers.PreferencesManager;
import com.example.semen.jwtlogin.model.Login;
import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    PreferencesManager preferencesManager = DataManager.getInstance().getPreferencesManager();
    String authToken = preferencesManager.getAuthToken();


    @POST("auth")
    Call<ResponseBody> login(@Body Login login);

    @GET("api")
    Call<List<Pet>> petList(@Header("Token") String token);
}
