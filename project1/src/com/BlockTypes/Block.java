package com.BlockTypes;

import com.Engine.Inventory;
import com.Engine.Map;
import com.Engine.Thing;
import com.EntityTypes.Player;
import com.Tools.Tool;

public abstract class Block extends Thing {
    public String designatedTool;
    private int mapCoordinatesX;
    private int mapCoordinatesY;
    public Block(String mapName, String inventoryName, int positionX, int positionY) {
        super(mapName, inventoryName);
        this.mapCoordinatesX = positionX;
        this.mapCoordinatesY = positionY;
    }

    public abstract void place(Map map, Thing block, Player player, int slot);

    public abstract void mine(Map map, Tool tool) throws InterruptedException;

    public abstract void collect(Map map, Inventory inventory);

    public int getMapCoordinatesX() {
        return mapCoordinatesX;
    }

    public void setMapCoordinatesX(int mapCoordinatesX) {
        this.mapCoordinatesX = mapCoordinatesX;
    }

    public int getMapCoordinatesY() {
        return mapCoordinatesY;
    }

    public void setMapCoordinatesY(int mapCoordinatesY) {
        this.mapCoordinatesY = mapCoordinatesY;
    }
}
