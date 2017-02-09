package ru.ageev_victor.calculator_ipoteka;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

public class Row extends TableRow {

    private int month;
    private double ostatokKredita;
    private double osnDolg;
    private double procenti;
    private double vsego;
    boolean onlyCalculate;

    TextView viewMonth;
    TextView viewOstatokKredita;
    TextView viewPlatez;
    TextView viewProcenti;
    TextView viewVsego;
    Context context;

    public Row(Context context, int month, Boolean onlyCalculate) {
        super(context);
        this.month = month;
        this.onlyCalculate = onlyCalculate;
        calculateForMes();
    }

    public Row(Context context, int month) {
        super(context);
        this.context = context;
        this.month = month;
        createViews();
        setSettingsToViews();
        calculateForMes();
        setViewsToRow();
        setDataToRow();
    }


    private void setDataToRow() {
        viewMonth.setText(String.valueOf(month));
        if (month == MainActivity.srokKredita) {
            viewOstatokKredita.setText("0");
        } else {
            String formattedOstatokKredita = String.format("%,d", ((int) ostatokKredita));
            viewOstatokKredita.setText(formattedOstatokKredita);
        }
        String formattedProcenti = String.format("%,d", ((int) procenti));
        viewProcenti.setText(formattedProcenti);
        String formattedPlatez = String.format("%,d", ((int) osnDolg));
        viewPlatez.setText(formattedPlatez);
        String formattedVsego = String.format("%,d", ((int) vsego));
        viewVsego.setText(formattedVsego);
    }

    private void createViews() {
        viewMonth = new TextView(context);
        viewVsego = new TextView(context);
        viewOstatokKredita = new TextView(context);
        viewPlatez = new TextView(context);
        viewProcenti = new TextView(context);
    }

    private void setSettingsToViews() {
        setSettingsToView(viewOstatokKredita);
        setSettingsToView(viewPlatez);
        setSettingsToView(viewProcenti);
        setSettingsToView(viewVsego);
        setSettingsToView(viewMonth);
        setBackgroundResource(R.color.colorHighlight);
        setPadding(0, 10, 0, 0);
        if (month == MainActivity.srokKredita) setPadding(0, 10, 0, 10);
    }

    private void setSettingsToView(TextView view) {
        view.setTextSize(14);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER_HORIZONTAL);
        if (month % 60 == 0 | month == 1) {
            view.setTextColor(Color.BLUE);
        }
        if (view == viewProcenti) {
            view.setBackgroundResource(R.drawable.rectangle);
            view.setTextColor(Color.RED);
        }
    }

    public void setViewsToRow() {
        this.addView(viewMonth);
        this.addView(viewOstatokKredita);
        this.addView(viewPlatez);
        this.addView(viewProcenti);
        this.addView(viewVsego);
    }

    public void calculateForMes() {
        procenti = (MainActivity.summaKredita * MainActivity.koefProcStavki);
        osnDolg = (MainActivity.mesPlatez - procenti);
        ostatokKredita = MainActivity.summaKredita - osnDolg;
        vsego = procenti + osnDolg;
        MainActivity.summaKredita = ostatokKredita;
    }
}
