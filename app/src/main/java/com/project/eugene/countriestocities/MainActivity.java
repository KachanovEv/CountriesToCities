package com.project.eugene.countriestocities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Spinner spinner;
    private HashMap<String, ArrayList<String>> countryMap;
    private ArrayAdapter<String> spinnerAdapter;
    private CityRVAdapter cityRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        // адаптер
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
//                // показываем позиция нажатого элемента
//                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                cityRVAdapter.setCityList(countryMap.get(parent.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        cityRVAdapter = new CityRVAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cityRVAdapter);


        getCountryListFromServer();
        getCountryListFromDB();
    }

    private void getCountryListFromServer() {
        Intent getCountriesIntent = new Intent(this, CountryListService.class);
        getCountriesIntent.putExtra(Constants.RECEIVER_KEY, new CountryListReceiver(handler));
        startService(getCountriesIntent);
    }

    private void getCountryListFromDB() {

    }

    public class CountryListReceiver extends ResultReceiver {
        CountryListReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constants.SUCCESS_RESULT) {
                countryMap = (HashMap<String, ArrayList<String>>)
                        resultData.getSerializable(Constants.COUNTRY_MAP_KEY);

                // TODO: 03.12.18 x_prt
                /*
                  Тут, при получении данных с сервера ты должен записывать их в таблицу базы данных.
                  У тебя, как раз, для этого есть ключи и сами данные для записи.
                  Но я, пока что, сделаю отображение их на экране, для наглядности работы.
                 */

                if (countryMap != null && !countryMap.isEmpty()) {
                    ArrayList countryList = new ArrayList(countryMap.keySet());
                    Collections.sort(countryList);
                    spinnerAdapter.clear();
                    spinnerAdapter.addAll(countryList);
                    spinner.setSelection(1);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

