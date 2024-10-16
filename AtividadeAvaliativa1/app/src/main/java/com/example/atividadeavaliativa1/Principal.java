package com.example.atividadeavaliativa1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.view.MenuItem;

public class Principal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        registerForContextMenu(btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Principal.this, "Você apertou curtamente o Botão 1.", Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(Principal.this, "Você apertou longamente o Botão 1", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLayout2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, Btn3.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opc1) {
            Toast.makeText(Principal.this, "OP1 selecionada!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opc2) {
            Toast.makeText(Principal.this, "OP2 selecionada!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opc3) {
            Toast.makeText(Principal.this, "OP3 selecionada!", Toast.LENGTH_SHORT).show();
            mostrarLayout2();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void mostrarLayout2() {
        Intent i = new Intent(Principal.this, Btn2.class);
        startActivity(i);
    }
}
