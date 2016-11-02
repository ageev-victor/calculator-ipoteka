package ru.ageev_victor.calculator_ipoteka;


import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;

public class Row extends TableRow {

    int month;
    double ostatokKredita;
    double platez;
    double procenti;
    double vsego;

    TextView viewMonth;
    TextView viewOstatokKredita;
    TextView viewPlatez;
    TextView viewProcenti;
    TextView viewVsego;
    Context context;

    public Row(Context context) {
        super(context);
    }

    public Row(Context context, int month, double ostatokKredita, double platez, double procenti, double vsego) {
        super(context);
        this.month = month;
        this.ostatokKredita = ostatokKredita;
        this.platez = platez;
        this.procenti = procenti;
        this.vsego = vsego;
        this.context = context;
        createViews();
        setViewsToRow();
    }

    private void createViews() {
        viewMonth = new TextView(context);
        viewOstatokKredita = new TextView(context);
        viewPlatez = new TextView(context);
        viewProcenti = new TextView(context);
        viewVsego = new TextView(context);
    }

    public void setViewsToRow() {
        this.addView(viewMonth);
        this.addView(viewOstatokKredita);
        this.addView(viewPlatez);
        this.addView(viewProcenti);
        this.addView(viewVsego);
    }


}
