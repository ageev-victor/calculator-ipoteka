package ru.ageev_victor.calculator_ipoteka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int stoimostKv;
    int pervonachVznos;
    int srokKredita;
    double procentStavka;
    String vidPlateza;
    Date nachaloViplat;
    double mesPlatez;
    int summaKredita;
    EditText stoimostKvEditText;
    EditText pervonachVznosEditText;
    EditText srokKreditaEditText;
    EditText procentStavkaEditText;
    TextView mesPlatezTextView;
    TextView pereplataTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void setDataFromViews() {
        srokKredita = Integer.parseInt(srokKreditaEditText.getText().toString());
        stoimostKv = Integer.parseInt(stoimostKvEditText.getText().toString());
        pervonachVznos = Integer.parseInt(pervonachVznosEditText.getText().toString());
        procentStavka = Double.parseDouble((procentStavkaEditText.getText().toString()));
    }

    private void initViews() {
        stoimostKvEditText = (EditText) findViewById(R.id.editText_stoimostKv);
        pervonachVznosEditText = (EditText) findViewById(R.id.editText_pervonachVznos);
        srokKreditaEditText = (EditText) findViewById(R.id.editText_srokKredita);
        procentStavkaEditText = (EditText) findViewById(R.id.editText_procentStavka);
        mesPlatezTextView = (TextView) findViewById(R.id.mesPlatezTextView);
        pereplataTextView = (TextView) findViewById(R.id.pereplataTextView);
    }


    public void calcucate(View view) {
        int pereplata;
        setDataFromViews();
        srokKredita = srokKredita * 12;
        double koefProcStavki = procentStavka / 12 / 100;
        double koefSt = Math.pow((1 + koefProcStavki), srokKredita);
        summaKredita = stoimostKv - pervonachVznos;
        mesPlatez = summaKredita * (koefProcStavki + koefProcStavki / (koefSt - 1));
        mesPlatezTextView.setText(String.valueOf((int) mesPlatez) + " руб/мес");
        pereplata = (int) (mesPlatez*srokKredita - summaKredita);
        pereplataTextView.setText(pereplata + " руб");
    }
}
