package com.ghs.mazegame.game.interfaces;

import com.ghs.mazegame.engine.display.Camera;
import com.ghs.mazegame.game.map.Map;

public interface Panel {

    public void update();

    public void render();

    public int checkState();

    public void setActive();

    public void setActive(Map map);

    public Map getMap();
}