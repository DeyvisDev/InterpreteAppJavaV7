package com.example.interpreteappjavav7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interpreteappjavav7.databinding.ActivitySubirImgBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubirImgActivity extends AppCompatActivity {
    //spinner
    TextView tvCategoria;
    Spinner spinner;
    //String[] categoriass={"Salud","Deportes","Educacion","Saludos","Tecnologia","Cocina","Medicina","Economia","Trabajo","Musica","Videos","Redes Sociales"};
    String[] categoriass={"Salud","Deportes","Educacion"};
    String[] items={"Salud","Deportes","Educacion"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    //instancias de subir img y datos as firebase
    ActivitySubirImgBinding binding;
    Uri uri;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    private ProgressDialog progressDialog;
    //fin de instancias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_img);
        //obtenemos la hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String millisInString  = dateFormat.format(new Date());
        String sfecha = "";
        String shoraa = "";
        sfecha = millisInString.substring(0,10);
        shoraa = millisInString.substring(11,16);
        //fin de obtencio hora

        //recepcion de usuario
        String emailU = getIntent().getExtras().getString("email");
        //text field
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                Log.d("Item selected", item);
            }
        });
        //fin de textfield
/*
        //spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,categoriass);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String categoriaSelected = (String) spinner.getItemAtPosition(position);
                Toast.makeText(SubirImgActivity.this, categoriaSelected, Toast.LENGTH_SHORT).show();
                Log.d("mensaje categorai seleccionada", categoriaSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });*/
        //fin de spinner


        //spinner categoria
        /*tvCategoria = findViewById(R.id.tvCategoria);
        spinnerCategoria =findViewById(R.id.spinnerCategoria);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //loadCategorias();*/
        //subir datos a firebase e img
        binding = ActivitySubirImgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Subiendo, espere por favor...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Subiendo...");

        binding.btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImage();
            }
        });
        String finalSfecha = sfecha;
        String finalShoraa = shoraa;
        binding.btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                final StorageReference reference = firebaseStorage.getReference()
                        .child("images")
                        .child(System.currentTimeMillis()+"");
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Model model = new Model();
                                model.setImgSubir(uri.toString());
                                model.setTituloSordo(binding.tituloSordo.getText().toString().trim());
                                model.setDescripcionSordo(binding.descripcionSordo.getText().toString().trim());
                                model.setTituloNormal(binding.tituloNormal.getText().toString().trim());
                                model.setDescripcionNormal(binding.descripcionNormal.getText().toString().trim());
                                model.setFecha(finalSfecha);
                                model.setHora(finalShoraa);
                                model.setUsuario(emailU);
                                model.setCategoria("Categoria");
                                firebaseDatabase.getReference().child("data")
                                        .push()
                                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                binding.tituloSordo.getText().clear();
                                                binding.descripcionSordo.getText().clear();
                                                binding.tituloNormal.getText().clear();
                                                binding.descripcionNormal.getText().clear();
                                                binding.imgSubir.setImageResource(R.drawable.background);
                                                progressDialog.dismiss();
                                                Toast.makeText(SubirImgActivity.this,"Subido correctamente", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(SubirImgActivity.this,"Error al subir los datos", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        });
                            }
                        });
                    }
                });

            }
        });
    }
    /*public void loadCategorias(){
        List<Categorias> categorias = new ArrayList<>();
        mDatabase.child("Categoria").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String categoria = ds.child("Categoria").getValue().toString();
                        categorias.add(new Categorias(id, categoria));
                    }
                    ArrayAdapter<Categorias> arrayAdapter = new ArrayAdapter<>(SubirImgActivity.this, android.R.layout.simple_dropdown_item_1line, categorias);
                    spinnerCategoria.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/

    private void UploadImage() {
        Log.d("mensaje","fase1");
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        Log.d("mensaje","fase2");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(SubirImgActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101 && resultCode==RESULT_OK){
            uri = data.getData();
            binding.imgSubir.setImageURI(uri);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}