package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView2;
    RecyclerView.LayoutManager layoutManager;
    adapter adapter;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView2=findViewById(R.id.rv2);
        layoutManager=new LinearLayoutManager(this);
        img=findViewById(R.id.imgex);
        startActivity(new Intent(MainActivity.this,Main3Activity.class));
        Picasso.with(getApplicationContext()).load("https://cdni.autocarindia.com/Utils/ImageResizer.ashx?n=https%3A%2F%2Fcdni.autocarindia.com%2FExtraImages%2F20200816011251_2020-Mahindra-Thar-off-road-action-1.jpg&h=795&w=1200&c=0").into(img);
        final ArrayList<row> al=new ArrayList<row>();
        al.add(new row(R.drawable.ic_android,"vamshi","addanki","https://cdni.autocarindia.com/Utils/ImageResizer.ashx?n=https%3A%2F%2Fcdni.autocarindia.com%2FExtraImages%2F20200816011251_2020-Mahindra-Thar-off-road-action-1.jpg&h=795&w=1200&c=0"));
        al.add(new row(R.drawable.ic_android,"sai","addanki","https://images.livemint.com/img/2020/11/04/600x338/Mahindrathar_1604486465203_1604486476829.jpg"));
        al.add(new row(R.drawable.ic_android,"krishna","addanki","https://cdni.autocarindia.com/ExtraImages/20201006121608_2020-Mahindra-Thar-blue-roof.jpg"));


        adapter=new adapter(al);
        adapter.setOnitemClicklistener(new adapter.onItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                row r=al.get(position);
                Toast.makeText(getApplicationContext(),al.get(position).getFirstname().toString(),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("url",r.url);
                startActivity(i);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

       recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
       ArrayList<slide> a=new ArrayList<slide>();
       a.add(new slide("https://cdni.autocarindia.com/Utils/ImageResizer.ashx?n=https%3A%2F%2Fcdni.autocarindia.com%2FExtraImages%2F20200816011251_2020-Mahindra-Thar-off-road-action-1.jpg&h=795&w=1200&c=0"));
        a.add(new slide("https://cdni.autocarindia.com/Utils/ImageResizer.ashx?n=https%3A%2F%2Fcdni.autocarindia.com%2FExtraImages%2F20200816011251_2020-Mahindra-Thar-off-road-action-1.jpg&h=795&w=1200&c=0"));
        a.add(new slide("https://cdni.autocarindia.com/Utils/ImageResizer.ashx?n=https%3A%2F%2Fcdni.autocarindia.com%2FExtraImages%2F20200816011251_2020-Mahindra-Thar-off-road-action-1.jpg&h=795&w=1200&c=0"));

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(new slideAdapter(a));
    }
    void  move(View v){
        Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
    }
}
