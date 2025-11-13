package com.pucpr.imcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText alturaEditText;
    EditText pesoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        alturaEditText = findViewById(R.id.alturaEditText);
        pesoEditText = findViewById(R.id.pesoEditText);

    }


    public void calcularButtonOnClick(View view) {
        String alturaString = alturaEditText.getText().toString();
        String pesoString = pesoEditText.getText().toString();

        double altura = Double.parseDouble(alturaString);
        double peso = Double.parseDouble(pesoString);
        double calculo;


        calculo = peso / (altura * altura);

        if (calculo < 18.5) {
            Toast.makeText(this, "Abaixo do peso", Toast.LENGTH_SHORT).show();
        } else if (calculo >= 18.5 && calculo <= 24.9) {
            Toast.makeText(this, "Peso normal", Toast.LENGTH_SHORT).show();
        } else if (calculo >= 25 && calculo <= 29.9) {
            Toast.makeText(this, "Sobrepeso", Toast.LENGTH_SHORT).show();
        }else if (calculo >= 30 && calculo <= 34.9) {
            Toast.makeText(this, "Obesidade grau 1", Toast.LENGTH_SHORT).show();
        }else if (calculo >= 35 && calculo <= 39.9) {
            Toast.makeText(this, "Obesidade grau 2", Toast.LENGTH_SHORT).show();
        }else if (calculo >= 40) {
            Toast.makeText(this, "Obesidade grau 3", Toast.LENGTH_SHORT).show();
        }
    }
}