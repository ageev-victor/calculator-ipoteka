package ru.ageev_victor.calculator_ipoteka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

public class TableActivity extends AppCompatActivity {

    TableLayout tablePlatezei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_platez);
        tablePlatezei = (TableLayout) findViewById(R.id.tablePlatezei);
    }

    @Override
    protected void onResume() {
        super.onResume();
            for (int i = 1; i != MainActivity.srokKredita; i++) {
                if(i % 12 == 0) {
                    Row row = new Row(getApplicationContext(), i / 12 + 1);
                    tablePlatezei.addView(row);
                }else {
                    if(i == 1){
                        tablePlatezei.addView(new Row(getApplicationContext(), 1));
                        continue;
                    }
                    new Row(getApplicationContext(), i);
                }
            }
            tablePlatezei.setShrinkAllColumns(true);
            tablePlatezei.setStretchAllColumns(true);
    }
}


