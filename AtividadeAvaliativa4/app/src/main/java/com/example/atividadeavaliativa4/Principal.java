package com.example.atividadeavaliativa4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Principal extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        Button btnFormato = (Button) findViewById(R.id.btnFormato);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker hora = (TimePicker) findViewById(R.id.pckHora);
                DatePicker data = (DatePicker) findViewById(R.id.pckData);
                EditText campo = (EditText) findViewById(R.id.campo);

                Calendar calNasc = Calendar.getInstance();

                calNasc.set(Calendar.YEAR, data.getYear());
                calNasc.set(Calendar.MONDAY, data.getMonth());
                calNasc.set(Calendar.DAY_OF_MONTH, data.getDayOfMonth());

                calNasc.set(Calendar.HOUR_OF_DAY, hora.getCurrentHour());
                calNasc.set(Calendar.MINUTE, hora.getCurrentMinute());

                Calendar calHoje = Calendar.getInstance();
                calHoje.setTime(new Date());

                int tempo = calHoje.compareTo(calNasc);

                if (tempo > 0) {
                    calcularData(calNasc, calHoje, campo.getText().toString());
                } else {
                    Toast.makeText(getBaseContext(), "Nascimento maior que data atual", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnFormato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Alternativa.class);
                startActivity(i);
            }
        });
    }

    public void calcularData (Calendar nascimento, Calendar agora, String nome) {
        int ano = agora.get(Calendar.YEAR);
        int mes = agora.get(Calendar.MONTH);
        int dia = agora.get(Calendar.DAY_OF_MONTH);

        int anoNasc = nascimento.get(Calendar.YEAR);
        int mesNasc = nascimento.get(Calendar.MONTH);
        int diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);

        int idade = ano - anoNasc;

        if(mes < mesNasc) {
            idade--;
        } else if (mes == mesNasc) {
            if (dia < diaNasc) {
                idade--;
            }
        }

        String mensagem = nome + " tem " + idade + " anos de idade";

        Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_SHORT).show();
    }
}
