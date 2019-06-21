package com.example.sonia.uvapp.retrofits;


import retrofit2.Call;
import retrofit2.http.GET;

public interface UvappInterface {



   @GET("phototype")
    Call<UvappResponse> phototype();



}
