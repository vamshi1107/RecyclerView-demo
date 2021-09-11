package com.example.recycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Dialog extends BottomSheetDialogFragment {

    String path;
    TextView e;
    String data;
    Button b;
    static Dialog newInstance(Context context){
        return new Dialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_dialog,container,false);
        e=v.findViewById(R.id.tv1);
        b=v.findViewById(R.id.bus);
        getData();
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(Intent.ACTION_SEND);
                 i.setType("text/*");
                 i.putExtra(Intent.EXTRA_TEXT,"LINK : "+ data);
                 startActivity(Intent.createChooser(i,null));
             }
         });
        return v;
    }
    void getData(){
        final StorageReference sr= FirebaseStorage.getInstance().getReference( "images");

        sr.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                data =String.valueOf(uri);
                e.setText(String.valueOf(uri));
                Log.d("vams",String.valueOf(uri));
            }
        });
    }
    void setpath(String s){
        this.path=s;

    }
}
