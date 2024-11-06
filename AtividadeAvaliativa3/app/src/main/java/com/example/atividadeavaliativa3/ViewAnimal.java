package com.example.atividadeavaliativa3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


public class ViewAnimal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animal);
        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        TextView nome_tela2 = (TextView) findViewById(R.id.nome_tela2);
        TextView imagem = (TextView) findViewById(R.id.imagem);

        String nomeAnimal = getIntent().getStringExtra("nome");
        int posicao = getIntent().getIntExtra("posicao", -1);
        nome_tela2.setText(nomeAnimal);

        Random random = new Random();

        switch (posicao) {
            case 0:
                int cachorroNum = random.nextInt(5) + 1;
                String nomeCachorro = "cachorro" + cachorroNum;
                int resIdCachorro = getResources().getIdentifier(nomeCachorro, "drawable", getPackageName());
                imagem.setBackgroundResource(resIdCachorro);
                break;
            case 1:
                int gatoNum = random.nextInt(5) + 1;
                String nomeGato = "gato" + gatoNum;
                int resIdGato = getResources().getIdentifier(nomeGato, "drawable", getPackageName());
                imagem.setBackgroundResource(resIdGato);
                break;
            default:

                break;
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewAnimal.this, Principal.class);
                startActivity(i);
            }
        });
    }
}
