package com.project.eugene.countriestocities;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    public static Retrofit retrofit;
    private static final String BASE_URL = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";

    public void getRetrofitObject(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
            Call<Model> call = service.getJsonObjectData();
            call.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    Log.e(" mainAction", "  response "+ response.body().toString());
                    Log.e(" mainAction", "  rom - "+ response.body().getcity());

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Log.e("MainActivity ", "  error "+ t.toString());

                }




            });

        }
        public void getRetrofitArray(){

        }
        }



