package com.example.actividad4tm5;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numero1;
    private EditText numero2;
    private CheckBox checkBoxSumar;
    private CheckBox checkBoxRestar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        checkBoxSumar = findViewById(R.id.checkBoxSumar);
        checkBoxRestar = findViewById(R.id.checkBoxRestar);
        textoResultado = findViewById(R.id.textoResultado);

        // Listener para el CheckBox de sumar
        checkBoxSumar.setOnCheckedChangeListener((buttonView, isChecked) -> realizarOperacion());

        // Listener para el CheckBox de restar
        checkBoxRestar.setOnCheckedChangeListener((buttonView, isChecked) -> realizarOperacion());
    }

    // Método para realizar la operación según los CheckBox seleccionados
    private void realizarOperacion() {
        // Verificar si los EditText tienen valores
        if (TextUtils.isEmpty(numero1.getText()) || TextUtils.isEmpty(numero2.getText())) {
            textoResultado.setText("Ingrese ambos números");
            return;
        }

        // Convertir los valores ingresados a enteros
        int num1 = Integer.parseInt(numero1.getText().toString());
        int num2 = Integer.parseInt(numero2.getText().toString());

        // Variable para almacenar los resultados
        StringBuilder resultado = new StringBuilder();

        // Verificar si el CheckBox de sumar está seleccionado
        if (checkBoxSumar.isChecked()) {
            int suma = num1 + num2;
            resultado.append("Suma: ").append(suma).append("\n");
        }

        // Verificar si el CheckBox de restar está seleccionado
        if (checkBoxRestar.isChecked()) {
            int resta = num1 - num2;
            resultado.append("Resta: ").append(resta).append("\n");
        }

        // Mostrar el resultado en el TextView
        textoResultado.setText(resultado.length() > 0 ? resultado.toString() : "Seleccione una operación");
    }
}