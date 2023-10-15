package com.example.moeda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// CLASSE QUE LIDA COM AS SOLICITAÇÕES HTTP
public class HttpHandler {

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            // CRIA OBJETO A PARTIR DA STRING URL
            URL url = new URL(reqUrl);

            // ABRE A CONEXAO URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // DEFINE O METODO DE SOLICITAÇÃO COM GET
            conn.setRequestMethod("GET");

            // CONEXAO
            InputStream is = conn.getInputStream();

            // CONERTE ENTRADA EM STRING
            response = convertStreamToString(is);
        } catch (IOException e) {
            // DEPURAÇÃO
            e.printStackTrace();
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        // BUFFEREDREADER PARA ENTRADA
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // CRIA  STRINGBUILDER PARA ARMAZENAR CONTEUDO CONVERTIDO
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
