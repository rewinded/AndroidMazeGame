package com.ghs.mazegame;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLES32;
import android.opengl.GLSurfaceView;
import android.os.Debug;
import android.util.Log;

import com.ghs.mazegame.engine.components.Shader;
import com.ghs.mazegame.engine.components.Texture;
import com.ghs.mazegame.engine.components.VAO;
import com.ghs.mazegame.engine.display.Camera;
import com.ghs.mazegame.objects.UIImage;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderer implements GLSurfaceView.Renderer {

    public static int cameraWidth = 384;
    public static int cameraHeight = 218;
    public static int scale = 16;

    private Resources resources;

    private Shader shader;

    private VAO vao;

    private Camera camera;

    private Texture texture;

    UIImage testImage;

    GLSurfaceView surface;

    public Renderer(Resources resources) {
        this.resources = resources;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        shader = new Shader(resources, R.raw.vert, R.raw.frag);

        texture = new Texture(resources, R.drawable.santic_claws);

        camera = new Camera(cameraWidth, cameraHeight);

        testImage = new UIImage(camera, texture, shader, 0, 0, 75, 75);

        GLES20.glClearColor(0f, 0f, 0f, 1f);
    }

    public void onSurfaceChanged(GL10 gl, int wid, int hig) {
        GLES20.glViewport(0,0,wid,hig);
    }


    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        testImage.render();
        //testImage.translate(1, 0);
    }
}