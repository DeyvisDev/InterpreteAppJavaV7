package com.example.interpreteappjavav7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ModuloNormalVerItemActivity extends AppCompatActivity {
    ImageView imgItemDetail;
    TextView tvTituloNormalDetail;
    TextView tvDescripcionNormalDetail;
    TextView tvAutorDetail;
    TextView tvCategoriaDetail;
    TextView tvFechaDetail;
    TextView tvHoraDetail;

    //cambio de modo
    Boolean estadoBoton;
    Button botonCambio;
    String tituloN;
    String descripcionN;
    String tituloS;
    String descripcionS;
    TextView tvTitulo;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_normal_ver_item);
        //recibimos datos del item seleccionado
        String TituloNormal = getIntent().getExtras().getString("TituloNormal");
        String DescripcionNormal = getIntent().getExtras().getString("DescripcionNormal");
        String TituloSordo = getIntent().getExtras().getString("TituloSordo");
        String DescripcionSordo = getIntent().getExtras().getString("DescripcionSordo");
        String Usuario = getIntent().getExtras().getString("Usuario");
        String Fecha = getIntent().getExtras().getString("Fecha");
        String Hora = getIntent().getExtras().getString("Hora");
        String Categoria = getIntent().getExtras().getString("Categoria");
        String ImgSubir = getIntent().getExtras().getString("ImgSubir");
        Log.d("TituloNormal", TituloNormal);
        Log.d("DescripcionNormal", DescripcionNormal);
        Log.d("TituloSordo", TituloSordo);
        Log.d("DescripcionSordo", DescripcionSordo);
        Log.d("Usuario", Usuario);
        Log.d("Fecha", Fecha);
        Log.d("Hora", Hora);
        Log.d("Categoria", Categoria);
        Log.d("ImgSubir", ImgSubir);

        Usuario = "Publicado por: "+Usuario;

        Fecha = "en: "+Fecha+" Hora: "+Hora;
        //cambio de estado de traduccion
        estadoBoton = false;
        botonCambio = findViewById(R.id.botonCambio);
        tvTitulo = findViewById(R.id.tvTitulo);


        imgItemDetail = findViewById(R.id.imgItemDetail);
        tvTituloNormalDetail = findViewById(R.id.tvTituloNormalDetail);
        tvDescripcionNormalDetail = findViewById(R.id.tvDescripcionNormalDetail);
        tvAutorDetail = findViewById(R.id.tvAutorDetail);
        tvCategoriaDetail = findViewById(R.id.tvCategoriaDetail);
        tvFechaDetail = findViewById(R.id.tvFechaDetail);
        //tvHoraDetail = findViewById(R.id.tvHoraDetail);

        Glide.with(this)
                        .load(ImgSubir).into(imgItemDetail);

        tvTituloNormalDetail.setText(TituloNormal);
        tvDescripcionNormalDetail.setText(DescripcionNormal);
        tvAutorDetail.setText(Usuario);
        tvCategoriaDetail.setText(Categoria);
        tvFechaDetail.setText(Fecha);
        //tvHoraDetail.setText(Hora);
        tituloN = TituloNormal;
        descripcionN = DescripcionNormal;
        tituloS = TituloSordo;
        descripcionS = DescripcionSordo;

    }

    public void pulsarCambio(View v) {
        if (estadoBoton==true){
            botonCambio.setText("Cambiar a sordos");
            estadoBoton = false;
            tvTituloNormalDetail.setText(tituloN);
            tvDescripcionNormalDetail.setText(descripcionN);
            tvTitulo.setText("Para personas oyentes");
        }else{
            botonCambio.setText("Cambiar a normal");
            estadoBoton = true;
            tvTituloNormalDetail.setText(tituloS);
            tvDescripcionNormalDetail.setText(descripcionS);
            tvTitulo.setText("Para personas sordas");
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(ModuloNormalVerItemActivity.this, ModuloNormalActivity.class);
        startActivity(i);
    }
}