package com.example.atividadeavaliativa3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;

public class Principal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        Button btnGerar = (Button) findViewById(R.id.btnGerar);

        String[] nomeanimais = {
                "Cachorro1", "Cachorro2", "Cachorro3", "Cachorro4", "Cachorro5",
                "Gato1", "Gato2", "Gato3", "Gato4", "Gato5"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, nomeanimais
        );
        AutoCompleteTextView nome = (AutoCompleteTextView) findViewById(R.id.nome);

        nome.setAdapter(adapter);


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

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeAnimal = nome.getText().toString();
                int selectedPosition = sp.getSelectedItemPosition();
                Intent i = new Intent(Principal.this, ViewAnimal.class);

                i.putExtra("nome", nomeAnimal);
                i.putExtra("posicao", selectedPosition);

                startActivity(i);
            }
        });

    }

    public void notificacao(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
