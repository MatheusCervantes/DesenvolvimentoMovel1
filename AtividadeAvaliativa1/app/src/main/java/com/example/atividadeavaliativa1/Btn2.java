package com.example.atividadeavaliativa1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Btn2 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_btn2);

        TextView ponto = (TextView) findViewById(R.id.ponto);

        ponto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Btn2.this, Principal.class);
                startActivity(i);
            }
        });
    }

}
