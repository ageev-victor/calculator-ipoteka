package ru.ageev_victor.calculator_ipoteka;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

public class Row extends TableRow {

    int month;
    static int ostatokKredita;
    static int osnDolg;
    static int procenti;
    static int vsego;

    TextView viewMonth;
    TextView viewOstatokKredita;
    TextView viewPlatez;
    TextView viewProcenti;
    TextView viewVsego;
    Context context;

    public Row(Context context, int month) {
        super(context);
        this.context = context;
        this.month = month;
        createViews();
        calculateForMes();
        setViewsToRow();
        setDataToRow();
    }

    private void setDataToRow() {
        viewMonth.setText(String.valueOf(month));
        viewOstatokKredita.setText(String.valueOf(ostatokKredita));
        viewProcenti.setText(String.valueOf(procenti));
        viewPlatez.setText(String.valueOf(osnDolg));
        viewVsego.setText(String.valueOf(vsego));
    }

    private void createViews() {
        viewMonth = new TextView(context);
        viewMonth.setTextColor(Color.BLACK);
        viewMonth.setGravity(Gravity.CENTER_HORIZONTAL);
        viewOstatokKredita = new TextView(context);
        viewOstatokKredita.setGravity(Gravity.CENTER_HORIZONTAL);
        viewOstatokKredita.setTextColor(Color.BLACK);
        viewPlatez = new TextView(context);
        viewPlatez.setGravity(Gravity.CENTER_HORIZONTAL);
        viewPlatez.setTextColor(Color.BLACK);
        viewProcenti = new TextView(context);
        viewProcenti.setGravity(Gravity.CENTER_HORIZONTAL);
        viewProcenti.setTextColor(Color.BLACK);
        viewVsego = new TextView(context);
        viewVsego.setGravity(Gravity.CENTER_HORIZONTAL);
        viewVsego.setTextColor(Color.BLACK);
    }

    public void setViewsToRow() {
        this.addView(viewMonth);
        this.addView(viewOstatokKredita);
        this.addView(viewPlatez);
        this.addView(viewProcenti);
        this.addView(viewVsego);
    }

    public void calculateForMes() {
        procenti = (int) (MainActivity.summaKredita * MainActivity.koefProcStavki);
        osnDolg = (int) (MainActivity.mesPlatez - procenti);
        ostatokKredita = MainActivity.summaKredita - osnDolg;
        vsego = procenti + osnDolg;
        MainActivity.summaKredita = ostatokKredita;
    }
}
