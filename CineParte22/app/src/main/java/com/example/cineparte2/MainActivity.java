package com.example.cineparte2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private List<Pelicula> peliculaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de películas
        inicializarPeliculas();

        // Configurar el adaptador con el contexto y la lista de películas
        peliculaAdapter = new PeliculaAdapter(this, peliculaList);
        recyclerView.setAdapter(peliculaAdapter);

        // Leer y mostrar la información desde el archivo de texto
        mostrarInformacionUso();
    }

    private void inicializarPeliculas() {
        peliculaList = new ArrayList<>();
        peliculaList.add(new Pelicula("La Infiltrada", "Una espía encubierta se infiltra en una peligrosa red criminal.", "Acción", 120, 4.5f, R.drawable.la_infiltrada));
        peliculaList.add(new Pelicula("Gru, Mi Villano Favorito", "Gru planea el mayor atraco de la historia, mientras cría a tres niñas huérfanas.", "Animación", 95, 4.7f, R.drawable.gru));
        peliculaList.add(new Pelicula("Smile", "Una joven psiquiatra enfrenta aterradoras visiones tras un evento traumático.", "Terror", 110, 4.0f, R.drawable.smile));
        peliculaList.add(new Pelicula("Venom", "Un periodista adquiere poderes tras fusionarse con un simbionte alienígena.", "Acción/Sci-Fi", 112, 3.8f, R.drawable.venom));
        peliculaList.add(new Pelicula("Joker", "Historia oscura sobre la evolución de Arthur Fleck en el icónico Joker.", "Drama", 122, 1.5f, R.drawable.joker));
        peliculaList.add(new Pelicula("aaa", "aaaaaaaaaaa", "Drama", 1, 1f, R.drawable.cinemania));
    }

    private void mostrarInformacionUso() {
        // Leer el archivo de texto desde raw
        InputStream inputStream = getResources().openRawResource(R.raw.informacion_uso);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String informacionUso = stringBuilder.toString();

        // Mostrar la información en un TextView
        TextView textView = findViewById(R.id.textViewInformacionUso);
        textView.setText(informacionUso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (item.getItemId() == R.id.perfil) {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.entradas) {
            Intent intent = new Intent(MainActivity.this, EntradasActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.ordenar_duracion) {
            ordenarPeliculasPorDuracion();
            return true;
        } else if (item.getItemId() == R.id.ordenar_calificacion) {
            ordenarPeliculasPorCalificacion();
            return true;
        } else if (item.getItemId() == R.id.idioma_es) {
            cambiarIdioma("es");
            mostrarToast("Idioma cambiado a Español");
            return true;
        } else if (item.getItemId() == R.id.idioma_en) {
            cambiarIdioma("en");
            mostrarToast("Idioma cambiado a Inglés");
            return true;
        } else if (item.getItemId() == R.id.idioma_fr) {
            cambiarIdioma("fr");
            mostrarToast("Idioma cambiado a Francés");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void ordenarPeliculasPorDuracion() {
        peliculaList.sort((p1, p2) -> Integer.compare(p1.getDuracion(), p2.getDuracion()));
        peliculaAdapter.notifyDataSetChanged();
    }

    private void ordenarPeliculasPorCalificacion() {
        peliculaList.sort((p1, p2) -> Float.compare(p2.getPuntuacion(), p1.getPuntuacion()));
        peliculaAdapter.notifyDataSetChanged();
    }

    private void cambiarIdioma(String idioma) {
        Locale locale = new Locale(idioma);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
