package com.fumec.android.Token;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

import com.fumec.android.Async.TokenAsync;
import com.fumec.android.Service.AtualizaToken;
import com.fumec.android.Util.TOTP;

public class MainActivity extends Activity {

	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		
		Intent i= new Intent(this, AtualizaToken.class);
		
		this.startService(i); 
		
		TokenAsync tokenAsync = new TokenAsync(this);
		tokenAsync.execute();
		
//		new Thread(new Runnable() {
//            public void run() {
//                while (mProgressStatus < 100) {
//                    mProgressStatus = mProgressStatus + 1;
//                    try {
//						Thread.sleep(250);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//                    // Update the progress bar
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            mProgress.setProgress(mProgressStatus);
//                        }
//                    });
//                }
//            }
//        }).start();
		
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

}
