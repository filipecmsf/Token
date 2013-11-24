package com.fumec.android.Async;

import android.os.AsyncTask;
import android.util.Log;

import com.fumec.android.Token.MainActivity;
import com.fumec.android.Util.TOTP;
import com.fumec.android.VO.Timer;

public class TokenAsync extends AsyncTask<Integer ,Integer ,Integer>{

	private MainActivity activity;

		public TokenAsync(MainActivity activity){
			this.activity = activity;
		}
		
	    @Override
	    protected void onPreExecute() {

	    }

		@Override
		protected Integer doInBackground(Integer... params) {
			new Thread(new Runnable() {
				Integer timer = 0;
				public void run() {
				Timer.setVariaveis();
				timer = Integer.valueOf(Timer.getSegundos())*4;
				
	              while (timer < 240) {
	            	  if(timer == 0){
	            		  TOTP.setTokens();
	            		  
	            		  activity.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								activity.atualizaToken(TOTP.getTokenAtual().toString());
								Log.d("AsyncTOKEN","Horario: " + Timer.getData() + " " + Timer.getHorario());
								Log.d("AsyncTOKEN","Token  : " + TOTP.getTokenAtual().toString());
							}
						});
	            		  
	            	  }
	            	  try {
							activity.atualizaProgress(timer);
	            		  	Thread.sleep(250);
                          	if(++timer == 240){
                          		timer = 0;
                          	}
	            	  } catch (InterruptedException e) {
	            		  e.printStackTrace();
	            	  }
	              }
	          }
	      }).start();
			return null;
		  }
}
