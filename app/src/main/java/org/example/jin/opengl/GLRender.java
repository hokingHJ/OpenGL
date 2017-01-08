package org.example.jin.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

public class GLRender implements GLSurfaceView.Renderer {
	
	private static final String TAG = "GLRender";
	private static final String FPS = "GLFPS...";
	private long startTime;
	private long fpsStartTime;
	private long numFrames;
	private final GLCube cube;
	
	public GLRender(Context context) {
		this.cube = new GLCube(context);
		Log.d(TAG, "ending Initialize ..........");
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		float lightAmbient[] = new float[]{0.2f,0.2f,0.2f,1};
		float lightDiffuse[] = new float[]{1,1,1,1};
		float[] lightPos = new float[]{1,1,1,1};
		float matAmbient[] = new float[]{1,1,1,1};
		float matDiffuse[] = new float[]{1,1,1,1};
		boolean SEE_THRU = true;
		this.startTime = System.currentTimeMillis();
		this.fpsStartTime = this.startTime;
		this.numFrames = 0;
		if(SEE_THRU){
			gl.glDisable(GL10.GL_DEPTH_TEST);
			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		}
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient,0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glDisable(GL10.GL_DITHER);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		Log.d(TAG, "onSurfaceCreated() is ending .....");
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio = (float)width/height;
		GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);
		Log.d(TAG, "onsurfaceChanged() is ending ......");
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -3.0f);
		long elapsed = System.currentTimeMillis()-this.startTime;
		gl.glRotatef(elapsed*(30f/1000f), 0, 1, 0);
		gl.glRotatef(elapsed*(15f/1000f), 1, 0, 0);
		cube.draw(gl);
		this.numFrames++;
		long fpsElapsed = System.currentTimeMillis()-this.fpsStartTime;
		if(fpsElapsed>5*1000){
			float fps = (numFrames*1000f)/fpsElapsed;
			Log.d(FPS, "Frame per second :"+fps+"("+numFrames+"frames in "+fpsElapsed+"ms)");
			this.fpsStartTime = System.currentTimeMillis();
			this.numFrames = 0;
		}
		Log.d(TAG, "onDrawFrame() is ending .......");
	}

}
