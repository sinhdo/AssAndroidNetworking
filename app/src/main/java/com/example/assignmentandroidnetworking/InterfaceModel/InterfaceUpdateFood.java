package com.example.assignmentandroidnetworking.InterfaceModel;

import com.example.assignmentandroidnetworking.DTO.Foods;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdateFood {
    @FormUrlEncoded
    @POST("update.php")
    Call<SvrUpdateFood> updateDB(

            @Field("id") String id,
            @Field("nameFoods") String nameFoods,
            @Field("price") String price,
            @Field("time") String time
    );
    @FormUrlEncoded
    @POST("booked.php")
    Call<SvrUpdateFood> updateBooked(
            @Field("id") String id,
            @Field("orderf") String orderf
     );
}
