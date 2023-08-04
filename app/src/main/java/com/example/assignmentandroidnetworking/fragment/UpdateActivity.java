package com.example.assignmentandroidnetworking.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceUpdateFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrUpdateFood;
import com.example.assignmentandroidnetworking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    TextView id;
    EditText name,price,time;
    Button update,cancle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        id = findViewById(R.id.txtIdFood);
        name=findViewById(R.id.txtNameFood);
        price=findViewById(R.id.txtPrice);
        time=findViewById(R.id.txtTime);
        update=findViewById(R.id.btnUpdate);
        cancle = findViewById(R.id.btnCancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRestrofit();

            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            id.setText("ID : " + intent.getIntExtra("id",0));
            name.setText(intent.getStringExtra("name"));
            price.setText(String.valueOf(intent.getIntExtra("price",0)));
            time.setText( String.valueOf(intent.getIntExtra("time",0)));

        }else {
            Toast.makeText(this, "Không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
        }


    }
    private void updateRestrofit() {
        //b0, chuẩn bị dữ liệu
        Foods f =new Foods();
        Intent intent = getIntent();
        f.setId(intent.getIntExtra("id",0));
        f.setNameFoods(name.getText().toString());
        f.setPrice(Integer.parseInt(price.getText().toString()));
        f.setTime(Integer.parseInt(time.getText().toString()));

        //b1. tạo đối tượng restrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //b2. goi interface +chuản bị hàm + thực thi hàm
        InterfaceUpdateFood interUpdate = retrofit.create(InterfaceUpdateFood.class);
        Call<SvrUpdateFood> call = interUpdate.updateDB(String.valueOf(f.getId()),
                f.getNameFoods(),String.valueOf(f.getPrice()),String.valueOf(f.getTime()));
        call.enqueue(new Callback<SvrUpdateFood>() {
            @Override
            public void onResponse(Call<SvrUpdateFood> call, Response<SvrUpdateFood> response) {
                SvrUpdateFood svrResponUpdate = response.body();
                Toast.makeText(UpdateActivity.this, svrResponUpdate.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SvrUpdateFood> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
}