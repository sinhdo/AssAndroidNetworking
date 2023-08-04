package com.example.assignmentandroidnetworking.InterfaceModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDeleteFood {
    @FormUrlEncoded
    @POST("delete.php")
    Call<SvrDeleteFood> deleteFood(
            @Field("id") int id
    );
}
