package com.example.actividad5tm5;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView seleccionDia;
    private RadioGroup grupoDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seleccionDia = findViewById(R.id.seleccionDia);
        grupoDias = findViewById(R.id.grupoDias);

        // Listener para detectar cambios en el RadioGroup
        grupoDias.setOnCheckedChangeListener((group, checkedId) -> {
            // Obtener el RadioButton seleccionado
            RadioButton radioButtonSeleccionado = findViewById(checkedId);

            // Actualizar el TextView con el texto del RadioButton seleccionado
            if (radioButtonSeleccionado != null) {
                String diaSeleccionado = radioButtonSeleccionado.getText().toString();
                seleccionDia.setText("Pulsado: " + diaSeleccionado);
            }
        });
    }
}
