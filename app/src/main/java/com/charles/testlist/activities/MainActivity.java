package com.charles.testlist.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.charles.testlist.R;
import com.charles.testlist.adapters.ListViewAdapter;
import com.charles.testlist.models.Restaurant;
import com.charles.testlist.rest.GateWay;
import com.charles.testlist.rest.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }, 2000);
    }

    private void loadData() {

        double lat = 37.422740;
        double lng = -122.139956;
        int offset = 0;
        int limit = 50;

        GateWay service = RestClient.getGateWay().create(GateWay.class);

        Call<List<Restaurant>> call = service.getRestaurant(lat, lng, offset, limit);

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> list = response.body();
                loadView(list);
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong... Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadView(List<Restaurant> restaurantList) {
        List<Restaurant> list = restaurantList;
        itemList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ListViewAdapter adapter = new ListViewAdapter(list, MainActivity.this);
        itemList.setAdapter(adapter);
    }
}
