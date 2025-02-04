package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoPeliculas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private ArrayList<Pelicula> listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_peliculas);

        recyclerView = findViewById(R.id.recyclerViewPeliculas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de películas desde el Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("peliculas")) {
            listaPeliculas = (ArrayList<Pelicula>) intent.getSerializableExtra("peliculas");
        } else {
            listaPeliculas = new ArrayList<>();
            mostrarToast("No se han encontrado películas");
        }

        // Configurar el adapter
        peliculaAdapter = new PeliculaAdapter(this, listaPeliculas);
        recyclerView.setAdapter(peliculaAdapter);
    }

    // Método para mostrar un Toast personalizado
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
