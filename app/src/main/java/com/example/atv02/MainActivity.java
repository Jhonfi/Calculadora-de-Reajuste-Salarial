package com.example.atv02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText editTextSalario;
    RadioGroup radioGroupPercentual;
    Button buttonCalcularReajuste;
    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSalario = findViewById(R.id.editTextSalario);
        radioGroupPercentual = findViewById(R.id.radioGroupPercentual);
        buttonCalcularReajuste = findViewById(R.id.buttonCalcularReajuste);
        textViewResultado = findViewById(R.id.textViewResultado);

        buttonCalcularReajuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularReajuste();
            }
        });
    }

    private void calcularReajuste() {
        String salarioStr = editTextSalario.getText().toString();

        if (salarioStr.isEmpty()) {
            Toast.makeText(this, "Por favor, digite o salário.", Toast.LENGTH_SHORT).show();
            return;
        }

        double salarioAtual;
        try {
            salarioAtual = Double.parseDouble(salarioStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Salário inválido. Use apenas números e ponto decimal.", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = radioGroupPercentual.getCheckedRadioButtonId();
        double percentual = 0.0;
        int percentualValor = 0; // Para exibir no resultado

        if (selectedId == R.id.radio40Percent) {
            percentual = 0.40;
            percentualValor = 40;
        } else if (selectedId == R.id.radio45Percent) {
            percentual = 0.45;
            percentualValor = 45;
        } else if (selectedId == R.id.radio50Percent) {
            percentual = 0.50;
            percentualValor = 50;
        } else {
            Toast.makeText(this, "Por favor, selecione um percentual de aumento.", Toast.LENGTH_SHORT).show();
            return;
        }

        double aumento = salarioAtual * percentual;
        double novoSalario = salarioAtual + aumento;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String salarioAtualFormatado = decimalFormat.format(salarioAtual);
        String aumentoFormatado = decimalFormat.format(aumento);
        String novoSalarioFormatado = decimalFormat.format(novoSalario);

        String resultado = "Salário Original: R$ " + salarioAtualFormatado + "\n" +
                "Aumento (" + percentualValor + "%): R$ " + aumentoFormatado + "\n" +
                "Novo Salário: R$ " + novoSalarioFormatado;

        textViewResultado.setText(resultado);
    }
}