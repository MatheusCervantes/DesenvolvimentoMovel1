package com.example.atividadeavaliativa1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Btn3 extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_btn3);

        EditText cidade = (EditText) findViewById(R.id.cidade);
        EditText ensino = (EditText) findViewById(R.id.ensino);

        cidade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    notificacao("Foco recebido !");
                } else {
                    notificacao("Foco perdido !");
                }
            }
        });

        ensino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    notificacao("Foco recebido !");
                } else {
                    notificacao("Foco perdido !");
                }
            }
        });
    }

    public void notificacao(String texto) {
        Toast.makeText(Btn3.this, texto, Toast.LENGTH_SHORT).show();
    }
}
