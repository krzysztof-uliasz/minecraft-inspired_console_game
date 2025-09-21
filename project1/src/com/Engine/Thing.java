package com.Engine;

public abstract class Thing {
    private final String mapName;
    private final String inventoryName;

    public Thing(String mapName, String inventoryName) {
        this.mapName = mapName;
        this.inventoryName = inventoryName;
    }

    public String getMapName() {
        return mapName;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public abstract int count();
}
