package com.example.trabalhofinal;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {

    private Context context;

    // Construtor da classe, recebe o contexto da aplicação
    public Db(Context context) {
        this.context = context;
    }

    public void inserirDados(String valorCombustivel, String distancia, String consumo, String modeloVeiculo, String valorGasto, String litrosGasto, String tipoCombustivel) {
        String data = valorCombustivel + ";" + distancia + ";" + consumo + ";" + modeloVeiculo + ";" + valorGasto + ";" + litrosGasto + ";" + tipoCombustivel + "\n";
        try {
            FileOutputStream fos = context.openFileOutput("dados.txt", Context.MODE_APPEND); //Modo append para não sobrescrever os dados
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega todos os registros de dados do arquivo
    public List<Map<String, String>> carregarDados() {
        // Lista para armazenar os registros de dados
        List<Map<String, String>> dados = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput("dados.txt");
            int n;
            StringBuilder sb = new StringBuilder();
            while ((n = fis.read()) != -1) {
                sb.append((char) n);
            }
            // Converte o conteúdo do arquivo para uma string
            String fileContent = sb.toString();
            // Divide a string em linhas usando o caractere de nova linha como delimitador
            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                // Divide a linha em valores usando o ponto e vírgula como delimitador
                String[] values = line.split(";");
                if (values.length == 7) {
                    Map<String, String> dataMap = new HashMap<>();
                    // Adiciona os valores ao mapa com seus respectivos nomes de campo
                    dataMap.put("valorCombustivel", values[0]);
                    dataMap.put("distancia", values[1]);
                    dataMap.put("consumo", values[2]);
                    dataMap.put("modeloVeiculo", values[3]);
                    dataMap.put("valorGasto", values[4]);
                    dataMap.put("litrosGasto", values[5]);
                    dataMap.put("tipoCombustivel", values[6]);
                    dados.add(dataMap);
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }

    public void limparDados() {
        try {
            // Abre o arquivo "dados.txt" em modo de escrita (substituindo o conteúdo)
            FileOutputStream fos = context.openFileOutput("dados.txt", Context.MODE_PRIVATE);
            // Fecha o arquivo (isso efetivamente limpa o conteúdo)
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}