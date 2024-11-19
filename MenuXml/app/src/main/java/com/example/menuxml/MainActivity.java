package com.example.menuxml;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que 'activity_main' exista.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el menú desde XML
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // Obtenemos el ID del ítem seleccionado

        switch (id) {
            case R.id.monday:
                Toast.makeText(this, "Pulsado Lunes", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.tuesday:
                Toast.makeText(this, "Pulsado Martes", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.wednesday:
                Toast.makeText(this, "Pulsado Miércoles", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.february:
                Toast.makeText(this, "Pulsado el mes de Febrero", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}