package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {

    String TAG = "EJEMPLO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("EJEMPLO", "onCreate() llamado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() llamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() llamado");
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG, "onPause() llamado");
//    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() llamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() llamado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() llamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);

        //Iniciar la actividad nueva
        startActivity(intent);

        Log.d("Ejemplo", "onPause() llamado");
    }

}



