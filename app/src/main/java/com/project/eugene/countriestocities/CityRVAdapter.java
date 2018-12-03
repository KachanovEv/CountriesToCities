package com.project.eugene.countriestocities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by x_prt_admin on 03.12.18
 */
class CityRVAdapter extends RecyclerView.Adapter<CityRVAdapter.CityHolder> {

    private Context context;
    private ArrayList<String> cityList = new ArrayList<>();

    public CityRVAdapter(Context context) {
        this.context = context;
    }

    public void setCityList(ArrayList<String> cityList) {
        Collections.sort(cityList);
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_city_list, viewGroup, false);
        return new CityHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder cityHolder, int i) {
        final String cityName = cityList.get(i);

        cityHolder.cityName.setText(cityName);
        cityHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://en.wikipedia.org/wiki/" + cityName;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {
        final TextView cityName;

        CityHolder(View view) {
            super(view);
            cityName = view.findViewById(R.id.tv_city_name);
        }
    }
}
