package com.example.sonia.uvapp.retrofits;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface webapi {



   @POST("uvapp/signup")
    Call<Response> signup(@Body  Data datos);



}
