package com.example.assignmentandroidnetworking.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceDeleteFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrDeleteFood;
import com.example.assignmentandroidnetworking.R;
import com.example.assignmentandroidnetworking.fragment.AddActivity;
import com.example.assignmentandroidnetworking.fragment.ChangeActivity;
import com.example.assignmentandroidnetworking.fragment.UpdateActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.FoodViewHoder>{
    private List<Foods> list;
    private Context mContext;
    private ChangeActivity changeActivity;


    public DetailAdapter(List<Foods> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FoodViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter,parent,false);
        return new FoodViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHoder holder, int position) {
        int index = position;
        Foods foods = list.get(position);
        if (foods==null){
            return;
        }

        Picasso.get().load(foods.getImg()).into(holder.img);
        holder.name.setText("NameFood: "+foods.getNameFoods());
        holder.time.setText("Time: "+foods.getTime()+ " m");
        holder.price.setText("Price: $"+String.valueOf(foods.getPrice()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              deleteFood(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), UpdateActivity.class);
                intent.putExtra("id",foods.getId());
                intent.putExtra("name",foods.getNameFoods());
                intent.putExtra("price",foods.getPrice());
                intent.putExtra("time",foods.getTime());
                Log.d("++++++TAG", "onClick: "+foods.getTime());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public static class FoodViewHoder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,price,time;
        private ImageView edit,delete;
        private Button de;

        public FoodViewHoder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            price=itemView.findViewById(R.id.price);
            edit = itemView.findViewById(R.id.btnEdit);
            delete  =itemView.findViewById(R.id.btnDelete);

        }
    }
    private void deleteFood(int i){
        Foods foods = list.get(i);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceDeleteFood interDelete = retrofit.create(InterfaceDeleteFood.class);
        Call<SvrDeleteFood> call = interDelete.deleteFood(foods.getId());
        call.enqueue(new Callback<SvrDeleteFood>() {
            @Override
            public void onResponse(Call<SvrDeleteFood> call, Response<SvrDeleteFood> response) {
                SvrDeleteFood deleteFood = response.body();
                Log.d("==================TAG", "onResponse: "+deleteFood.getMessage());
            }

            @Override
            public void onFailure(Call<SvrDeleteFood> call, Throwable t) {
                Log.d("===============TAG", "onResponse: "+t.getMessage());
            }
        });
    }
    private void dialogEdit(Context context){

    }


}
