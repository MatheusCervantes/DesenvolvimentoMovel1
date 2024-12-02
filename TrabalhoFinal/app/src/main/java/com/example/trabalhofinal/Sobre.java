package com.example.trabalhofinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Sobre extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sobre);

        Button btnVoltar = findViewById(R.id.btnVoltarSobre);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sobre.this, Principal.class);
                startActivity(intent);
            }
        });
    }
}
