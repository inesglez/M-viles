package com.example.actividad4tm1;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDial = findViewById(R.id.buttonDial);
        Button buttonCall = findViewById(R.id.buttonCall);

        // Botón para abrir el marcador
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMarcador("123456789"); // Cambia por el número que desees
            }
        });

        // Botón para hacer la llamada directa
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerLlamada("123456789"); // Cambia por el número que desees
            }
        });
    }

    // Método para abrir el marcador
    private void abrirMarcador(String numeroTelefono) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numeroTelefono));
        startActivity(intent);
    }

    // Método para hacer la llamada directa
    private void hacerLlamada(String numeroTelefono) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Si no tiene el permiso, solicita el permiso
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Si ya tiene el permiso, realiza la llamada
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numeroTelefono));
            startActivity(intent);
        }
    }

    // Manejo de la respuesta del usuario a la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realizar la llamada
                hacerLlamada("123456789"); // Cambia por el número que desees
            } else {
                // Permiso denegado, mostrar un mensaje
                // Aquí puedes mostrar un Toast o un mensaje al usuario
            }
        }
    }
}