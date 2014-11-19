package com.sistemarpa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class WebService extends AsyncTask<String, String, String> {

    protected String doInBackground(String ...raLido ){
        //do something in background
    	
    	   String Turl = "http://192.241.171.69:8080/alunonfc/webservice/presenca/?id=" + raLido[0];
	        String erro = null;
	        String mensagem = "";
			
			try {
	        URL url = new URL(Turl);
	        HttpURLConnection http = (HttpURLConnection) url.openConnection();
	        http.setDoOutput(true);
	        http.setRequestMethod("PUT");
	        OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());
	        out.write("Test");
	        out.close();
	        InputStream resposta = http.getInputStream();
	        BufferedReader texto = new BufferedReader(new InputStreamReader(resposta));
	        String respostaWS = texto.readLine();
	        mensagem = "RESPOSTA:" + respostaWS;
	        System.out.println(mensagem);
		    }
	        catch(Exception e) {
	        	mensagem = "ERRO:" + e;
				e.printStackTrace();
				System.out.println("ERROR " + e);
			}
    	  return mensagem;
    }

	
}
