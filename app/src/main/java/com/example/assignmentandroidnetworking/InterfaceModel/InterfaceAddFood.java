package com.example.assignmentandroidnetworking.InterfaceModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceAddFood {
    @FormUrlEncoded
    @POST("insert.php")
    Call<SvrAddFood> insertPrd(
            @Field("img") String img,
            @Field("nameFoods") String nameFoods,
            @Field("price") String price,
            @Field("time") String time,
            @Field("orderf") String oderf

    );
}
