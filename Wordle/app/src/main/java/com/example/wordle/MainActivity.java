package com.example.wordle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout cuadrillaLetras;
    private TextView[][] casillas = new TextView[6][5]; // 6 filas y 5 columnas
    private int filaActual = 0;
    private int columnaActual = 0;
    private String palabraSeleccionada;
    private String[] listaPalabras = {"PERRO", "LIMON", "MANGO", "FORMA", "RATON"}; // Lista de palabras de 5 letras en español

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuadrillaLetras = findViewById(R.id.cuadrillaLetras);
        palabraSeleccionada = listaPalabras[new Random().nextInt(listaPalabras.length)];

        // Inicializa las celdas de la cuadrícula
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                TextView celda = new TextView(this);
                celda.setTextSize(24);
                celda.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                celda.setBackgroundResource(android.R.drawable.editbox_background_normal);
                celda.setPadding(8, 8, 8, 8);
                GridLayout.LayoutParams parametros = new GridLayout.LayoutParams();
                parametros.rowSpec = GridLayout.spec(i);
                parametros.columnSpec = GridLayout.spec(j);
                celda.setLayoutParams(parametros);
                casillas[i][j] = celda;
                cuadrillaLetras.addView(celda);
            }
        }

        // Configura el teclado
        configurarTeclado();
    }

    private void configurarTeclado() {
        int[] idsBotones = {
                R.id.btnQ, R.id.btnW, R.id.btnE, R.id.btnR, R.id.btnT,
                R.id.btnY, R.id.btnU, R.id.btnI, R.id.btnO, R.id.btnP,
                R.id.btnA, R.id.btnS, R.id.btnD, R.id.btnF, R.id.btnG,
                R.id.btnH, R.id.btnJ, R.id.btnK, R.id.btnL, R.id.btnZ,
                R.id.btnX, R.id.btnC, R.id.btnV, R.id.btnB, R.id.btnN, R.id.btnM
        };

        for (int id : idsBotones) {
            Button boton = findViewById(id);
            boton.setOnClickListener(view -> onLetraPulsada(boton.getText().toString()));
        }

        // Configurar botón de borrar
        findViewById(R.id.btnBorrar).setOnClickListener(view -> onBorrarPulsado());

        // Configurar botón de Enter
        findViewById(R.id.btnEnter).setOnClickListener(view -> onEnterPulsado());
    }

    private void onLetraPulsada(String letra) {
        if (columnaActual < 5) {
            casillas[filaActual][columnaActual].setText(letra); // Mantiene el texto
            columnaActual++;
        }
    }

    private void onBorrarPulsado() {
        if (columnaActual > 0) {
            columnaActual--;
            casillas[filaActual][columnaActual].setText(""); // Solo borra el texto, pero no el color
        }
    }

    private void onEnterPulsado() {
        if (columnaActual == 5) {
            // Construimos la palabra ingresada
            StringBuilder palabraIngresada = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                palabraIngresada.append(casillas[filaActual][i].getText().toString());
            }

            verificarPalabra(palabraIngresada.toString());

            filaActual++;
            columnaActual = 0;

            // Verifica si se ha terminado el juego
            if (filaActual == 6 && !palabraIngresada.toString().equals(palabraSeleccionada)) {
                Toast.makeText(this, "¡Juego Terminado! La palabra era: " + palabraSeleccionada, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debe ingresar una palabra de 5 letras.", Toast.LENGTH_SHORT).show();
        }
    }

    private void verificarPalabra(String palabra) {
        for (int i = 0; i < 5; i++) {
            String letraIngresada = String.valueOf(palabra.charAt(i));
            TextView textView = casillas[filaActual][i];

            if (letraIngresada.equals(String.valueOf(palabraSeleccionada.charAt(i)))) {
                textView.setBackgroundColor(Color.GREEN); // Letra correcta y en la posición correcta
            } else if (palabraSeleccionada.contains(letraIngresada)) {
                textView.setBackgroundColor(Color.rgb(255, 165, 0)); // Letra correcta, pero en la posición incorrecta (color naranja)
            } else {
                textView.setBackgroundColor(Color.LTGRAY); // Letra incorrecta
            }
        }
    }
}
