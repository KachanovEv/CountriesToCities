package com.project.eugene.countriestocities;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by x_prt on 20.09.18
 */
public class CountryListService extends IntentService {

    public CountryListService() {
        super("CountryListService");
    }

    private Call<HashMap<String, ArrayList<String>>> getCountryListCall() {
        RetrofitObjectAPI api = RetrofitInstance.createRetrofitObjectAPIService();
        return api.getCountryList();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ResultReceiver receiver = (ResultReceiver) Objects.requireNonNull(
                Objects.requireNonNull(intent).getExtras()).get(Constants.RECEIVER_KEY);

        if (receiver != null) {
            try {
                Call<HashMap<String, ArrayList<String>>> countryListCall = getCountryListCall();
                Response<HashMap<String, ArrayList<String>>> response = countryListCall.execute();

                Bundle b = new Bundle();
                b.putSerializable(Constants.COUNTRY_MAP_KEY, response.body());

                receiver.send(Constants.SUCCESS_RESULT, b);
            } catch (IOException e) {
                e.printStackTrace();
                receiver.send(Constants.FAIL_RESULT, null);
            }
        }
    }
}
