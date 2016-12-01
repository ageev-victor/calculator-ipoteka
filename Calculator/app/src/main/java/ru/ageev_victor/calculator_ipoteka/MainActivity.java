package ru.ageev_victor.calculator_ipoteka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int stoimostKv;
    static int pervonachVznos;
    static int srokKredita;
    static double procentStavka;
    static double mesPlatez;
    static int summaKredita;
    EditText stoimostKvEditText;
    EditText pervonachVznosEditText;
    EditText srokKreditaEditText;
    EditText procentStavkaEditText;
    TextView mesPlatezTextView;
    TextView pereplataChisloTextView;
    TableLayout tablePlatezei;
    static double koefProcStavki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setDataFromViews() {
        String errorSrok = "Допустимый срок от 1 до 35 лет";
        String errorStavka = "Допустимая ставка от 0 до 30 %";
        String errorStoimost = "Допустимая стоимость от 1 до 100000000";
        String errorPervVznos = "Недопустимый первоначальный взнос";
        String errorDefault = "Есть незаполненные поля!";
        try {
            srokKredita = Integer.parseInt(srokKreditaEditText.getText().toString());
            if (srokKredita > 35 | srokKredita <= 1) throw new NumberFormatException(errorSrok);

            stoimostKv = Integer.parseInt(stoimostKvEditText.getText().toString());
            if (stoimostKv > 100000000 | stoimostKv <= 1)
                throw new NumberFormatException(errorStoimost);

            procentStavka = Double.parseDouble((procentStavkaEditText.getText().toString()));
            if (procentStavka > 30 | procentStavka <= 1)
                throw new NumberFormatException(errorStavka);

            pervonachVznos = Integer.parseInt(pervonachVznosEditText.getText().toString());
            if (pervonachVznos > stoimostKv | pervonachVznos <= 1)
                throw new NumberFormatException(errorPervVznos);


        } catch (NumberFormatException e) {
            switch (e.getMessage()) {
                case "Допустимый срок от 1 до 35 лет":
                    Toast.makeText(this, errorSrok, Toast.LENGTH_SHORT).show();
                    break;
                case "Допустимая ставка от 1 до 30 %":
                    Toast.makeText(this, errorStavka, Toast.LENGTH_SHORT).show();
                    break;
                case "Допустимая стоимость от 1 до 100000000":
                    Toast.makeText(this, errorStoimost, Toast.LENGTH_SHORT).show();
                    break;
                case "Недопустимый первоначальный взнос":
                    Toast.makeText(this, errorPervVznos, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, errorDefault, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initViews() {
        stoimostKvEditText = (EditText) findViewById(R.id.editText_stoimostKv);
        pervonachVznosEditText = (EditText) findViewById(R.id.editText_pervonachVznos);
        srokKreditaEditText = (EditText) findViewById(R.id.editText_srokKredita);
        procentStavkaEditText = (EditText) findViewById(R.id.editText_procentStavka);
        mesPlatezTextView = (TextView) findViewById(R.id.mesPlatezTextView);
        pereplataChisloTextView = (TextView) findViewById(R.id.pereplataChisloTextView);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void calculate(View viVew) {
        int pereplata;
        setDataFromViews();
        srokKredita = srokKredita * 12;
        koefProcStavki = procentStavka / 12 / 100;
        double koefSt = Math.pow((1 + koefProcStavki), srokKredita);
        summaKredita = stoimostKv - pervonachVznos;
        mesPlatez = summaKredita * (koefProcStavki + koefProcStavki / (koefSt - 1));
        mesPlatezTextView.setText(String.valueOf((int) mesPlatez) + " руб");
        pereplata = (int) (mesPlatez * srokKredita - summaKredita);
        pereplataChisloTextView.setText(pereplata + " руб");
        hideKeyboard();
    }

    public void repeatCalculate() {
        summaKredita = stoimostKv - pervonachVznos;
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void showTablPlatezei(View view) {
        repeatCalculate();
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }
}
