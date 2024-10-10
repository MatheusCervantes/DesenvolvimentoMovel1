package com.example.atividadeavaliativa2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        LinearLayout layout_principal = (LinearLayout) findViewById(R.id.layout_principal);
        EditText nome = (EditText) findViewById(R.id.nome);
        EditText regiao = (EditText) findViewById(R.id.regiao);
        ImageButton btnenviar = (ImageButton) findViewById(R.id.btnenviar);

        layout_principal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                notificacao("Clique em um dos componentes !");
                return true;
            }
        });

        nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    notificacao("Nome recebeu foco");
                } else {
                    notificacao("Nome perdeu foco");
                }
            }
        });

        regiao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    notificacao("Região recebeu foco");
                } else {
                    notificacao("Região perdeu foco");
                }
            }
        });

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(Principal.this);
                textView.setText("Dados inseridos com sucesso!");
                setContentView(textView);
            }
        });

    }

    public void notificacao(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
