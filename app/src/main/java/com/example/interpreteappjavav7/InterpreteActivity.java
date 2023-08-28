package com.example.interpreteappjavav7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.interpreteappjavav7.databinding.ActivityInterpreteBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InterpreteActivity extends AppCompatActivity {

    ActivityInterpreteBinding binding;
    //String filePath;


    //primer intent
    String path;
    private Uri uri =null;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interprete);
        binding = ActivityInterpreteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //primer intent
        clickListeners();

        //uploadImage();

    }

    private void clickListeners() {
        binding.btnSelectFoto.setOnClickListener(v->{
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,10);
            }else{
                ActivityCompat.requestPermissions(InterpreteActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        });
        binding.btnTracucirFoto.setOnClickListener(v->{
            int i = 3;
            addCustomer(String.valueOf(i));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10 && resultCode== Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = InterpreteActivity.this;
            path = RealPathUtil.getRealPath(context,uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            binding.imageView.setImageBitmap(bitmap);
        }
    }
    public void addCustomer(String n_topp){
        Log.d("msg","f1");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pythonapip2-zgu4fg4ppa-uc.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        File file = new File(path);
        Log.d("msg","f2");
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",file.getName(),requestFile);

        RequestBody n_top = RequestBody.create(MediaType.parse("multipart/form-data"),n_topp);

        Log.d("msg","f3");

        ApiService apiService = retrofit.create(ApiService.class);
        Call<AddCustomerRes> call = apiService.addCustomer(body,n_top);
        Log.d("msg","f4");
        Log.d("msg",body.toString());
        call.enqueue(new retrofit2.Callback<AddCustomerRes>() {
            @Override
            public void onResponse(Call<AddCustomerRes> call, Response<AddCustomerRes> response) {
                Log.d("msg vv",response.toString());
                if (response.isSuccessful()){
                    Log.d("resutaldo1","sss");
                }
            }
            @Override
            public void onFailure(Call<AddCustomerRes> call, Throwable t) {
                Log.d("resutaldo2",t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}