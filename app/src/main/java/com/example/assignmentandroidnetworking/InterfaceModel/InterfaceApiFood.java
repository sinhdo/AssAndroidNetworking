package com.example.assignmentandroidnetworking.InterfaceModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface InterfaceApiFood {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    InterfaceApiFood API_FOOD = new Retrofit.Builder()
            .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
            //https://jsonplaceholder.typicode.com/posts
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(InterfaceApiFood.class);
    @GET("select.php")
//    Call<List<Foods>> getListFoods();
    Call<SvrSelectFoodApi> getListFoods();
    @GET("selectOrder.php")
    Call<SvrSelectFoodApi> getListFoodsBooked();


}

