package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
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

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de películas
        peliculaList = new ArrayList<>();
        peliculaList.add(new Pelicula("La Infiltrada", "Una espía encubierta se infiltra en una peligrosa red criminal.", "Acción", 120, 4.5f, R.drawable.la_infiltrada));
        peliculaList.add(new Pelicula("Gru, Mi Villano Favorito", "Gru planea el mayor atraco de la historia, mientras cría a tres niñas huérfanas.", "Animación", 95, 4.7f, R.drawable.gru));
        peliculaList.add(new Pelicula("Smile", "Una joven psiquiatra enfrenta aterradoras visiones tras un evento traumático.", "Terror", 110, 4.0f, R.drawable.smile));
        peliculaList.add(new Pelicula("Venom", "Un periodista adquiere poderes tras fusionarse con un simbionte alienígena.", "Acción/Sci-Fi", 112, 3.8f, R.drawable.venom));
        peliculaList.add(new Pelicula( "Joker", "Historia oscura sobre la evolución de Arthur Fleck en el icónico Joker.", "Drama", 122, 1.5f, R.drawable.joker));

        // Configurar el adaptador con el contexto y la lista de películas
        peliculaAdapter = new PeliculaAdapter(this, peliculaList);
        recyclerView.setAdapter(peliculaAdapter);
    }

    // Inflar el menú cuando el usuario hace clic en el ícono de ajustes (el menú desplegable)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);  // Asegúrate de que el archivo de menú sea "menu_desplegable"
        return true;
    }

    // Manejar la acción del menú, como "Cerrar sesión" o "Perfil"
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Usar if-else en lugar de switch para evitar problemas con R.id
        if (item.getItemId() == R.id.cerrar_sesion) {
            // Al seleccionar "Cerrar sesión", redirigimos a LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finalizamos la actividad actual (MainActivity)
            return true;
        } else if (item.getItemId() == R.id.perfil) {
            // Al seleccionar "Perfil", no hace nada por ahora o puedes abrir una nueva actividad de perfil
            // Intent intentPerfil = new Intent(MainActivity.this, PerfilActivity.class);
            // startActivity(intentPerfil);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
}
}
