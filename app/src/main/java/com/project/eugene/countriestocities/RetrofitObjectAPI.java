package com.project.eugene.countriestocities;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitObjectAPI {

    @GET("android/downloadcode/objectfile.json")
    Call<Model> getJsonObjectData();
}