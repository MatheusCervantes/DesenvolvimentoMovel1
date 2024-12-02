package com.example.trabalhofinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Simulacao extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_simulacao);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimpar = findViewById(R.id.btnLimpar);
        Button btnVoltar = findViewById(R.id.btnVoltar);
        EditText edtValorCombustivel = findViewById(R.id.edtValorCombustivel);
        EditText edtDistancia = findViewById(R.id.edtDistancia);
        EditText edtConsumo = findViewById(R.id.edtConsumo);
        EditText edtModeloVeiculo = findViewById(R.id.edtModeloVeiculo);
        TextView txtValorGasto = findViewById(R.id.txtValorGasto);
        TextView txtNumeroLitros = findViewById(R.id.txtNumeroLitros);
        Spinner spinnerTipoCombustivel = findViewById(R.id.spinnerTipoCombustivel);
        registerForContextMenu(btnCalcular);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulacao.this, Principal.class);
                startActivity(intent);
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorCombustivel = Double.parseDouble(edtValorCombustivel.getText().toString());
                double distancia = Double.parseDouble(edtDistancia.getText().toString());
                double consumo = Double.parseDouble(edtConsumo.getText().toString());

                double valorGasto = calcularValorGasto(valorCombustivel, distancia, consumo);
                double numeroLitros = calcularNumeroLitros(valorGasto, valorCombustivel);

                txtValorGasto.setText(String.format("R$ %.2f", valorGasto));
                txtNumeroLitros.setText(String.format("%.2f litros", numeroLitros));
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValorCombustivel.setText("");
                edtDistancia.setText("");
                edtConsumo.setText("");
                edtModeloVeiculo.setText("");
                txtValorGasto.setText("");
                txtNumeroLitros.setText("");
                spinnerTipoCombustivel.setSelection(0);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Gasolina", "Álcool", "Diesel"});
        spinnerTipoCombustivel.setAdapter(adapter);

        spinnerTipoCombustivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position == 0) {
                    notificacao("Gasolina");
                }
                if (position == 1) {
                    notificacao("Álcool");
                }
                if (position == 2) {
                    notificacao("Diesel");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edtValorCombustivel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    notificacao("Valor combustível recebeu foco");
                } else {
                    notificacao("Valor combustível perdeu foco");
                }
            }
        });
    }

    private double calcularValorGasto(double valorCombustivel, double distancia, double consumo) {
        return valorCombustivel * (distancia / consumo);
    }

    private double calcularNumeroLitros(double valorGasto, double valorCombustivel) {
        return valorGasto / valorCombustivel;
    }

    public void notificacao(String texto) {
        Snackbar.make(findViewById(android.R.id.content), texto, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculos, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        EditText edtValorCombustivel = findViewById(R.id.edtValorCombustivel);
        EditText edtDistancia = findViewById(R.id.edtDistancia);
        EditText edtConsumo = findViewById(R.id.edtConsumo);
        EditText edtModeloVeiculo = findViewById(R.id.edtModeloVeiculo);
        TextView txtValorGasto = findViewById(R.id.txtValorGasto);
        TextView txtNumeroLitros = findViewById(R.id.txtNumeroLitros);
        Spinner spinnerTipoCombustivel = findViewById(R.id.spinnerTipoCombustivel);

        if (id == R.id.menu_calculo1) {
            notificacao(
                    "Cálculo do valor gasto: " +
                            edtValorCombustivel.getText().toString() + " * (" + edtDistancia.getText().toString() + " / " + edtConsumo.getText().toString() + ") = R$ " +
                            txtValorGasto.getText().toString());
            return true;
        } else if (id == R.id.menu_calculo2) {
            notificacao(
                    "Cálculo da quantidade de combustível gasta: " + edtDistancia.getText().toString() + " / " + edtConsumo.getText().toString() + " = " +
                            txtNumeroLitros.getText().toString()); // Mostra a conta com os valores originais
            return true;
        }
        else if (id == R.id.menu_calculo3) {
            Db db = new Db(this);
            db.inserirDados(
                    edtValorCombustivel.getText().toString(),
                    edtDistancia.getText().toString(),
                    edtConsumo.getText().toString(),
                    edtModeloVeiculo.getText().toString(),
                    txtValorGasto.getText().toString(),
                    txtNumeroLitros.getText().toString(),
                    spinnerTipoCombustivel.getSelectedItem().toString()
            );
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}

