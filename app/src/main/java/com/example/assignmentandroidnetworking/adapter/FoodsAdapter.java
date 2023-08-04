package com.example.assignmentandroidnetworking.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentandroidnetworking.DTO.Foods;
import com.example.assignmentandroidnetworking.InterfaceModel.InterfaceUpdateFood;
import com.example.assignmentandroidnetworking.InterfaceModel.SvrUpdateFood;
import com.example.assignmentandroidnetworking.R;
import com.example.assignmentandroidnetworking.fragment.UpdateActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.MyViewHoder> {
    private List<Foods> list;
    private Context mContext;

    public FoodsAdapter(List<Foods> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    public void setData(ArrayList<Foods> obj){
        this.list=obj;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_adapter,parent,false);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            int index = position;
            Foods foods = list.get(position);
            if (foods==null){
                return;
            }

//            holder.img.setImageResource(Integer.parseInt(foods.getImg()));
        Picasso.get().load(foods.getImg()).into(holder.img);
            holder.name.setText(foods.getNameFoods());
            holder.time.setText(foods.getTime()+ "P");
            holder.price.setText(String.valueOf(foods.getPrice()));
            if (foods.getOrderf()==1){
                holder.book.setText("Đã ĐẶT");
                holder.book.setBackgroundColor(Color.GREEN);
            }
            holder.book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateBooked(position);
                }
            });
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public static class MyViewHoder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,price,time;
        private Button book;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
           img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            price=itemView.findViewById(R.id.price);
            book = itemView.findViewById(R.id.book);
        }
    }
    private void updateBooked(int i) {
        Foods f  = list.get(i);
        f.setId(f.getId());
        if (f.getOrderf()==0){
            f.setOrderf(1);
        }else {
            f.setOrderf(0);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aomavaio.000webhostapp.com/API_Ass/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //b2. goi interface +chuản bị hàm + thực thi hàm
        InterfaceUpdateFood interUpdate = retrofit.create(InterfaceUpdateFood.class);
        Call<SvrUpdateFood> call = interUpdate.updateBooked(String.valueOf(f.getId()), String.valueOf(f.getOrderf()));
        call.enqueue(new Callback<SvrUpdateFood>() {
            @Override
            public void onResponse(Call<SvrUpdateFood> call, Response<SvrUpdateFood> response) {
                SvrUpdateFood svrResponUpdate = response.body();
                if (f.getOrderf()==0){
                    Toast.makeText(mContext.getApplicationContext(),"Huỷ đặt thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext.getApplicationContext(), "Đặt thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SvrUpdateFood> call, Throwable t) {
                Toast.makeText(mContext.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
