package com.example;

import java.util.HashMap;
import java.util.Map;

public class Cash {
    private static Map<String, Endereco> cache = new HashMap<>();

    public static void adicionarEndereco(String cep, Endereco endereco) {
        cache.put(cep, endereco);
    }

    public static Endereco obterEndereco(String cep) {
        return cache.get(cep);
    }

    public static boolean enderecoExistente(String cep) {
        return cache.containsKey(cep);
    }
}
