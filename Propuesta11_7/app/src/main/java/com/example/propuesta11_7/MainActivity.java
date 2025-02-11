package com.example.propuesta11_7;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Uri videoUri;
    private boolean grabando = false;

    private ArrayList<Uri> listaVideos = new ArrayList<>(); // Lista para almacenar los URI de los videos grabados

    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonGrabar = findViewById(R.id.botonGrabar);
        Button botonDetener = findViewById(R.id.botonDetener);
        Button botonReproducir = findViewById(R.id.botonReproducir);
        surfaceView = findViewById(R.id.surfaceView);

        botonDetener.setEnabled(false);

        if (!checkPermissions()) {
            requestPermissions();
        }

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                // Liberar recursos al destruir el SurfaceView
                liberarRecursos();
            }
        });

        botonGrabar.setOnClickListener(v -> iniciarGrabacion(botonGrabar, botonDetener));
        botonDetener.setOnClickListener(v -> detenerGrabacion(botonGrabar, botonDetener));
        botonReproducir.setOnClickListener(v -> reproducirVideo());
    }

    private void iniciarGrabacion(Button botonGrabar, Button botonDetener) {
        // Usar MediaStore para crear la ruta de guardado
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // Para Android 10 y superior
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, "mivideo_" + System.currentTimeMillis() + ".mp4"); // Nombre único
            values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES);

            videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);

            // Si la URI es válida, usala como destino para grabar
            if (videoUri != null) {
                try {
                    // Liberar cualquier recurso previo
                    liberarRecursos();

                    grabador = new MediaRecorder();
                    grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
                    grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                    // Establecer la orientación de la grabación
                    grabador.setOrientationHint(90);  // Cambiar a 0, 90, 180 o 270 según sea necesario

                    // Ajustar la rotación de la SurfaceView
                    int rotation = getWindowManager().getDefaultDisplay().getRotation();
                    int degrees = 0;

                    // Acomoda la rotación según la orientación del dispositivo
                    switch (rotation) {
                        case Surface.ROTATION_0:
                            degrees = 0;
                            break;
                        case Surface.ROTATION_90:
                            degrees = 90;
                            break;
                        case Surface.ROTATION_180:
                            degrees = 180;
                            break;
                        case Surface.ROTATION_270:
                            degrees = 270;
                            break;
                    }

                    // Establecer la rotación del SurfaceView
                    surfaceView.setRotation(degrees);

                    // Configurar el archivo de salida para el video
                    grabador.setOutputFile(getContentResolver().openFileDescriptor(videoUri, "w").getFileDescriptor());
                    grabador.setPreviewDisplay(surfaceHolder.getSurface());

                    grabador.prepare();
                    grabador.start();
                    grabando = true;
                    botonGrabar.setEnabled(false);
                    botonDetener.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Para Android 9 o anterior, usa el almacenamiento tradicional
            String ruta = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mivideo_" + System.currentTimeMillis() + ".mp4";
            grabador = new MediaRecorder();
            grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            grabador.setOutputFile(ruta);
            grabador.setPreviewDisplay(surfaceHolder.getSurface());

            // Establecer la orientación de la grabación
            grabador.setOrientationHint(90);  // Cambiar a 0, 90, 180 o 270 según sea necesario

            // Ajustar la rotación de la SurfaceView
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            int degrees = 0;

            // Acomoda la rotación según la orientación del dispositivo
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break;
                case Surface.ROTATION_90:
                    degrees = 90;
                    break;
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;
            }

            // Ajusta la rotación de la SurfaceView para la vista previa
            surfaceView.setRotation(degrees);

            try {
                grabador.prepare();
                grabador.start();
                grabando = true;
                botonGrabar.setEnabled(false);
                botonDetener.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void detenerGrabacion(Button botonGrabar, Button botonDetener) {
        if (grabando) {
            grabador.stop();
            grabador.release();
            grabador = null;
            grabando = false;
            botonGrabar.setEnabled(true);
            botonDetener.setEnabled(false);

            // Añadir el video grabado a la lista de videos
            listaVideos.add(videoUri);
            Toast.makeText(this, "Video grabado con éxito", Toast.LENGTH_SHORT).show();
        }
    }

    private void reproducirVideo() {
        if (listaVideos.isEmpty()) {
            Toast.makeText(this, "No hay videos grabados para reproducir", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el último video grabado
        Uri videoAReproducir = listaVideos.get(listaVideos.size() - 1); // Obtén el último video grabado

        // Liberar cualquier reproductor anterior
        if (reproductor != null) {
            reproductor.release();
        }

        reproductor = new MediaPlayer();
        try {
            // Usar la URI del último video grabado para reproducir
            reproductor.setDataSource(getContentResolver().openFileDescriptor(videoAReproducir, "r").getFileDescriptor());
            reproductor.setDisplay(surfaceHolder); // Mostrar video en el SurfaceView

            // Ajustar la orientación de la SurfaceView antes de comenzar la reproducción
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            int degrees = 0;

            // Acomoda la rotación según la orientación del dispositivo
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break;
                case Surface.ROTATION_90:
                    degrees = 90;
                    break;
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;
            }

            // Ajusta la rotación de la SurfaceView antes de reproducir
            surfaceView.setRotation(degrees);

            reproductor.prepare();
            reproductor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void liberarRecursos() {
        // Liberar recursos asociados con la grabación y reproducción
        if (grabador != null) {
            grabador.release();
            grabador = null;
        }

        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }

    private boolean checkPermissions() {
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int mic = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return camera == PackageManager.PERMISSION_GRANTED && mic == PackageManager.PERMISSION_GRANTED && storage == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_PERMISSIONS);
    }
}


