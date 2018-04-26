package com.ramirez.peliculas;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter_cardview extends RecyclerView.Adapter<RecyclerViewAdapter_cardview.PelisViewHolder>{

    private ArrayList<Pelicula> peliculas;

    public abstract void onFavClick(CompoundButton buttonView, boolean isChecked);

    public static class PelisViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        ImageView img;
        CheckBox checkBox;

        public PelisViewHolder(View itemview){
            super(itemview);
            cardView = itemview.findViewById(R.id.card_view);
            name = itemview.findViewById(R.id.TV_name);
            img = itemview.findViewById(R.id.imagen);
            checkBox = itemview.findViewById(R.id.checked);
        }
    }

    public RecyclerViewAdapter_cardview(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PelisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return (new PelisViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull PelisViewHolder holder,final int position) {
        holder.name.setText(peliculas.get(position).getNombre());
        holder.img.setImageResource(peliculas.get(position).getImg());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onFavClick(buttonView,isChecked);
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Favorita: "+ peliculas.get(position).getNombre(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }
}

