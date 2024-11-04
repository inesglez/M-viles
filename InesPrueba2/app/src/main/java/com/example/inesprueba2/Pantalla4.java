package com.example.inesprueba2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

public class Pantalla4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla4);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] androidVersions = {
                "APPLE PIE", "BANANA BREAD", "CUPCAKE", "DONUT", "ECLAIR",
                "FROYO", "GINGERBREAD", "HONEYCOMB", "ICE CREAM SANDWICH",
                "JELLY BEAN", "KITKAT", "LOLLIPOP", "MARSHMALLOW", "NOUGAT",
                "OREO", "PIE", "ANDROID 10"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, androidVersions);
        autoCompleteTextView.setAdapter(adapter);
    }
}
