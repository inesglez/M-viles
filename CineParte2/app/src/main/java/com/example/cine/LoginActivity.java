package com.example.cine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText inputNombre, inputClave;
    private Button btnIniciarSesion;
    private List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputNombre = findViewById(R.id.inputNombre);
        inputClave = findViewById(R.id.inputClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        // Inicializar lista de usuarios válidos
        inicializarListaUsuarios();

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputNombre.getText().toString();
                String clave = inputClave.getText().toString();

                if (verificarUsuario(nombre, clave)) {
                    // Usuario válido, redirigir al MainActivity o AdminActivity según el rol
                } else {
                    // Credenciales incorrectas
                    Toast.makeText(LoginActivity.this, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarListaUsuarios() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("admin", "1234", "administrador"));
        listaUsuarios.add(new Usuario("ines", "1234", "normal"));
        listaUsuarios.add(new Usuario("usuario2", "1234", "normal"));
    }

    private boolean verificarUsuario(String nombre, String clave) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave)) {
                // Redirigir según el rol del usuario
                Intent intent;
                if (usuario.getRol().equals("administrador")) {
                    intent = new Intent(LoginActivity.this, AdminActivity.class);  // Administrador
                } else {
                    intent = new Intent(LoginActivity.this, MainActivity.class);  // Usuario normal
                }
                startActivity(intent);
                finish();
                return true;
            }
        }
        return false;
    }

}
