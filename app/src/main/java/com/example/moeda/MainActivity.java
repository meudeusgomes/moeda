package com.example.moeda;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ConvertTask.OnConversionListener {

    private EditText editTextAmount;
    private TextView textViewResult;
    private Button clearButton, closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RETIRA A BARRA DE STATUS VISÍVEL
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextAmount = findViewById(R.id.editTextAmount);
        textViewResult = findViewById(R.id.textViewResult);
        clearButton = findViewById(R.id.limparBtn);
        closeButton = findViewById(R.id.fecharBtn);
        Button buttonConvert = findViewById(R.id.buttonConvert);

        // CONFIGURAÇÃO DO BOTÃO LIMPAR DADOS
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LIMPA OS CAMPOS INPUT e RESULTADO
                editTextAmount.setText("");
                textViewResult.setText("");

                // TOAST BOTÃO LIMPAR DADOS
                Toast.makeText(MainActivity.this, "LIMPANDO DADOS", Toast.LENGTH_SHORT).show();
            }
        });

        // CONFIGURAÇÃO DO BOTÃO FECHAR
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // CONFIGURAÇÃO BOTAO CONVERTER
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VALOR INSERIDO PELO USUÁRIO
                String amount = editTextAmount.getText().toString();

                // VERIFICAÇÃO SE O VALOR NAO ESTÁ VAZIO
                if (!amount.isEmpty()) {
                    // DEFINE URL DA API
                    String apiUrl = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
                    String fullUrl = apiUrl.replace(":moedas", amount);

                    // EXECUÇÃO DA TAREFA/TASK
                    ConvertTask convertTask = new ConvertTask(MainActivity.this);
                    convertTask.execute(fullUrl);
                }
            }
        });
    }

    @Override
    public void onConversionComplete(String result) {
        try {
            // Parse the JSON response from the conversion API
            JSONObject jsonObject = new JSONObject(result);
            JSONObject infoObject = jsonObject.getJSONObject("USDBRL");
            String bidPrice = infoObject.getString("bid");

            // Calculate the converted amount based on the bid price
            double amount = Double.parseDouble(editTextAmount.getText().toString());
            double convertedAmount = amount * Double.parseDouble(bidPrice);

            // Display the conversion result in the TextView
            textViewResult.setText(String.format("%.2f USD = %.2f BRL", amount, convertedAmount));
        } catch (JSONException e) {
            // Handle JSON parsing errors
            e.printStackTrace();
        }
    }
}
