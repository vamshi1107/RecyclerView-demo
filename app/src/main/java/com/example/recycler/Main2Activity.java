package com.example.recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.ref.Reference;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    HashMap<String,Object> h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=findViewById(R.id.imageView);
        Intent i=getIntent();

        String d=i.getStringExtra("url");
        Picasso.with(getApplicationContext()).load(Uri.parse(d)).into(imageView);
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference("recycler");
        HashMap<String,Object> hp=new HashMap<String, Object>();
        hp.put("name","vamshi");
        int c=0;
        ref.child(String.valueOf(c)).setValue(hp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
        c+=1;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                send((HashMap<String,Object>)snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
   void send(HashMap<String,Object> hm){
        h=hm;
       Toast.makeText(getApplicationContext(),h.get("name").toString(),Toast.LENGTH_LONG);
       Log.d("vam",h.get("name").toString());
    }
}
