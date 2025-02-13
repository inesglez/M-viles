package com.example.action_up;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MiCuartoGraficoInter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer la vista personalizada para dibujar
        setContentView(new MiVista(this, null));
    }
}
