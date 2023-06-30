package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Consulta {
    private static final String URL = "https://viacep.com.br/ws/";

    public void pesquisaCEP() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CEP que deseja consultar:");
        String cep = scanner.nextLine();
        // scanner.close();

        if (Cash.enderecoExistente(cep)) {
            Endereco enderecoCache = Cash.obterEndereco(cep);
            exibirInformacoesEndereco(enderecoCache);
        } else {
            try {
                URL url = new URL(URL + cep + "/json/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

                    String logradouro = (String) jsonObject.get("logradouro");
                    String bairro = (String) jsonObject.get("bairro");
                    String cidade = (String) jsonObject.get("localidade");
                    String estado = (String) jsonObject.get("uf");

                    Endereco endereco = new Endereco(logradouro, bairro, cidade, estado);
                    Cash.adicionarEndereco(cep, endereco);

                    exibirInformacoesEndereco(endereco);
                } else {
                    System.out.println("Erro ao fazer a consulta. Código de resposta: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

public void pesquisaEnd() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite o Estado:");
    String estado = scanner.nextLine();
    System.out.println("Digite a Cidade:");
    String cidade = scanner.nextLine();
    System.out.println("Digite o Logradouro:");
    String logradouro = scanner.nextLine();

    cidade = cidade.replace(" ", "%20");

    String pesquisa = estado + "/" + cidade + "/" + logradouro;

    if (Cash.enderecoExistente(pesquisa)) {
        Endereco enderecoCache = Cash.obterEndereco(pesquisa);
        exibirInformacoesEndereco(enderecoCache);
    } else {
        try {
            URL url = new URL(URL + estado + "/" + cidade + "/" + logradouro + "/json/");
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(response.toString());

                if (!jsonArray.isEmpty()) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                    String cep = (String) jsonObject.get("cep");
                    String logradouroResult = (String) jsonObject.get("logradouro");
                    String bairro = (String) jsonObject.get("bairro");
                    String cidadeResult = (String) jsonObject.get("localidade");
                    String estadoResult = (String) jsonObject.get("uf");

                    Endereco endereco = new Endereco(logradouroResult, bairro, cidadeResult, estadoResult);
                    Cash.adicionarEndereco(cep, endereco);

                    exibirInformacoesEndereco(endereco);
                } else {
                    System.out.println("Nenhum resultado encontrado para o endereço informado.");
                }
            } else {
                System.out.println("Erro ao fazer a consulta. Código de resposta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    private void exibirInformacoesEndereco(Endereco endereco) {
        System.out.println("\nResultado da consulta:");
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Estado: " + endereco.getEstado());
    }
}