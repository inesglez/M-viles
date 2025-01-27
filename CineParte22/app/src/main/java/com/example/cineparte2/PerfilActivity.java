package com.example.cineparte2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private ImageView imageViewPerfil;
    private TextView textViewNombreCompleto, textViewFechaNacimiento;
    private EditText editTextNombre, editTextCorreo, editTextTelefono, editTextContrasena;
    private Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializar vistas
        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        textViewNombreCompleto = findViewById(R.id.textViewNombreCompleto);
        textViewFechaNacimiento = findViewById(R.id.textViewFechaNacimiento);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        // Configuración inicial de los datos (puedes obtenerlos de un backend o BD)
        textViewNombreCompleto.setText("Inés González");
        textViewFechaNacimiento.setText("28/12/2002");

        // Guardar cambios
        btnGuardar.setOnClickListener(v -> {
            String nuevoNombre = editTextNombre.getText().toString();
            String nuevoCorreo = editTextCorreo.getText().toString();
            String nuevoTelefono = editTextTelefono.getText().toString();
            String nuevaContrasena = editTextContrasena.getText().toString();

            // Aquí puedes guardar los cambios en una base de datos o enviarlos a un backend
            Toast.makeText(this, "Cambios guardados:\n" +
                            "Nombre: " + nuevoNombre + "\n" +
                            "Correo: " + nuevoCorreo + "\n" +
                            "Teléfono: " + nuevoTelefono + "\n" +
                            (nuevaContrasena.isEmpty() ? "Contraseña: No modificada" : "Contraseña: Actualizada"),
                    Toast.LENGTH_LONG).show();
            finish();
        });

        // Configurar botón Cancelar
        btnCancelar.setOnClickListener(v -> finish());
    }
}
