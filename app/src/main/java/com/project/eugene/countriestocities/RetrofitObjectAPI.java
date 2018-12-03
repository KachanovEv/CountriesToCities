package com.project.eugene.countriestocities;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitObjectAPI {

    @GET("David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Call<HashMap<String, ArrayList<String>>> getCountryList();
}