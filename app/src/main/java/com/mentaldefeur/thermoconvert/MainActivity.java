package com.mentaldefeur.thermoconvert;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private EditText edt;
    private RadioButton radio1;
    private RadioButton radio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBtn = findViewById(R.id.btnConvert);
        Button getBtnEffacer = findViewById(R.id.btnEffacer);
        edt = findViewById(R.id.etTemperature);
        radio1 = findViewById(R.id.rbCelsiusToFahrenheit);
        radio2 = findViewById(R.id.rbFahrenheitToCelsius);
        txtView = findViewById(R.id.txtAffiche);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edt.getText().toString();
                if (input.isEmpty()) {
                    Snackbar.make(view, "Veuillez entrer une température", Snackbar.LENGTH_LONG).show();
                    return;
                }

                try {
                    if (radio1.isChecked()) {
                        txtView.setText(convertCelsiusToFahrenheit(input));
                    } else if (radio2.isChecked()) {
                        txtView.setText(convertFahrenheitToCelsius(input));
                    } else {
                        Snackbar.make(view, "Veuillez sélectionner une conversion", Snackbar.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    Snackbar.make(view, "Valeur invalide", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        getBtnEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText("");
                txtView.setText("");
                edt.requestFocus();
            }
        });
    }

    private String convertCelsiusToFahrenheit(String celsius) {
        double celsiusValue = Double.parseDouble(celsius.replace(',', '.'));
        double fahrenheitValue = (celsiusValue * 9.0 / 5.0) + 32;
        return String.format("%.2f", fahrenheitValue);
    }

    private String convertFahrenheitToCelsius(String fahrenheit) {
        double fahrenheitValue = Double.parseDouble(fahrenheit.replace(',', '.'));
        double celsiusValue = (fahrenheitValue - 32) * 5.0 / 9.0;
        return String.format("%.2f", celsiusValue);
    }

}