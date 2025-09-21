package com.EntityTypes;

import com.Engine.Inventory;

public class Player extends Entity {
    public static final String mapName = "\u001B[31m" + "Player" + "\u001B[0m";
    public static final String fullName = "\u001B[31m" + "Player" + "\u001B[0m";
    private int health = 20;
    private boolean isAlive = true;
    private final int attackPower = 1;
    private int isOnFireTurns = 0;
    private Inventory inventory = new Inventory();
    public Player() {
        super(mapName, fullName, 3, 3);
    }

    @Override
    public void hit(Entity e) {
        e.receiveDamage(attackPower);
    }

    @Override
    public void receiveDamage(int damage) {
        if (this.health - damage <= 0) {
            this.health = 0;
            this.kill();
        }
        else this.health-= damage;
    }

    @Override
    public void kill() {
        this.isAlive = false;
    }

    @Override
    public boolean isOnFire() {
        return isOnFireTurns > 0;
    }

    @Override
    public int getHealthStatus() {
        return health;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
