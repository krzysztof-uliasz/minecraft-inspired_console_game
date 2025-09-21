package com.EntityTypes;

public abstract class Entity {

    private final String mapName;
    private final String fullName;
    private int mapCoordinatesX;
    private int mapCoordinatesY;
    public Entity(String mapName, String fullName, int positionX, int positionY) {
        this.mapName = mapName;
        this.fullName = fullName;
        this.mapCoordinatesX = positionX;
        this.mapCoordinatesY = positionY;
    }
    public abstract void hit(Entity e);
    public abstract void receiveDamage(int damage);
    public abstract void kill();
    public abstract boolean isOnFire();
    public abstract int getHealthStatus();

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

    public String getMapName() {
        return mapName;
    }

    public String getFullName() {
        return fullName;
    }
}