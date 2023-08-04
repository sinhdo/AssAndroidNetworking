package com.example.assignmentandroidnetworking.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceApiFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrSelectFoodApi;
import com.example.assignmentandroidnetworking.R;
import com.example.assignmentandroidnetworking.adapter.DetailAdapter;
import com.example.assignmentandroidnetworking.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartFragment extends Fragment {
    RecyclerView rcybooked;
    OrderAdapter adapter;
    ArrayList<Foods> list;
    SvrSelectFoodApi svrResponseSelect;
    SwipeRefreshLayout swipeRefreshLayout;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = view.findViewById(R.id.refesh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rcybooked = view.findViewById(R.id.rcybooked);
                LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                rcybooked.setLayoutManager(linearLayoutManager);
                list = new ArrayList<>();
                CallApi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    void RefeshCycle(){

    }
    private void  CallApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterfaceApiFood interSelect=retrofit.create(InterfaceApiFood.class);
        Call<SvrSelectFoodApi> call=interSelect.getListFoodsBooked();
        call.enqueue(new Callback<SvrSelectFoodApi>() {
            @Override
            public void onResponse(Call<SvrSelectFoodApi> call, Response<SvrSelectFoodApi> response) {
                svrResponseSelect=response.body();
                list=new ArrayList<>(Arrays.asList(svrResponseSelect.getFoods()));
                adapter = new OrderAdapter(getContext(),list);
                rcybooked.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SvrSelectFoodApi> call, Throwable t) {

            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        CallApi();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        onStart();
//    }
}