package ru.ageev_victor.calculator_ipoteka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TableActivity extends AppCompatActivity {

    TableLayout tablePlatezei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_platez);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
        for (int i = 1; i < MainActivity.SrokKredita; i++) {
            tablePlatezei.addView(new Row(getApplicationContext(),i));
        }
        //tablePlatezei.setShrinkAllColumns(true);
        tablePlatezei.setStretchAllColumns(true);
    }

}


