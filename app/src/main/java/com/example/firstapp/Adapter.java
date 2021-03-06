package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Data> models;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    interface OnClickListener{
        void onClick(Data model);
    }

    public Adapter(Context context, ArrayList<Data> models){
        this.context = context;
        this.models = models;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

         ImageView imageView;
         TextView title;
         TextView bodyInfo;
         CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            bodyInfo = itemView.findViewById(R.id.bodyInfo);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position ) {
        ((ViewHolder) holder).title.setText(models.get(position).getTitle());
        ((ViewHolder) holder).bodyInfo.setText(models.get(position).getBodyInfo());
        Glide.with(context)
                .load(models.get(position).getImage())
                .into( ((ViewHolder) holder).imageView);
        ((ViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onClick(models.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}