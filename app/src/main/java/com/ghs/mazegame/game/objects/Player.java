package com.ghs.mazegame.game.objects;

import android.renderscript.Matrix4f;

import com.ghs.mazegame.engine.components.Shader;
import com.ghs.mazegame.engine.components.Texture;
import com.ghs.mazegame.engine.components.VAO;
import com.ghs.mazegame.engine.display.Camera;
import com.ghs.mazegame.engine.math.Vector3f;
import com.ghs.mazegame.engine.utils.Hitbox;

public class Player {

    private float x = 0, y = 0, width = 0, height = 0;
    private float rightBound = 0, bottomBound = 0;
    private Hitbox hitbox;
    private Camera camera;
    private VAO vao;
    private Texture texture;
    private Shader shader;

    public Player(Camera camera, Texture texture, Shader shader, float x, float y, float width, float height, float rightBound, float bottomBound) {
        this.texture = texture;
        this.shader = shader;
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rightBound = rightBound;
        this.bottomBound = bottomBound;
        this.hitbox = new Hitbox(x, y, width, height);
        float[] vertices = new float[] {
            0.0f,  0.0f,   0.5f, //TOP LEFT
            0.0f,  height, 0.5f, //BOTTOM LEFT
            width, height, 0.5f, //BOTTOM RIGHT
            width, 0.0f,   0.5f  //TOP RIGHT
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

    public void update() {
        if(x < 0)
            x = 0;
        else if(x + width > rightBound)
            x = rightBound - width;
        if(y < 0)
            y = 0;
        else if(y + height > bottomBound)
            y = bottomBound - height;
        hitbox.setPosition(x, y);
    }

    public void render() {
        Matrix4f proj = camera.getProjection();
        proj.translate(x, y, 0);
        shader.setUniformMat4f("projection", proj);
        texture.bind();
        shader.enable();
        vao.render();
        shader.disable();
        texture.unbind();
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        hitbox.setPosition(x, y);
    }

    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
        hitbox.setPosition(this.x, this.y);
    }

    public void translate(Vector3f vector) {
        translate(vector.x, vector.y);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}