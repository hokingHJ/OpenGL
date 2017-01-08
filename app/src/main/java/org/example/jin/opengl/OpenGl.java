package org.example.jin.opengl;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class OpenGl extends Activity {
	private static final String TAG = "OpenGL";
	private GLView view;
	private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Initialize GLView()");
		this.view = new GLView(this);
		Log.d(TAG, "Ending the Initialize GLView()");
		setContentView(view);
		Log.d(TAG, "Ending ... setContentView()");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mp!=null){
			mp.release();
		}
		this.mp = MediaPlayer.create(this, R.raw.kanong);
		this.mp.setLooping(true);
		this.view.onResume();
		this.mp.start();
		Log.d(TAG, "onResume() is invoked");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.mp.stop();
		this.view.onPause();
		Log.d(TAG, "onPause is invoked");
	}
	
}
