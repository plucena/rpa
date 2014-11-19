package com.sistemarpa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import com.sistemarpa.Cadastro;
import com.sistemarpa.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Cadastro extends Activity {
	
	protected final Context context = this;
	
	Button btcadastrar, btvoltar2;
	TextView tRA;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro);
		
		btvoltar2 = (Button)
				findViewById(R.id.btvoltar2);
		
		btcadastrar = (Button)
				findViewById(R.id.btcadastrar);
		
		tRA = (TextView) findViewById(R.id.tRA2);
		
		btvoltar2.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent TrocaTela = new
				Intent(Cadastro.this,QrCode.class);
				Cadastro.this.startActivity(TrocaTela);
				Cadastro.this.finish();
			}
		});
		
		btcadastrar.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				String raLido = tRA.getText().toString();
		        String Turl = "http://192.241.171.69:8080/alunonfc/webservice/presenca/?id=" + raLido;
		        String erro = null;
		        String mensagem = "TESTE";
				
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
			    }
		        catch(Exception e) {
		        	mensagem = "ERRO:" + e;
					e.printStackTrace();
				}
		        
					// set title
					alertDialogBuilder.setTitle("Aviso");
		 			
						mensagem = "Sua presença foi registrada:" +  raLido;
						
					// set dialog message
					alertDialogBuilder
				
					.setMessage(mensagem)
						.setCancelable(false)
						.setNegativeButton("OK",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
					}
			
			
				});

				
				
			}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
