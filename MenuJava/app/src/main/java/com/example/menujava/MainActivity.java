package com.example.menujava;

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
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Crear Menú Principal Programáticamente
        MenuItem daysOfWeek = menu.add("Días de la Semana");
        Menu daysSubMenu = daysOfWeek.getSubMenu();
        // Agregar opciones al submenú "Días de la Semana"
        daysSubMenu.add(0, 1, 0, "Lunes");
        daysSubMenu.add(0, 2, 0, "Martes");
        daysSubMenu.add(0, 3, 0, "Miércoles");
        daysSubMenu.add(0, 4, 0, "Jueves");

        MenuItem monthsOfYear = menu.add("Meses del Año");
        Menu monthsSubMenu = monthsOfYear.getSubMenu();
        // Agregar opciones al submenú "Meses del Año"
        monthsSubMenu.add(0, 5, 0, "Enero");
        monthsSubMenu.add(0, 6, 0, "Febrero");
        monthsSubMenu.add(0, 7, 0, "Marzo");

        return true; // Indica que el menú está creado
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Manejar las opciones seleccionadas del menú
        switch (item.getItemId()) {
            case 1: // Lunes
                Toast.makeText(this, "Pulsado Lunes", Toast.LENGTH_SHORT).show();
                return true;
            case 2: // Martes
                Toast.makeText(this, "Pulsado Martes", Toast.LENGTH_SHORT).show();
                return true;
            case 3: // Miércoles
                Toast.makeText(this, "Pulsado Miércoles", Toast.LENGTH_SHORT).show();
                return true;
            case 4: // Jueves
                Toast.makeText(this, "Pulsado Jueves", Toast.LENGTH_SHORT).show();
                return true;
            case 5: // Enero
                Toast.makeText(this, "Pulsado Enero", Toast.LENGTH_SHORT).show();
                return true;
            case 6: // Febrero
                Toast.makeText(this, "Pulsado Febrero", Toast.LENGTH_SHORT).show();
                return true;
            case 7: // Marzo
                Toast.makeText(this, "Pulsado Marzo", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
