package com.example.quiz4;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String[] currencyOptions = {"USD", "EUR", "GBP", "YEN"};
    private double exchangeRate = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText liraAmountEditText = findViewById(R.id.liraAmount);
        final EditText convertedAmountEditText = findViewById(R.id.convertedAmount);
        ListView currencyListView = findViewById(R.id.currencyListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencyOptions);
        currencyListView.setAdapter(adapter);

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exchangeRate = getExchangeRate(currencyOptions[position]);
                convertAmount();
            }
        });

        liraAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Leave it empty
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Leave it empty
            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertAmount();
            }
        });

        final TextView titleText = findViewById(R.id.titleText);
        animateTitleText(titleText);
    }

    private double getExchangeRate(String selectedCurrency) {
        if (selectedCurrency.equals("USD")) {
            return 18.5;
        } else if (selectedCurrency.equals("EUR")) {
            return 18.3;
        } else if (selectedCurrency.equals("GBP")) {
            return 21.1;
        } else if (selectedCurrency.equals("YEN")) {
            return 7.84;
        } else {
            return 0.0;
        }
    }

    private void convertAmount() {
        EditText liraAmountEditText = findViewById(R.id.liraAmount);
        EditText convertedAmountEditText = findViewById(R.id.convertedAmount);

        String liraAmountStr = liraAmountEditText.getText().toString();
        if (!liraAmountStr.isEmpty()) {
            double liraAmount = Double.parseDouble(liraAmountStr);
            double convertedAmount = liraAmount * exchangeRate;
            convertedAmountEditText.setText(String.format("%.2f", convertedAmount));
        }
    }

    private void animateTitleText(final TextView titleText) {
        int yellowColor = 0xFFFF00FF; // Yellow color in ARGB format
        int pinkColor = 0xFFFA58F4; // Pink color in ARGB format

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), pinkColor, yellowColor);
        colorAnimation.setDuration(2000); // 2000 milliseconds or 2 seconds

        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                titleText.setTextColor((int) animator.getAnimatedValue());
            }
        });

        colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimation.start();
    }
}
