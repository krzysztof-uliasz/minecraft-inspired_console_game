package com.EntityTypes;

public class Creeper extends Entity {
    public static final String mapName = "\u001B[32m" + "creepr" + "\u001B[0m";
    public static final String fullName = "\u001B[32m" + "Creeper" + "\u001B[0m";
    private int mappositionX = 3;
    private int positionY = 3;
    public Creeper(int x, int y) {
        super(mapName, fullName, x, y);
    }

    @Override
    public void hit(Entity e) {

    }

    @Override
    public void receiveDamage(int damage) {

    }

    @Override
    public void kill() {

    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public int getHealthStatus() {
        return 0;
    }

    public void setStatus(int[] mapCoordinates) {
    }
}
