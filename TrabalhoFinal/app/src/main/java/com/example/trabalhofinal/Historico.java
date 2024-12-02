package com.example.trabalhofinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.Gravity;
import java.util.List;
import java.util.Map;

public class Historico extends Activity {
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_historico);

        Button btnLimparHistorico = findViewById(R.id.btnLimparHistorico);
        Button btnVoltar = findViewById(R.id.btnVoltar);
        TableLayout tabelaHistorico = findViewById(R.id.tabelaHistorico);

        carregarDadosNoLayout(tabelaHistorico);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Historico.this, Principal.class);
                startActivity(intent);
            }
        });

        btnLimparHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db db = new Db(Historico.this);
                db.limparDados();

                // Limpar linhas da tabela
                int childCount = tabelaHistorico.getChildCount(); // Retorna o número de filhos da tabela (ViewGroups)
                if (childCount > 1) {
                    tabelaHistorico.removeViews(1, childCount - 1);
                }
            }
        });
    }

    public void carregarDadosNoLayout(TableLayout tableLayout) {
        Db db = new Db(this);
        List<Map<String, String>> dados = db.carregarDados();

        // Itera sobre os dados e cria as TableRow
        for (Map<String, String> dataMap : dados) {
            TableRow tableRow = new TableRow(this);
            tableRow.setPadding(5, 5, 5, 5);
            tableRow.setBackgroundColor(this.getResources().getColor(android.R.color.white));

            // Cria os TextViews para cada campo do registro
            TextView tvModeloVeiculo = criarTextView(dataMap.get("modeloVeiculo"));
            TextView tvTipoCombustivel = criarTextView(dataMap.get("tipoCombustivel"));
            TextView tvValorCombustivel = criarTextView(dataMap.get("valorCombustivel"));
            TextView tvDistancia = criarTextView(dataMap.get("distancia"));
            TextView tvConsumo = criarTextView(dataMap.get("consumo"));
            TextView tvLitrosGasto = criarTextView(dataMap.get("litrosGasto"));
            TextView tvValorTotal = criarTextView(dataMap.get("valorGasto"));

            // Adiciona os TextViews à TableRow
            tableRow.addView(tvModeloVeiculo);
            tableRow.addView(tvTipoCombustivel);
            tableRow.addView(tvValorCombustivel);
            tableRow.addView(tvDistancia);
            tableRow.addView(tvConsumo);
            tableRow.addView(tvLitrosGasto);
            tableRow.addView(tvValorTotal);

            // Adiciona a TableRow ao layout
            tableLayout.addView(tableRow);
        }
    }

    // Função auxiliar para criar um TextView com as configurações padrão
    private TextView criarTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(this.getResources().getColor(android.R.color.black));
        textView.setText(text);
        return textView;
    }
}
