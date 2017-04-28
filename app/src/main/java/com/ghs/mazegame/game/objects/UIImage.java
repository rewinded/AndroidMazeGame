package com.ghs.mazegame.game.objects;

import android.renderscript.Matrix4f;

import com.ghs.mazegame.engine.components.Shader;
import com.ghs.mazegame.engine.components.Texture;
import com.ghs.mazegame.engine.components.VAO;
import com.ghs.mazegame.engine.display.Camera;

public class UIImage extends UIObject {

    private float x = 0, y = 0;
    private float width = 0, height = 0;
    private Camera camera;
    private VAO vao;
    private Texture texture;
    private Shader shader;

    public UIImage(Camera camera, Texture texture, Shader shader, float x, float y, float width, float height) {
        this.texture = texture;
        this.shader = shader;
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        float[] vertices = new float[] {
            0.0f,  0.0f,   0.0f, //TOP LEFT
            0.0f,  height, 0.0f, //BOTTOM LEFT
            width, height, 0.0f, //BOTTOM RIGHT
            width, 0.0f,   0.0f  //TOP RIGHT
        };
        int[] indices = new int[] {
            0, 1, 3,
            1, 2, 3
        };
        float[] texCoords = new float[] {
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };
        vao = new VAO(vertices, indices, texCoords);
    }

    public void render() {
        Matrix4f proj = camera.getUnatransformedProjection();
        proj.translate(x, y, 0);
        shader.setUniformMat4f("projection", proj);
        texture.bind();
        shader.enable();
        vao.render();
        shader.disable();
        texture.unbind();
    }

    public boolean contains(float x, float y) {
        if ((x >= this.x && x < this.x + this.width) && (y >= this.y && y < this.y + this.height)) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}