package com.example.hubert02;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hubert02.R;

public class MainActivity extends AppCompatActivity {

    EditText Wzrost , waga;
    RadioGroup grupa;
    CheckBox Zapisz;
    Button przycisk;
    TextView Wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_main);

        Wzrost = findViewById(R.id.etWzrost);
        waga=findViewById(R.id.etwaga);
        grupa = findViewById(R.id.grupa);;
        Zapisz = findViewById(R.id.cbZapisz);
        przycisk = findViewById(R.id.btnOblicz);

        Wynik = findViewById(R.id.tvWynik);


        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String w = Wzrost.getText().toString().trim().replace(',', '.');
                String Kg = waga.getText().toString().trim().replace(',', '.');;

                if (w.isEmpty() || Kg.isEmpty()) {
                    if (w.isEmpty()) {
                        Wzrost.setError("Pole nie moze byc puste!");
                    }
                    if (Kg.isEmpty())  {
                        waga.setError("Pole nie moze byc puste!");
                    }
                    return;
                }

                if (grupa.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Wybierz plec", Toast.LENGTH_SHORT).show();
                    return;
                }

                double h;
                double Masa;
                try {
                    h = Double.parseDouble(w);
                    Masa = Double.parseDouble(Kg);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Zle dane", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (h <= 0 || Masa <= 0) {
                    Toast.makeText(MainActivity.this, "Liczby musza byc wieksze od 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                double hm = h / 100;;
                double BMI = Masa / (hm * hm);

                String opis;
                if (BMI < 18.5) {
                    opis = "Niedowaga";
                } else if (BMI < 25) {
                    opis = "Waga prawidlowa";
                } else if (BMI < 30) {
                    opis = "Nadwaga";
                } else {
                    opis = "Otylosc";
                }

                String plec = "";
                if (grupa.getCheckedRadioButtonId() == R.id.rbK) {
                    plec = "Kobieta";
                } else {
                    plec = "Mezczyzna";
                }

                String tekst = plec + "\nBMI: " + String.format("%.2f", BMI) + "\n" + opis;;

                if (Zapisz.isChecked()) {
                    tekst = tekst + "\n(dane zapisane)";
                }

                Wynik.setText(tekst);

            }
        });


    }
}
