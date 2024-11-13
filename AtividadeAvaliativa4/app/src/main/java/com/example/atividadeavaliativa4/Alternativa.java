package com.example.atividadeavaliativa4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class Alternativa extends Activity {

    private TimePickerDialog.OnTimeSetListener ouvidorTempo;
    private DatePickerDialog.OnDateSetListener ouvidorData;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alternativo);

        texto = findViewById(R.id.txtPrompt);
        Button btnData = findViewById(R.id.btnData);
        final RadioButton escolhaHora = findViewById(R.id.escolhaHora);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaHora.isChecked())
                    showDialog(0);
                else
                    showDialog(1);
            }
        });

        ouvidorTempo = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                texto.setText("Escolha uma opção abaixo: " + hourOfDay + ":" + minute);
            }
        };

        ouvidorData = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                texto.setText("Escolha uma opção abaixo: " + dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new TimePickerDialog(this, ouvidorTempo, 12, 0, false);
            case 1:
                return new DatePickerDialog(this, ouvidorData, 2024, 10, 10);
            default:
                return null;
        }
    }
}
