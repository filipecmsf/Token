package com.fumec.android.Async;

import com.fumec.android.Token.MainActivity;

import android.os.AsyncTask;

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
	              while (timer < 240) {
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
