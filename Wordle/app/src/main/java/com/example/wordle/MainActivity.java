package com.example.wordle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout cuadrillaLetras;
    private TextView[][] casillas = new TextView[6][5]; // 6 filas y 5 columnas
    private int filaActual = 0;
    private int columnaActual = 0;
    private String palabraSeleccionada;
    private String[] listaPalabras = {"PERRO", "LIMON", "MANGO", "FORMA", "RATON"}; // Lista de palabras de 5 letras en español
    private Map<String, Integer> estadoLetras = new HashMap<>(); // Mapa para almacenar el estado de las letras

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que esto coincide con tu archivo XML

        cuadrillaLetras = findViewById(R.id.cuadrillaLetras);
        palabraSeleccionada = listaPalabras[new Random().nextInt(listaPalabras.length)];

        // Inicializa las celdas de la cuadrícula
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                TextView celda = new TextView(this);
                crearEstiloCelda(celda);

                // parámetros para la cuadrícula
                GridLayout.LayoutParams parametros = new GridLayout.LayoutParams();
                parametros.rowSpec = GridLayout.spec(i);
                parametros.columnSpec = GridLayout.spec(j);

                // Ajusta el tamaño de las celdas
                int anchoCelda = 190; // Cambia este valor para ajustar el ancho
                int alturaCelda = 170; // Cambia este valor para ajustar la altura
                parametros.width = anchoCelda; // Establece el ancho de la celda
                parametros.height = alturaCelda; // Establece la altura de la celda

                celda.setLayoutParams(parametros);
                casillas[i][j] = celda;
                cuadrillaLetras.addView(celda);
            }
        }

        configurarTeclado();
    }

    private void crearEstiloCelda(TextView celda) {
        celda.setTextSize(30); // Aumenta el tamaño de la letra
        celda.setTextColor(Color.BLACK); // Cambia el color de la letra
        celda.setBackgroundResource(android.R.drawable.editbox_background_normal);
        celda.setPadding(60, 20, 60, 20); // Ajusta el padding
        celda.setGravity(View.TEXT_ALIGNMENT_CENTER); // Centra el texto en la celda
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
            if (palabraIngresada.toString().equals(palabraSeleccionada)) {
                Toast.makeText(this, "¡Felicidades! Adivinaste la palabra: " + palabraSeleccionada, Toast.LENGTH_LONG).show();
                //reiniciar el juego
                reiniciarJuego();
            } else if (filaActual == 6) {
                Toast.makeText(this, "¡Juego Terminado! La palabra era: " + palabraSeleccionada, Toast.LENGTH_LONG).show();
                // reiniciar el juego
                reiniciarJuego();
            }
        } else {
            Toast.makeText(this, "Debe ingresar una palabra de 5 letras.", Toast.LENGTH_SHORT).show();
        }
    }

    private void reiniciarJuego() {
        // Reinicia la actividad
        recreate();
    }

    private void verificarPalabra(String palabra) {
        for (int i = 0; i < 5; i++) {
            String letraIngresada = String.valueOf(palabra.charAt(i));
            TextView textView = casillas[filaActual][i];

            if (letraIngresada.equals(String.valueOf(palabraSeleccionada.charAt(i)))) {
                textView.setBackgroundColor(Color.rgb(135, 255, 129)); // Letra correcta y en la posición correcta
                actualizarEstadoLetra(letraIngresada, Color.rgb(135, 255, 129));
            } else if (palabraSeleccionada.contains(letraIngresada)) {
                textView.setBackgroundColor(Color.rgb(255, 188, 77)); // Letra correcta, pero en la posición incorrecta
                actualizarEstadoLetra(letraIngresada, Color.rgb(255, 188, 77));
            } else {
                textView.setBackgroundColor(Color.rgb(229, 228, 227)); // Letra incorrecta
                actualizarEstadoLetra(letraIngresada, Color.rgb(229, 228, 227));
            }
        }
    }

    private void actualizarEstadoLetra(String letra, int color) {
        // Si la letra ya tiene un color definido y no es verde (correcta en la posición correcta)
        if (estadoLetras.containsKey(letra) && estadoLetras.get(letra) == Color.rgb(135, 255, 129)) {
            return; // No actualiza si ya está correcto
        }

        estadoLetras.put(letra, color);
        Button boton = findViewById(getButtonId(letra));
        if (boton != null) {
            boton.setBackgroundColor(color);
        }
    }

    private int getButtonId(String letra) {
        // Mapea las letras a los ID de los botones
        switch (letra.toUpperCase()) {
            case "Q": return R.id.btnQ;
            case "W": return R.id.btnW;
            case "E": return R.id.btnE;
            case "R": return R.id.btnR;
            case "T": return R.id.btnT;
            case "Y": return R.id.btnY;
            case "U": return R.id.btnU;
            case "I": return R.id.btnI;
            case "O": return R.id.btnO;
            case "P": return R.id.btnP;
            case "A": return R.id.btnA;
            case "S": return R.id.btnS;
            case "D": return R.id.btnD;
            case "F": return R.id.btnF;
            case "G": return R.id.btnG;
            case "H": return R.id.btnH;
            case "J": return R.id.btnJ;
            case "K": return R.id.btnK;
            case "L": return R.id.btnL;
            case "Z": return R.id.btnZ;
            case "X": return R.id.btnX;
            case "C": return R.id.btnC;
            case "V": return R.id.btnV;
            case "B": return R.id.btnB;
            case "N": return R.id.btnN;
            case "M": return R.id.btnM;
            default: return -1; // No encontrado
        }
    }
}
