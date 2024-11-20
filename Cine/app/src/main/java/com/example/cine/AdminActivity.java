package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private Button btnVerLista, btnEliminar, btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

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
}
