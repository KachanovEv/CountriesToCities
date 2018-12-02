package com.project.eugene.countriestocities;

import retrofit2.http.GET;
import java.util.List;
import retrofit2.Call;

public interface RetrofitArrayAPI {

    @GET("android/downloadcode/arrayfile.json")
    Call<List<Model>> getJsonArrayData();
}