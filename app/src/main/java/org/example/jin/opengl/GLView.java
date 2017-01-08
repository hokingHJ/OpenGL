package org.example.jin.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
class GLView extends GLSurfaceView{
	private final GLRender renderer;
	private static final String TAG = "GLView";
	public GLView(Context context) {
		super(context);
		Log.d(TAG, "starting .........");
		Log.d(TAG, "Initialize the GLRender()");
		this.renderer = new GLRender(context);
		Log.d(TAG, "setRenderer() is invoked");
		setRenderer(this.renderer);
		Log.d(TAG, "ending ..........");
	}
	
	
}
