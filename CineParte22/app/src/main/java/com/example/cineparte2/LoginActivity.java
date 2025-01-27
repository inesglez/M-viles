package com.example.cineparte2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private EditText inputNombre, inputClave;
    private Button btnIniciarSesion, btnInformacion;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas
        inputNombre = findViewById(R.id.inputNombre);
        inputClave = findViewById(R.id.inputClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnInformacion = findViewById(R.id.btnInformacion);

        // Inicializar la base de datos
        dbHelper = new DatabaseHelper(this);

        // Agregar usuarios predeterminados (solo una vez, opcional)
        dbHelper.insertarUsuario("admin", "1234", "administrador");
        dbHelper.insertarUsuario("ines", "1234", "normal");
        dbHelper.insertarUsuario("usuario2", "1234", "normal");

        // Cargar último usuario conectado
        cargarUltimoUsuario();

        // Configurar el evento de clic en el botón "Iniciar sesión"
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputNombre.getText().toString();
                String clave = inputClave.getText().toString();

                if (verificarUsuario(nombre, clave)) {
                    // Guardar el nombre del usuario en SharedPreferences
                    guardarUltimoUsuario(nombre);
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el evento de clic en el botón "Información"
        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInformacion();
            }
        });
    }

    private void cargarUltimoUsuario() {
        SharedPreferences preferences = getSharedPreferences("cine_app_prefs", MODE_PRIVATE);
        String lastUsername = preferences.getString("last_username", "");
        inputNombre.setText(lastUsername);
    }

    private void guardarUltimoUsuario(String nombre) {
        SharedPreferences preferences = getSharedPreferences("cine_app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("last_username", nombre);
        editor.apply();
    }

    private boolean verificarUsuario(String nombre, String clave) {
        // Verificar credenciales en la base de datos
        Cursor cursor = dbHelper.verificarCredenciales(nombre, clave);
        if (cursor != null && cursor.moveToFirst()) {
            int rolIndex = cursor.getColumnIndex("rol");

            if (rolIndex == -1) {
                // Manejar el caso donde no se encuentra la columna "rol"
                cursor.close();
                return false;
            }

            String rol = cursor.getString(rolIndex);  // Acceder al valor del rol usando el índice válido

            Intent intent;
            if (rol.equals("administrador")) {
                intent = new Intent(LoginActivity.this, AdminActivity.class);
            } else {
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
            cursor.close(); // Cerrar el cursor después de usarlo
            return true;
        }
        cursor.close();  // Asegúrate de cerrar el cursor en todos los casos
        return false;
    }

    private void mostrarInformacion() {
        // Leer el archivo de texto desde res/raw
        InputStream inputStream = getResources().openRawResource(R.raw.informacion_uso);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String informacion = stringBuilder.toString();

        // Crear y mostrar el cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage(informacion);
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
