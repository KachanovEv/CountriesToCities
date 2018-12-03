package com.project.eugene.countriestocities;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static RetrofitObjectAPI retrofitObjectAPI;

    public static RetrofitObjectAPI createRetrofitObjectAPIService() {
        if (retrofitObjectAPI == null) {
            retrofitObjectAPI = prepareApi();
        }
        return retrofitObjectAPI;
    }

    private static RetrofitObjectAPI prepareApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        addLogging(httpClientBuilder);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_URL)
                .client(httpClientBuilder.build());
        return retrofitBuilder.build().create(RetrofitObjectAPI.class);
    }

    private static void addLogging(OkHttpClient.Builder okHttpClient) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(interceptor);
        }
    }
}



