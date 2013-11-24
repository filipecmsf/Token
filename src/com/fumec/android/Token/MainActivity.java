package com.fumec.android.Token;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fumec.android.Async.TokenAsync;
import com.fumec.android.Service.AtualizaToken;
import com.fumec.android.Util.TOTP;

public class MainActivity extends Activity {

	private ProgressBar mProgress;
	private TextView tvToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		tvToken = (TextView) findViewById(R.id.tv_token);
		
		Intent i= new Intent(this, AtualizaToken.class);
		
		this.startService(i); 
		
		TokenAsync tokenAsync = new TokenAsync(this);
		tokenAsync.execute();
		
		TOTP.setTokens();
		Integer token = TOTP.getTokenAtual();
		Log.d("TOKEN", "TOKEN ATUAL = " + token.toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void atualizaProgress(Integer valor){
		mProgress.setProgress(valor);
	}
	
	public void atualizaToken(String valor){
		tvToken.setText(valor);
	}

}
