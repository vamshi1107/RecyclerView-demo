package com.example.recycler;

import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class adapter extends RecyclerView.Adapter<adapter.viewholder> {


    onItemClickListener listener;
    interface onItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnitemClicklistener(onItemClickListener listener){
        this.listener=listener;
    }
    ArrayList<row> list=new ArrayList<row>();
    class viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2;

        public viewholder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            textView1=itemView.findViewById(R.id.tex1);
            textView2=itemView.findViewById(R.id.tex2);
            imageView=itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(listener !=null){
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }
    public adapter(ArrayList<row> al){
        list=al;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        viewholder vh=new viewholder(v,listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
       row r=list.get(position);
       holder.imageView.setImageResource(r.getImage());
       holder.textView1.setText(r.getFirstname());
       holder.textView2.setText(r.getLastname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
