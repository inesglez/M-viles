package com.example.actividad3tm5;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numero1;
    private EditText numero2;
    private CheckBox checkBoxSumar;
    private CheckBox checkBoxRestar;
    private Button botonCalcular;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        checkBoxSumar = findViewById(R.id.checkBoxSumar);
        checkBoxRestar = findViewById(R.id.checkBoxRestar);
        botonCalcular = findViewById(R.id.botonCalcular);
        textoResultado = findViewById(R.id.textoResultado);

        botonCalcular.setOnClickListener(v -> {
            // Convertir los valores ingresados a enteros
            int num1 = Integer.parseInt(numero1.getText().toString());
            int num2 = Integer.parseInt(numero2.getText().toString());

            // Variable para almacenar los resultados
            StringBuilder resultado = new StringBuilder();

            // Verificar si el checkbox de sumar está seleccionado
            if (checkBoxSumar.isChecked()) {
                int suma = num1 + num2;
                resultado.append("Suma: ").append(suma).append("\n");
            }

            // Verificar si el checkbox de restar está seleccionado
            if (checkBoxRestar.isChecked()) {
                int resta = num1 - num2;
                resultado.append("Resta: ").append(resta).append("\n");
            }

            // Mostrar el resultado en el TextView
            textoResultado.setText(resultado.length() > 0 ? resultado.toString() : "Seleccione una operación");
        });
    }
}