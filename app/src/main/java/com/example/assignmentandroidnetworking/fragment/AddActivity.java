package com.example.assignmentandroidnetworking.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceAddFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrAddFood;
import com.example.assignmentandroidnetworking.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {
    EditText name,price,time;
    Button add,cancle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.txtNameFood);
        price = findViewById(R.id.txtPrice);
        time = findViewById(R.id.txtTime);
        add =findViewById(R.id.btnAdd);
        cancle =findViewById(R.id.btnCancle);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProducts();
            }
        });
    }

    ArrayList<Foods> ls;
    Foods foods = new Foods();
    private void addProducts()
    {
        foods.setImg("https://ap.poly.edu.vn/images/logo.png");
        foods.setNameFoods(name.getText().toString());
        foods.setPrice(Integer.parseInt(price.getText().toString()));
        foods.setTime(Integer.parseInt(time.getText().toString()));
        foods.setOrderf(0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceAddFood interfaceInsert = retrofit.create(InterfaceAddFood.class);
        //2.2 chuan bi ham
        Call<SvrAddFood> call = interfaceInsert.insertPrd(foods.getImg(),foods.getNameFoods(),String.valueOf(foods.getPrice()),String.valueOf(foods.getTime()),String.valueOf(foods.getOrderf()));
        //2.3 goi ham
        call.enqueue(new Callback<SvrAddFood>() {
            @Override
            public void onResponse(Call<SvrAddFood> call, Response<SvrAddFood> response) {
                SvrAddFood svrResponseInsert = response.body(); //lay noi dung server tra ve
                Toast.makeText(AddActivity.this, svrResponseInsert.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SvrAddFood> call, Throwable t) {
                Toast.makeText(AddActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
}