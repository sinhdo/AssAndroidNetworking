package com.example.assignmentandroidnetworking.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceApiFood;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceDeleteFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrDeleteFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrSelectFoodApi;
import com.example.assignmentandroidnetworking.R;
import com.example.assignmentandroidnetworking.adapter.DetailAdapter;
import com.example.assignmentandroidnetworking.adapter.FoodsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private DetailAdapter adapter;
    private List<Foods> list ;
    SvrSelectFoodApi svrResponseSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        swipeRefreshLayout = findViewById(R.id.refesh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView = findViewById(R.id.id_recycleview);
                LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                list = new ArrayList<>();
                CallApi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void  CallApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterfaceApiFood interSelect=retrofit.create(InterfaceApiFood.class);
        Call<SvrSelectFoodApi> call=interSelect.getListFoods();
        call.enqueue(new Callback<SvrSelectFoodApi>() {
            @Override
            public void onResponse(Call<SvrSelectFoodApi> call, Response<SvrSelectFoodApi> response) {
                 svrResponseSelect=response.body();
               RefeshCycle();
            }

            @Override
            public void onFailure(Call<SvrSelectFoodApi> call, Throwable t) {

            }
        });
    }
    public static void deleteFood(int id){
        Foods foods = new Foods();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceDeleteFood interDelete = retrofit.create(InterfaceDeleteFood.class);
        Call<SvrDeleteFood> call = interDelete.deleteFood(id);
        call.enqueue(new Callback<SvrDeleteFood>() {
            @Override
            public void onResponse(Call<SvrDeleteFood> call, Response<SvrDeleteFood> response) {
                SvrDeleteFood deleteFood = response.body();
//                Toast.makeText(, "Xoá thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SvrDeleteFood> call, Throwable t) {
//                Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        onStart();
        super.onResume();
    }
    void RefeshCycle(){
        list=new ArrayList<>(Arrays.asList(svrResponseSelect.getFoods()));
        adapter = new DetailAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}