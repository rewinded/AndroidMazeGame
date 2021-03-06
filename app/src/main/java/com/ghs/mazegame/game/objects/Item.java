package com.ghs.mazegame.game.objects;

import android.renderscript.Matrix4f;

import com.ghs.mazegame.engine.components.Shader;
import com.ghs.mazegame.engine.components.Texture;
import com.ghs.mazegame.engine.components.VAO;
import com.ghs.mazegame.engine.display.Camera;
import com.ghs.mazegame.game.interfaces.GameObject;

import static com.ghs.mazegame.game.Main.defaultShader;

public class Item implements GameObject {

    private float x = 0, y = 0;
    private float width = 0, height = 0;
    private Camera camera;
    private VAO vao;
    private Texture texture;
    private Shader shader;
    private String name;

    public Item(String name, Camera camera, Texture texture, Shader shader, float x, float y, float width, float height, float depth) {
        this.name = name;
        this.texture = texture;
        this.shader = shader;
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        float[] vertices = new float[] {
                0.0f,  0.0f,   depth, //TOP LEFT
                0.0f,  height, depth, //BOTTOM LEFT
                width, height, depth, //BOTTOM RIGHT
                width, 0.0f,   depth  //TOP RIGHT
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

    public Item(Camera camera, Texture texture, float x, float y, float width, float height, float depth) {
        this.texture = texture;
        shader = defaultShader;
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        float[] vertices = new float[] {
                0.0f,  0.0f,   depth, //TOP LEFT
                0.0f,  height, depth, //BOTTOM LEFT
                width, height, depth, //BOTTOM RIGHT
                width, 0.0f,   depth  //TOP RIGHT
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

    public void update() {}

    public void render() {
        texture.bind();
        shader.enable();
        Matrix4f model = new Matrix4f();
        model.loadTranslate(x, y, 0);
        shader.setUniformMat4f("model", model);
        shader.setUniformMat4f("projection", camera.getProjection());
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

    public String getName() {
        return name;
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