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

import java.sql.Time;

public class Alternativa extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alternativo);

        TextView texto = (TextView)findViewById(R.id.txtPrompt);
        Button btnData = (Button) findViewById(R.id.btnData);

        final RadioButton escolhaHora = (RadioButton) findViewById(R.id.escolhaHora);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaHora.isChecked())
                    showDialog(0);
                else
                    showDialog(1);
            }
        });

        @Override
        protected onCreateDialog(int id) {
            ouvidorTempo = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet (TimePicker view, int hourOfDay, int minute) {
                    texto.setText("Escolha uma opção abaixo: " + hourOfDay + ":" + minute);
                }
            };

            ouvidorData = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet (DatePicker view, int year, int month, int dayOfMonth) {
                    texto.setText("Escolha uma opção abaixo: " + dayOfMonth + "/" + month + "/" + year);
                }
            };

            switch (id) {
                case 0: TimePickerDialog tempo;
                    tempo = new TimePickerDialog(this, ouvidorTempo, 12, 00, false);
                    return tempo;
                case 1: DatePickerDialog data;
                    data = new DatePickerDialog(this, ouvidorData, 2024, 10, 10);
                    return data;
            }
            return null;
        }
    }
}
