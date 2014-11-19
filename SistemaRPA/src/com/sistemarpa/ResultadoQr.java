package com.sistemarpa;

import com.sistemarpa.R;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoQr extends Activity {		
	
	Button  btavancar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultado_qrcode);		
		
		Intent intent = getIntent();
		TextView text = (TextView) findViewById(R.id.text3);
		text.setText(intent.getStringExtra("resultQr"));
		
		btavancar = (Button)
				findViewById(R.id.btavancar);		
	
		
		btavancar.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent TrocaTela = new
				Intent(ResultadoQr.this,Cadastro.class);
				ResultadoQr.this.startActivity(TrocaTela);
				ResultadoQr.this.finish();
			}
		});
	}
	
}