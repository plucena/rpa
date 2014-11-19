package com.sistemarpa;

import com.sistemarpa.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class QrCode extends Activity {
	
	protected static final String ZXING_MARKET = "market://search?q=pname:com.google.zxing.client.android";
	protected static final String ZXING_DIRECT = "https://zxing.googlecode.com/files/BarcodeScanner3.1.apk";
	private static String QrCode;
	
	Button btvoltar1, btQr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qr_code);
		
		btvoltar1 = (Button)
				findViewById(R.id.btvoltar1);
		
		btQr = (Button)
				findViewById(R.id.btQr);
		
		
		
		
		btvoltar1.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent TrocaTela = new
				Intent(QrCode.this,MainActivity.class);
				QrCode.this.startActivity(TrocaTela);
				QrCode.this.finish();
			}
		});
	
	
	
	
	
	//btQr.setOnClickListener(new View.OnClickListener(){
		
		
	//});
}
		
	
	public void setQrCodeText(String param){
		Intent TrocaTela = new Intent(QrCode.this,ResultadoQr.class);
		TrocaTela.putExtra("resultQr", param );
		startActivity(TrocaTela);
		finish();
	}
	

	
	
	public void lerQR(View view){
		// Criou-se um Intent com o caminho aplicativo que ir� ler o QR code
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				
				// Passou o parametro "SCAN_MODE" para ler somente c�digos no formato QR
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
				
				try {
					startActivityForResult(intent, 0);
					
		 
				} catch (ActivityNotFoundException e) {
					mostrarMensagem();
					
				}
	}

	
	
	private void mostrarMensagem() {
		new AlertDialog.Builder(this)
		.setTitle("Instalar barcode scanner?")
		.setMessage("Para escanear QR code voc� precisa instalar o ZXing barcode scanner.")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setPositiveButton("Instalar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ZXING_MARKET));
						try {
							startActivity(intent);
						} catch (ActivityNotFoundException e) { // Se n�o tiver o Play Store
							intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ZXING_DIRECT));
							startActivity(intent);
						}
					}
				})
		.setNegativeButton("Cancelar", null).show();

}
		
	 @Override
		public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
				String qrcode = intent.getStringExtra("SCAN_RESULT");
				
				setQrCodeText(qrcode);			
			} 
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