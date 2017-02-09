package ru.ageev_victor.calculator_ipoteka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int stoimostKv;
    private int pervonachVznos;
    protected static int srokKredita;
    private double procentStavka;
    protected static double mesPlatez;
    protected static double summaKredita;
    protected static double koefProcStavki;
    private boolean successCalculated = false;

    EditText stoimostKvEditText;
    EditText pervonachVznosEditText;
    EditText srokKreditaEditText;
    EditText procentStavkaEditText;
    TextView mesPlatezTextView;
    TextView pereplataChisloTextView;
    TableLayout tablePlatezei;

    private String errorSrok = "Допустимый срок от 1 до 35 лет";
    private String errorStavka = "Допустимая ставка от 1 до 35 %";
    private String errorStoimost = "Допустимая стоимость от 10000 до 100000000";
    private String errorPervVznos = "Первоначальный взнос от 0 и не больше стоимости";
    static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Подождите...");
        mProgressDialog.show();
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
        srokKredita = Integer.parseInt(srokKreditaEditText.getText().toString());
        stoimostKv = Integer.parseInt(stoimostKvEditText.getText().toString());
        procentStavka = Double.parseDouble((procentStavkaEditText.getText().toString()));
        pervonachVznos = Integer.parseInt(pervonachVznosEditText.getText().toString());
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

    public void calculate(View view) {
        new CalculateTask().execute();
    }

    private void repeatCalculate() {
        summaKredita = stoimostKv - pervonachVznos;
    }

    public void showTablPlatezei(View view) {
        if (successCalculated) {
            repeatCalculate();
            showProgressDialog();
            Intent intent = new Intent(getBaseContext(), TableActivity.class);
            startActivity(intent);
        }
    }

    public void clearEdText(View view) {
        switch (view.getId()) {
            case R.id.clear_stoimost: {
                stoimostKvEditText.setText("");
                stoimostKvEditText.requestFocus();
                break;
            }
            case R.id.clear_srok: {
                srokKreditaEditText.setText("");
                srokKreditaEditText.requestFocus();
                break;
            }
            case R.id.clear_pervi_vznos: {
                pervonachVznosEditText.setText("");
                pervonachVznosEditText.requestFocus();
                break;
            }
            case R.id.clear_stavka:
                procentStavkaEditText.setText("");
                procentStavkaEditText.requestFocus();
                break;
        }
    }

        class CalculateTask extends AsyncTask<Void, Void, Void> {
            double pereplata;
            NumberFormatException exception;

            protected Void doInBackground(Void... voids) {
                try {
                    setDataFromViews();
                    if (srokKredita > 35 | srokKredita < 1)
                        throw new NumberFormatException(errorSrok);
                    if (stoimostKv > 100000000 | stoimostKv < 10000)
                        throw new NumberFormatException(errorStoimost);
                    if (procentStavka > 35 | procentStavka < 1)
                        throw new NumberFormatException(errorStavka);
                    if (pervonachVznos > stoimostKv)
                        throw new NumberFormatException(errorPervVznos);
                    srokKredita = srokKredita * 12;
                    koefProcStavki = procentStavka / 12 / 100;
                    double koefSt = Math.pow((1 + koefProcStavki), srokKredita);
                    summaKredita = stoimostKv - pervonachVznos;
                    mesPlatez = (float) (summaKredita * (koefProcStavki + koefProcStavki / (koefSt - 1)));
                    pereplata =  (mesPlatez * srokKredita - summaKredita);
                    successCalculated = true;
                } catch (NumberFormatException e) {
                    successCalculated = false;
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (exception != null) {
                    String errorDefault = "Есть незаполненные поля!";
                    exceptionHandling(errorSrok, errorStavka, errorStoimost, errorPervVznos, errorDefault, exception);
                }
                String valuta = " руб";
                String formattedmesPlatez = String.format("%,d", ((int) mesPlatez));
                mesPlatezTextView.setText(formattedmesPlatez.concat(valuta));
                String formattedPereplata =  String.format("%,d", ((int) pereplata));
                pereplataChisloTextView.setText(formattedPereplata.concat(valuta));
            }

            private void exceptionHandling(String errorSrok, String errorStavka, String errorStoimost, String errorPervVznos, String errorDefault, NumberFormatException e) {
                switch (e.getMessage()) {
                    case "Допустимый срок от 1 до 35 лет":
                        srokKreditaEditText.setText("");
                        srokKreditaEditText.requestFocus();
                        Toast.makeText(getApplicationContext(), errorSrok, Toast.LENGTH_SHORT).show();
                        break;
                    case "Допустимая ставка от 1 до 35 %":
                        procentStavkaEditText.setText("");
                        procentStavkaEditText.requestFocus();
                        Toast.makeText(getApplicationContext(), errorStavka, Toast.LENGTH_SHORT).show();
                        break;
                    case "Допустимая стоимость от 10000 до 100000000":
                        stoimostKvEditText.setText("");
                        stoimostKvEditText.requestFocus();
                        Toast.makeText(getApplicationContext(), errorStoimost, Toast.LENGTH_SHORT).show();
                        break;
                    case "Первоначальный взнос от 0 и не больше стоимости":
                        pervonachVznosEditText.setText("");
                        pervonachVznosEditText.requestFocus();
                        Toast.makeText(getApplicationContext(), errorPervVznos, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), errorDefault, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
