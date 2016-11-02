package ru.ageev_victor.calculator_ipoteka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TableActivity extends AppCompatActivity {


    public TableLayout tablePlatezei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_platez);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
        for (TableRow row : MainActivity.rows) {
            tablePlatezei.addView(row);
        }
    }
}
