package com.example.recycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.security.Permissions;
import java.security.acl.Permission;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
ImageView img;
Button b2,bs;
ProgressBar p;
Uri uri;
    final String path="vamshi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        p=findViewById(R.id.pb);
        img=findViewById(R.id.imgv);
        bs=findViewById(R.id.buts);
        bs.setEnabled(false);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
        b2=findViewById(R.id.butu);
        b2.setEnabled(false);
        p.setVisibility(View.INVISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(Manifest.permission.READ_EXTERNAL_STORAGE) && check(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Intent i=new Intent(Intent.ACTION_PICK);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i,"SELECT IMAGE"),1);
                }
            }
        });
        Date d=new Date();
        final StorageReference sr= FirebaseStorage.getInstance().getReference( "images");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sr.child(path).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                        open();
                        p.setVisibility(View.INVISIBLE);
                        bs.setEnabled(true);
                        b2.setEnabled(false);
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        p.setVisibility(View.VISIBLE);
                        Log.d("vams", String.valueOf(snapshot.getBytesTransferred()));
                        double c= ( snapshot.getBytesTransferred() * 100)/snapshot.getTotalByteCount();
                        p.setProgress((int)c);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("vams",e.toString());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            uri=data.getData();
            Picasso.with(getApplicationContext()).load(data.getData()).into(img);
            b2.setEnabled(true);
        }
    }

    Boolean check(String s){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), s)==1){
            Toast.makeText(getApplicationContext(),"permissions granted",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            requestPermissions(new String[]{s},1);
            return true;
        }

    }
   void open(){
       Dialog da =Dialog.newInstance(this);
       da.setpath(path);
       da.show(getSupportFragmentManager(),"vamshi");

    }

}
