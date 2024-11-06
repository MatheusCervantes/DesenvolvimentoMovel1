package com.example.atividadeavaliativa3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;


public class Principal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        String[] animal = {"Cachorro", "Gato"};

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(
          this, android.R.layout.simple_dropdown_item_1line, animal
        );

        Spinner sp = findViewById(R.id.fonte);
        sp.setAdapter(adapterSpinner);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                if (position == 0) {  // Gato está na posição 1 {
                    notificacao("Cachorro");
                }
                if (position == 1) {  // Gato está na posição 1 {
                    notificacao("Gato");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }

    public void notificacao(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
