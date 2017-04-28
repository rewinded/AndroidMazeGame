package com.ghs.mazegame.game.panels;

import static com.ghs.mazegame.game.Renderer.*;

import com.ghs.mazegame.R;
import com.ghs.mazegame.engine.components.Shader;
import com.ghs.mazegame.engine.components.Texture;
import com.ghs.mazegame.engine.display.Camera;
import com.ghs.mazegame.engine.math.Vector3f;
import com.ghs.mazegame.game.objects.DPad;
import com.ghs.mazegame.game.objects.Map;
import com.ghs.mazegame.game.objects.Player;

public class TestPlayPanel implements Panel {

    private Map map;
    private Player player;
    private DPad dpad;

    private Camera camera;

    public TestPlayPanel(Camera camera) {
        this.camera = camera;
        dpad = new DPad(camera, SCALE * 0.75f, cameraHeight - SCALE * 2.75f, SCALE * 2 + 1, SCALE * 2 + 1);
        player = new Player(camera, new Texture(R.drawable.samby), new Shader(R.raw.defaultvs, R.raw.defaultfs), SCALE, SCALE, SCALE, SCALE);
    }

    public void update() {
        dpad.update();
        updatePlayer();
        updateCamera();
    }

    public void render() {
        map.render();
        player.render();
        dpad.render();
    }

    private void updatePlayer() {
        float speed = SCALE * 3;
        player.translate(dpad.getDir().mul(speed, new Vector3f()).mul(time));
        player.update();
        map.checkPlayerCollision(player);
    }

    private void updateCamera() {
        int width = map.getWidth();
        int height = map.getHeight();
        //Sets the camera position putting the player in the center
        camera.setPosition(player.getX() + (player.getWidth() - camera.getWidth()) / 2, player.getY() + (player.getHeight() - camera.getHeight()) / 2, 0);
        //Checks that the camera is not going past the edge of the map and adjusting the camera position if neccessary
        if(camera.getX() < 0)
            camera.setPosition(0, camera.getY(), 0);
        else if(camera.getX() > width * SCALE - camera.getWidth())
            camera.setPosition(width * SCALE - camera.getWidth(), camera.getY(), 0);
        if(camera.getY() < 0)
            camera.setPosition(camera.getX(), 0, 0);
        else if(camera.getY() > height * SCALE - camera.getHeight())
            camera.setPosition(camera.getX(), height * SCALE - camera.getHeight(), 0);
    }

    public void setActive(Map map) {
        this.map = map;
    }
}