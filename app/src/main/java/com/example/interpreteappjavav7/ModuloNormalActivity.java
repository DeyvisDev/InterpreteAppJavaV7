package com.example.interpreteappjavav7;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ModuloNormalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ModuloNormalAdapter moduloNormalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_normal);
        recyclerView = findViewById(R.id.reciclerviewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModuloNormalModel> options =
                new FirebaseRecyclerOptions.Builder<ModuloNormalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("data"),ModuloNormalModel.class)
                        .build();
        moduloNormalAdapter = new ModuloNormalAdapter(options);
        recyclerView.setAdapter(moduloNormalAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        moduloNormalAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        moduloNormalAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.searchid);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                mysearch(newText);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                mysearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //buscador
    private void mysearch(String newText) {
        FirebaseRecyclerOptions<ModuloNormalModel> options =
                new FirebaseRecyclerOptions.Builder<ModuloNormalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("data").orderByChild("tituloNormal").startAt(newText).endAt(newText+"\uf8ff"),ModuloNormalModel.class)
                        .build();
        moduloNormalAdapter = new ModuloNormalAdapter(options);
        moduloNormalAdapter.startListening();
        recyclerView.setAdapter(moduloNormalAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}