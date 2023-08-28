package com.example.interpreteappjavav7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ModuloDiccionarioActivity extends AppCompatActivity {
    String[] categoriass={"Salud","Deportes","Educacion"};
    Spinner spinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_diccionario);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,categoriass);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String categoriaSelected = (String) spinner.getItemAtPosition(position);
                Toast.makeText(ModuloDiccionarioActivity.this, categoriaSelected, Toast.LENGTH_SHORT).show();
                Log.d("mensaje categorai seleccionada", categoriaSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}