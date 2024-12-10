package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

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
        setSupportActionBar(toolbar);  // Establecer la Toolbar como ActionBar

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

    // Inflar el menú cuando el usuario hace clic en el ícono de ajustes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);  // Inflar el archivo de menú
        return true;
    }

    // Manejar la acción del menú, incluyendo "Ordenar por duración" y "Ordenar por calificación"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            // Cerrar sesión y regresar a LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (item.getItemId() == R.id.perfil) {
            // Abrir la actividad PerfilActivity
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.entradas) {
            // Navegar a EntradasActivity
            Intent intent = new Intent(MainActivity.this, EntradasActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.ordenar_duracion) {
            // Ordenar las películas por duración
            ordenarPeliculasPorDuracion();
            return true;
        } else if (item.getItemId() == R.id.ordenar_calificacion) {
            // Ordenar las películas por calificación
            ordenarPeliculasPorCalificacion();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Método para ordenar las películas por duración
    private void ordenarPeliculasPorDuracion() {
        peliculaList.sort((p1, p2) -> Integer.compare(p1.getDuracion(), p2.getDuracion()));
        peliculaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    // Método para ordenar las películas por calificación
    private void ordenarPeliculasPorCalificacion() {
        peliculaList.sort((p1, p2) -> Float.compare(p2.getPuntuacion(), p1.getPuntuacion())); // Orden descendente
        peliculaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }
}
