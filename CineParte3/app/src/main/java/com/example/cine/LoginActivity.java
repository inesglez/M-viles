package com.example.cine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText inputNombre, inputClave;
    private Button btnIniciarSesion;
    private List<Usuario> listaUsuarios;
    private MediaPlayer mediaPlayer; // Para la música de fondo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputNombre = findViewById(R.id.inputNombre);
        inputClave = findViewById(R.id.inputClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        // Inicializar lista de usuarios válidos
        inicializarListaUsuarios();

        // Iniciar la música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.musica_fondo); // Archivo en res/raw/
        mediaPlayer.setLooping(true); // Para que se repita en bucle
        mediaPlayer.start();

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputNombre.getText().toString();
                String clave = inputClave.getText().toString();

                if (verificarUsuario(nombre, clave)) {
                    // Usuario válido, redirigir según el rol
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
                // Detener la música antes de cambiar de pantalla
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                // Redirigir según el rol del usuario
                Intent intent;
                if (usuario.getRol().equals("administrador")) {
                    intent = new Intent(LoginActivity.this, AdminActivity.class);
                } else {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
