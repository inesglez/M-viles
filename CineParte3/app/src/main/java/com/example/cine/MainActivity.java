package com.example.cine;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private List<Pelicula> peliculaList;
    private static final int REQUEST_ADD_MOVIE = 1; // Código de solicitud

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        peliculaList = new ArrayList<>();
        // Datos de ejemplo (puedes eliminarlos o agregar más)
        peliculaList.add(new Pelicula("La Infiltrada", "Una espía encubierta...", "Acción", 120, 4.5f, R.drawable.la_infiltrada, null));
        peliculaList.add(new Pelicula("Gru, Mi Villano Favorito", "Gru planea el mayor atraco...", "Animación", 95, 4.7f, R.drawable.gru, null));
        peliculaList.add(new Pelicula("Smile", "Una joven psiquiatra enfrenta...", "Terror", 110, 4.0f, R.drawable.smile, null));
        peliculaList.add(new Pelicula("Venom", "Un periodista adquiere poderes...", "Acción/Sci-Fi", 112, 3.8f, R.drawable.venom, null));
        peliculaList.add(new Pelicula("Joker", "Historia oscura sobre la evolución...", "Drama", 122, 1.5f, R.drawable.joker, null));

        peliculaAdapter = new PeliculaAdapter(this, peliculaList);
        recyclerView.setAdapter(peliculaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cerrar_sesion) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.perfil) {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.entradas) {
            Intent intent = new Intent(MainActivity.this, EntradasActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.ordenar_duracion) {
            ordenarPeliculasPorDuracion();
            return true;
        } else if (id == R.id.ordenar_calificacion) {
            ordenarPeliculasPorCalificacion();
            return true;
        } else if (id == R.id.idioma_es) {
            cambiarIdioma("es");
            mostrarToast("Idioma cambiado a Español");
            return true;
        } else if (id == R.id.idioma_en) {
            cambiarIdioma("en");
            mostrarToast("Idioma cambiado a Inglés");
            return true;
        } else if (id == R.id.idioma_fr) {
            cambiarIdioma("fr");
            mostrarToast("Idioma cambiado a Francés");
            return true;
        } else if (id == R.id.añadir_pelicula) { // Nuevo: Añadir película
            Intent intent = new Intent(this, AñadirPelicula.class);
            startActivityForResult(intent, REQUEST_ADD_MOVIE); // Iniciar con código de solicitud
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Locale locale;
        switch (idioma) {
            case "es":
                locale = new Locale("es");
                break;
            case "en":
                locale = new Locale("en");
                break;
            case "fr":
                locale = new Locale("fr");
                break;
            default:
                locale = Locale.getDefault();
                break;
        }

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        recreate();
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_MOVIE) { // Usar el código de solicitud
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Pelicula nuevaPelicula = (Pelicula) data.getSerializableExtra("pelicula");
                    if (nuevaPelicula != null) {
                        peliculaList.add(nuevaPelicula);
                        peliculaAdapter.notifyDataSetChanged();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Añadir película cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}