package com.example.trabalhofinal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.snackbar.Snackbar;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        Button btnSimulacao = findViewById(R.id.btnSimulacao);
        Button btnHistorico = findViewById(R.id.btnHistorico);
        Button btnSobre = findViewById(R.id.btnSobre);
        ImageView imgLogo = findViewById(R.id.imgLogo);

        btnSimulacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Simulacao.class);
                startActivity(intent);
            }
        });

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Historico.class);
                startActivity(intent);
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Sobre.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notificacao("Você deu um clique longo na bússola");
                return true;
            }
        });
    }

    public void notificacao(String texto) {
        Snackbar.make(findViewById(android.R.id.content), texto, Snackbar.LENGTH_SHORT).show();
    }
}
