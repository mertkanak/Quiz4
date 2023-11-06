package com.example.quiz4;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView currencyListView = findViewById(R.id.currencyListView);

        // XML'deki dizi (array) kaynağını alın
        String[] currencyOptions = getResources().getStringArray(R.array.currency_array);

        // ArrayAdapter kullanarak ListView'i doldurun
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencyOptions);
        currencyListView.setAdapter(adapter);
    }
}
