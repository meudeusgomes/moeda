package com.example.moeda;

import android.os.AsyncTask;

// CONVERSAO DA MOEDA EM SEGUNDO PLANO
public class ConvertTask extends AsyncTask<String, Void, String> {

    private OnConversionListener listener;

    public ConvertTask(OnConversionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        // INSTANCIA HTTPHANDLER PARA SOLICITAÇÕES HTTP
        HttpHandler httpHandler = new HttpHandler();

        // CHAMADA DE SERVIÇO NA URL
        return httpHandler.makeServiceCall(params[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        // NOTIFICA CONCLUSAO DA TAREFA
        if (listener != null) {
            listener.onConversionComplete(result);
        }
    }

    public interface OnConversionListener {
        void onConversionComplete(String result);
    }
}
