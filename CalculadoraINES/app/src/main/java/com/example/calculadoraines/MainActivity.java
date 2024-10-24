package com.example.calculadoraines;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView pantalla;
    private String input = "";
    private String operador = "";
    private double primerNumero = 0;
    private boolean operadorPresionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que este es el nombre correcto de tu layout

        pantalla = findViewById(R.id.pantalla);

        // Números
        Button n0 = findViewById(R.id.n0);
        Button n1 = findViewById(R.id.n1);
        Button n2 = findViewById(R.id.n2);
        Button n3 = findViewById(R.id.n3);
        Button n4 = findViewById(R.id.n4);
        Button n5 = findViewById(R.id.n5);
        Button n6 = findViewById(R.id.n6);
        Button n7 = findViewById(R.id.n7);
        Button n8 = findViewById(R.id.n8);
        Button n9 = findViewById(R.id.n9);


        // Operadores
        Button suma = findViewById(R.id.suma);
        Button resta = findViewById(R.id.resta);
        Button multi = findViewById(R.id.multi);
        Button dividir = findViewById(R.id.dividir);
        Button calcular = findViewById(R.id.calcular);
        Button borrarTodo = findViewById(R.id.borrarTodo);
        Button punto = findViewById(R.id.punto);

        // Listener para números
        View.OnClickListener listenerNumero = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                input += b.getText().toString();
                pantalla.setText(input);
            }
        };

        n0.setOnClickListener(listenerNumero);
        n1.setOnClickListener(listenerNumero);
        n2.setOnClickListener(listenerNumero);
        n3.setOnClickListener(listenerNumero);
        n4.setOnClickListener(listenerNumero);
        n5.setOnClickListener(listenerNumero);
        n6.setOnClickListener(listenerNumero);
        n7.setOnClickListener(listenerNumero);
        n8.setOnClickListener(listenerNumero);
        n9.setOnClickListener(listenerNumero);

        // Listener para operadores
        View.OnClickListener listenerOperador = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    primerNumero = Double.parseDouble(input);
                    input = "";
                    Button b = (Button) v;
                    operador = b.getText().toString();
                    operadorPresionado = true;
                    pantalla.setText("");
                }
            }
        };

        suma.setOnClickListener(listenerOperador);
        resta.setOnClickListener(listenerOperador);
        multi.setOnClickListener(listenerOperador);
        dividir.setOnClickListener(listenerOperador);

        // Listener para el botón igual (=)
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty() && operadorPresionado) {
                    double segundoNumero = Double.parseDouble(input);
                    double resultado = 0;

                    switch (operador) {
                        case "+":
                            resultado = primerNumero + segundoNumero;
                            break;
                        case "-":
                            resultado = primerNumero - segundoNumero;
                            break;
                        case "*":
                            resultado = primerNumero * segundoNumero;
                            break;
                        case "/":
                            if (segundoNumero != 0) {
                                resultado = primerNumero / segundoNumero;
                            } else {
                                pantalla.setText("Error");
                                return;
                            }
                            break;
                    }

                    pantalla.setText(String.valueOf(resultado));
                    input = String.valueOf(resultado);
                    operadorPresionado = false;
                }
            }
        });

        // Listener para el botón de punto decimal
        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.contains(".")) {
                    input += ".";
                    pantalla.setText(input);
                }
            }
        });

        // Listener para el botón "C" (borrar todo)
        borrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                primerNumero = 0;
                operador = "";
                pantalla.setText("VISOR"); // Regresar al texto de sugerencia
            }
        });
    }
}
