package com.example.actividad6tm5;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CheckBox myCheckBox;
    private RadioButton myRadioButton;
    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCheckBox = findViewById(R.id.myCheckBox);
        myRadioButton = findViewById(R.id.myRadioButton);
        mySwitch = findViewById(R.id.mySwitch);

        // Configura los listeners según sea necesario
        myCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Manejar el evento de cambio de CheckBox
        });

        myRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Manejar el evento de cambio de RadioButton
        });

        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Manejar el evento de cambio de Switch
        });
    }
}