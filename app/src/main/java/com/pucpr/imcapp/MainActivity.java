package com.pucpr.imcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText alturaEditText;
    EditText pesoEditText;
    Button calcularButton;
    TextView resultadoValorTextView;
    TextView resultadoClassificacaoTextView;

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
        calcularButton = findViewById(R.id.calcularButton);
        resultadoValorTextView = findViewById(R.id.resultadoValorTextView);
        resultadoClassificacaoTextView = findViewById(R.id.resultadoClassificacaoTextView);

    }


    public void calcularButtonOnClick(View view) {

        // aqui ve qual o texto ATUAL do botao
        String textoBotao = calcularButton.getText().toString();

        if (textoBotao.equals("Fazer novo cálculo")) {
            alturaEditText.setText("");
            pesoEditText.setText("");
            resultadoValorTextView.setText("");
            resultadoClassificacaoTextView.setText("");

            resultadoValorTextView.setVisibility(View.GONE);
            resultadoClassificacaoTextView.setVisibility(View.GONE);

            calcularButton.setText("Calcular");
            return;
        }

        // usando o toast para validar se os campos tao vazios
        String alturaString = alturaEditText.getText().toString();
        String pesoString = pesoEditText.getText().toString();

        if (alturaString.isEmpty() || pesoString.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
            return;
        }

        // calculo do imc
        double altura = Double.parseDouble(alturaString);
        double peso = Double.parseDouble(pesoString);
        double calculo = peso / (altura * altura);

        // tosat de confirmacao
        Toast.makeText(this, "Cálculo realizado com sucesso!", Toast.LENGTH_SHORT).show();

        // msgs que vao variar de acordo com o resultado
        String classificacao = "";

        if (calculo < 18.5) {
            classificacao = "Abaixo do peso";
        } else if (calculo <= 24.9) {
            classificacao = "Normal";
        } else if (calculo <= 29.9) {
            classificacao = "Sobrepeso";
        } else if (calculo <= 34.9) {
            classificacao = "Obesidade grau 1";
        } else if (calculo <= 39.9) {
            classificacao = "Obesidade grau 2";
        } else {
            classificacao = "Obesidade grau 3";
        }

        String textoValor = String.format("SEU IMC É DE %.1f Kg/m².", calculo);
        String textoClassificacao = "De acordo com a Organização Mundial da Saúde, seu IMC é considerado " + classificacao + " para a sua altura."
                + "\n\nEste cálculo é apenas uma referência. Em caso de dúvidas, procure um médico especialista.";

        // mostrar os resultados
        resultadoValorTextView.setText(textoValor);
        resultadoClassificacaoTextView.setText(textoClassificacao);

        resultadoValorTextView.setVisibility(View.VISIBLE);
        resultadoClassificacaoTextView.setVisibility(View.VISIBLE);

        // muda o botao para novo calculo
        calcularButton.setText("Fazer novo cálculo");
    }
}