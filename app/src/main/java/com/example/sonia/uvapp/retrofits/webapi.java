package com.example.sonia.uvapp.retrofits;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface webapi {



   @POST("uvapp/signup")
    Call<Response> signup(@Body  Data datos);


   @GET("uvapp/delete/{nick}")
    Call<Response> deleteUser(@Path("nick") String nick);


}
