package com.example.sonia.uvapp.retrofits;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {

        public static String host="192.168.0.12", puerto="8080";

        public static final String BASE_URL = "http://"+host+":"+puerto+"/";

        private static Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create());

        private static Retrofit retrofit = builder.build();





//Service builder that creates implementation of those interfaces is created here.

        public static <T> T buildService(Class<T> type) {


            return retrofit.create(type);
        }
}