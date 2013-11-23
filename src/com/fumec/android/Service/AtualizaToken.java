package com.fumec.android.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AtualizaToken extends Service {
//	private MainActivity activity;
//	private ProgressBar mProgress;
//    private int mProgressStatus = 0;
//    private Handler mHandler = new Handler();
	
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("SERVICE", "onStartCommand");
		
//		mProgress = (ProgressBar) findViewById(R.id.progressBar);
//		
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
		
	    return Service.START_NOT_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
		  Log.d("SERVICE", "onBind");
		  return null;
	  }
}
