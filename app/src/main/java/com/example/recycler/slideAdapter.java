package com.example.recycler;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class slideAdapter extends RecyclerView.Adapter<slideAdapter.slideViewHolder> {

    ArrayList<slide> al;
    class  slideViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public slideViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img2);
        }
    }
    slideAdapter(ArrayList<slide> al){
        this.al=al;
    }
    @NonNull
    @Override
    public slideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.slide,parent,false);
        c=parent.getContext();
        slideViewHolder sb=new slideViewHolder(v);
        return sb;
    }
     Context c;
    @Override
    public void onBindViewHolder(@NonNull slideViewHolder holder, int position) {
         slide s=al.get(position);
         Picasso.with(c).load(Uri.parse(s.url)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

}
