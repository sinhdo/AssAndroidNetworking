package com.example.assignmentandroidnetworking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHoder> {

    Context context;
    List<Foods> list;

    public OrderAdapter(Context context, List<Foods> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oder_item,parent,false);
        return new OrderViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHoder holder, int position) {
        int index = position;
        Foods foods = list.get(position);
        if (foods==null){
            return;
        }

        Picasso.get().load(foods.getImg()).into(holder.img);
        holder.name.setText("NameFood: "+foods.getNameFoods());
        holder.time.setText("Time: "+foods.getTime()+ " m");
        holder.price.setText("Price: $"+String.valueOf(foods.getPrice()));

        holder.booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updateBooked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public static class OrderViewHoder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,price,time;
        private Button booked;

        public OrderViewHoder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            price=itemView.findViewById(R.id.price);
            booked = itemView.findViewById(R.id.btnBooked);
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
        InterfaceUpdateFood interUpdate = retrofit.create(InterfaceUpdateFood.class);
        Call<SvrUpdateFood> call = interUpdate.updateBooked(String.valueOf(f.getId()), String.valueOf(f.getOrderf()));
        call.enqueue(new Callback<SvrUpdateFood>() {
            @Override
            public void onResponse(Call<SvrUpdateFood> call, Response<SvrUpdateFood> response) {
                SvrUpdateFood svrResponUpdate = response.body();
                if (f.getOrderf()==0){
                    Toast.makeText(context.getApplicationContext(),"Huỷ đặt thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context.getApplicationContext(), "Đặt thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SvrUpdateFood> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Lấy dữ liệu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
