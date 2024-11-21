package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminActivity extends AppCompatActivity {

    private Button btnVerLista, btnEliminar, btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Configurar el Toolbar con el menú de opciones
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVerLista = findViewById(R.id.btnVerLista);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnAgregar = findViewById(R.id.btnAgregar);

        btnVerLista.setOnClickListener(v -> {
            // Ver lista de películas, igual que un usuario normal
            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(v -> {
            // Implementar lógica para eliminar películas
        });

        btnAgregar.setOnClickListener(v -> {
            // Implementar lógica para agregar películas
        });
    }

    // Inflar el menú cuando el usuario hace clic en el ícono de ajustes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);  // Inflar el archivo de menú
        return true;
    }

    // Manejar la acción del menú, como "Cerrar sesión" o "Perfil"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            // Al seleccionar "Cerrar sesión", redirigimos a LoginActivity
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finalizamos la actividad actual (AdminActivity)
            return true;
        } else if (item.getItemId() == R.id.perfil) {
            // Acción para el perfil
            // Aquí puedes lanzar una nueva actividad de perfil si la tienes
            // Intent intentPerfil = new Intent(AdminActivity.this, PerfilActivity.class);
            // startActivity(intentPerfil);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
