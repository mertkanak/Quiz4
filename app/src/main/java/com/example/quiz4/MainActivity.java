package com.example.quiz4;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    private String[] currencyOptions = {"USD", "EUR", "GBP", "YEN"};
    private double exchangeRate = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText liraAmountEditText = findViewById(R.id.liraAmount);
        final EditText convertedAmountEditText = findViewById(R.id.convertedAmount);
        Button convertButton = findViewById(R.id.convertButton);
        Button resetButton = findViewById(R.id.resetButton);

        final ListView currencyListView = findViewById(R.id.currencyListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencyOptions);
        currencyListView.setAdapter(adapter);

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exchangeRate = getExchangeRate(currencyOptions[position]);
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liraAmountStr = liraAmountEditText.getText().toString();
                if (!liraAmountStr.isEmpty()) {
                    double liraAmount = Double.parseDouble(liraAmountStr);
                    double convertedAmount = liraAmount * exchangeRate;
                    convertedAmountEditText.setText(String.format("%.2f", convertedAmount));
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liraAmountEditText.setText("");
                convertedAmountEditText.setText("");
            }
        });
    }

    private double getExchangeRate(String selectedCurrency) {
        // Bu kısmı doldurmanız gerekiyor.
        // Seçilen para birimine göre gerçek döviz kurlarını almanız gerekiyor.
        // Örnek olarak, sabit döviz kurları kullanabilirsiniz (örneğin, USD için 13.0).
        if (selectedCurrency.equals("USD")) {
            return 13.0;
        } else if (selectedCurrency.equals("EUR")) {
            return 15.0;
        } else if (selectedCurrency.equals("GBP")) {
            return 18.0;
        } else if (selectedCurrency.equals("YEN")) {
            return 4.0;
        } else {
            return 0.0; // Eğer geçerli bir para birimi yoksa, sıfırı döndürün.
        }
    }
}

