package com.example.interpreteappjavav7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {
    Button btnTraduccionIA;
    Button btnParaSordos;
    Button btnParaNormales;
    Button btnDiccionario;
    Button btnSubir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //recibimos datos del login
        String emailU = getIntent().getExtras().getString("email");
        String passwordU = getIntent().getExtras().getString("pass");
        Log.d("email", emailU);
        Log.d("pass", passwordU);
        //fin de recepcion de datos de login
        //instanciamos los botones
        btnTraduccionIA = findViewById(R.id.btnTraduccionIA);
        btnParaSordos = findViewById(R.id.btnParaSordos);
        btnParaNormales = findViewById(R.id.btnParaNormales);
        btnDiccionario = findViewById(R.id.btnDiccionario);
        btnSubir = findViewById(R.id.btnSubir);
        //creamos los intents
        btnTraduccionIA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, InterpreteActivity.class);
                startActivity(i);
            }
        });
        btnParaSordos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, ModuloSordoActivity.class);
                startActivity(i);
            }
        });
        btnParaNormales.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, ModuloNormalActivity.class);
                startActivity(i);
            }
        });
        btnDiccionario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, ModuloDiccionarioActivity.class);
                startActivity(i);
            }
        });
        btnSubir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, SubirImgActivity.class);
                i.putExtra("email", emailU);
                startActivity(i);
            }
        });
    }
    //
    /*
    public void botonInterpreteImg(View v){
        Intent i = new Intent(InicioActivity.this, LoginActivity.class);
        startActivity(i);
    }*/
}