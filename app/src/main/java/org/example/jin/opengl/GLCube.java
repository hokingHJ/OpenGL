package org.example.jin.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

class GLCube {
	private final IntBuffer mVertexBuffer;
	private final IntBuffer mTextureBuffer;
	private final Bitmap bmpFront;
	private final Bitmap bmpBack;
	private final Bitmap bmpLeft;
	private final Bitmap bmpRight;
	private final Bitmap bmpTop;
	private final Bitmap bmpBottom;
	private static final String TAG = "GLCube"; 
	public GLCube(Context context) {
		int one = 65536;
		int half = one/2;
		int vertices[] = {
				//front
				-half,-half,half,half,-half,half,-half,half,half,half,half,half,
				//back
				-half,-half,-half,-half,half,-half,half,-half,-half,half,half,-half,
				//left
				-half,-half,half,-half,half,half,-half,-half,-half,-half,half,-half,
				//right
				half,-half,-half,half,half,-half,half,-half,half,half,half,half,
				//top
				-half,half,half,half,half,half,-half,half,-half,half,half,-half,
				//bottom
				-half,-half,half,-half,-half,-half,half,-half,half,half,-half,-half
		};
		int texCoords[] = {
				//front
				0,one,one,one,0,0,one,0,
				//back
				one,one,one,0,0,one,0,0,
				//left
				one,one,one,0,0,one,0,0,
				//right
				one,one,one,0,0,one,0,0,
				//top
				one,0,0,0,one,one,0,one,
				//bottom
				0,0,0,one,one,0,one,one,
		};
		this.bmpFront = BitmapFactory.decodeResource(context.getResources(), R.drawable.ps60);
		this.bmpBack = BitmapFactory.decodeResource(context.getResources(), R.drawable.ps61);
		this.bmpLeft = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty01);
		this.bmpRight = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty02);
		this.bmpTop = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty03);
		this.bmpBottom = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty04);
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length*4);
		vbb.order(ByteOrder.nativeOrder());
		tbb.order(ByteOrder.nativeOrder());
		this.mVertexBuffer = vbb.asIntBuffer();
		this.mTextureBuffer = tbb.asIntBuffer();
		mVertexBuffer.put(vertices);
		mTextureBuffer.put(texCoords);
		mVertexBuffer.position(0);
		mTextureBuffer.position(0);
	}
	public void draw(GL10 gl){
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
		//GLUtils
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpFront, 0);
		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpBack, 0);
		gl.glNormal3f(0, 0, -1);
		gl.glColor4f(1, 1, 1, 1); //
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpLeft, 0);
		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(-1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpRight, 0);
		gl.glNormal3f(1, 0, 0);
		gl.glColor4f(1, 1, 1, 1); //
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpTop, 0);
		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.bmpBottom, 0);
		gl.glColor4f(1, 1, 1, 1); //
		gl.glNormal3f(0, -1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		Log.d(TAG, "draw() is ending .......");
	}
	static void configTexture(GL10 gl,Bitmap bmp){
		
		//bmp.recycle();
	}
	
}
