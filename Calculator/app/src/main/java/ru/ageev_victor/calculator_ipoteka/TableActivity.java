package ru.ageev_victor.calculator_ipoteka;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

public class TableActivity extends AppCompatActivity {

    TableLayout tablePlatezei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_table_platez);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
        addRowsToTable();
        setTableParams();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setTableParams() {
        tablePlatezei.setShrinkAllColumns(true);
        tablePlatezei.setStretchAllColumns(true);
    }

    private void addRowsToTable() {
        int FIRST_MONTH = 1;
        for (int i = 1; i != MainActivity.srokKredita + 1; i++) {
            if (i == FIRST_MONTH) {
                tablePlatezei.addView(new Row(getApplicationContext(), FIRST_MONTH));
                continue;
            }
            if (i % 12 == 0) {
                tablePlatezei.addView(new Row(getApplicationContext(), i));
                continue;
            }
            new Row(getApplicationContext(), i, true);
        }
        MainActivity.mProgressDialog.cancel();
    }
}


