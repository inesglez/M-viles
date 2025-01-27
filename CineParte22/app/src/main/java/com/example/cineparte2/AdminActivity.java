package com.example.cineparte2;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private List<Pelicula> peliculaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Configurar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de películas
        peliculaList = new ArrayList<>();
        peliculaList.add(new Pelicula("La Infiltrada", "Una espía encubierta se infiltra en una peligrosa red criminal.", "Acción", 120, 4.5f, R.drawable.la_infiltrada));
        peliculaList.add(new Pelicula("Gru, Mi Villano Favorito", "Gru planea el mayor atraco de la historia, mientras cría a tres niñas huérfanas.", "Animación", 95, 4.7f, R.drawable.gru));
        peliculaList.add(new Pelicula("Smile", "Una joven psiquiatra enfrenta aterradoras visiones tras un evento traumático.", "Terror", 110, 4.0f, R.drawable.smile));
        peliculaList.add(new Pelicula("Venom", "Un periodista adquiere poderes tras fusionarse con un simbionte alienígena.", "Acción/Sci-Fi", 112, 3.8f, R.drawable.venom));
        peliculaList.add(new Pelicula("Joker", "Historia oscura sobre la evolución de Arthur Fleck en el icónico Joker.", "Drama", 122, 1.5f, R.drawable.joker));

        // Configurar el adaptador con el contexto y la lista de películas
        peliculaAdapter = new PeliculaAdapter(this, peliculaList);
        recyclerView.setAdapter(peliculaAdapter);
    }

    // Inflar el menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    // Manejar las opciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (item.getItemId() == R.id.añadir_pelicula) {
            Intent intent = new Intent(AdminActivity.this, AñadirPelicula.class);
            startActivityForResult(intent, 1); // Código de solicitud
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Manejar el resultado de la actividad AñadirPelicula
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Pelicula nuevaPelicula = (Pelicula) data.getSerializableExtra("pelicula");
            if (nuevaPelicula != null) {
                peliculaList.add(nuevaPelicula);
                peliculaAdapter.notifyDataSetChanged(); // Actualizar RecyclerView
            }
        }
    }
}
