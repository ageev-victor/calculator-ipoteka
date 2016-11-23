package ru.ageev_victor.calculator_ipoteka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static int stoimostKv;
    static int pervonachVznos;
    static int SrokKredita;
    static double procentStavka;
    static double mesPlatez;
    static int summaKredita;
    EditText stoimostKvEditText;
    EditText pervonachVznosEditText;
    EditText srokKreditaEditText;
    EditText procentStavkaEditText;
    //EditText dosrochiPlategiEditText;
    TextView mesPlatezTextView;
    TextView pereplataChisloTextView;
    public static ArrayList<Row> rows = new ArrayList<>();
    TableLayout tablePlatezei;
    static double koefProcStavki;
    //static double dosrochniPlategi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void setDataFromViews() {
        SrokKredita = Integer.parseInt(srokKreditaEditText.getText().toString());
        stoimostKv = Integer.parseInt(stoimostKvEditText.getText().toString());
        pervonachVznos = Integer.parseInt(pervonachVznosEditText.getText().toString());
        procentStavka = Double.parseDouble((procentStavkaEditText.getText().toString()));
        //dosrochniPlategi = Double.parseDouble((dosrochiPlategiEditText.getText().toString()));
    }

    private void initViews() {
        stoimostKvEditText = (EditText) findViewById(R.id.editText_stoimostKv);
        pervonachVznosEditText = (EditText) findViewById(R.id.editText_pervonachVznos);
        srokKreditaEditText = (EditText) findViewById(R.id.editText_srokKredita);
        procentStavkaEditText = (EditText) findViewById(R.id.editText_procentStavka);
        mesPlatezTextView = (TextView) findViewById(R.id.mesPlatezTextView);
        pereplataChisloTextView = (TextView) findViewById(R.id.pereplataChisloTextView);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
        //dosrochiPlategiEditText = (EditText) findViewById(R.id.editText_dosrochiPlategi);
    }


    public void calculate(View view) {
        int pereplata;
        setDataFromViews();
        SrokKredita = SrokKredita * 12;
        koefProcStavki = procentStavka / 12 / 100;
        double koefSt = Math.pow((1 + koefProcStavki), SrokKredita);
        summaKredita = stoimostKv - pervonachVznos;
        mesPlatez = summaKredita * (koefProcStavki + koefProcStavki / (koefSt - 1));
        mesPlatezTextView.setText(String.valueOf((int) mesPlatez) + " руб/мес");
        pereplata = (int) (mesPlatez * SrokKredita - summaKredita);
        pereplataChisloTextView.setText(pereplata + " руб");
    }

    public void showTablPlatezei(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
        }

    public void clearText(View view){
        if(view.equals(pervonachVznosEditText)){
            pervonachVznosEditText.setText("");
        }
    }
    }
