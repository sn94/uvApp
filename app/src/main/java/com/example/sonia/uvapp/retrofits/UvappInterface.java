package com.example.sonia.uvapp.retrofits;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UvappInterface {
    @Multipart
   @POST("phototype")
   Call<UvappResponse> phototype( @Part("file\"; filename=\"pp.png\" ") RequestBody file);



}
