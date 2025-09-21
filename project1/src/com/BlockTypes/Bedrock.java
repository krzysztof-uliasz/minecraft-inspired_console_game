package com.BlockTypes;

import com.Engine.Inventory;
import com.Engine.Map;
import com.Engine.Thing;
import com.EntityTypes.Player;
import com.Interfaces.Stackable;
import com.Tools.Tool;

public class Bedrock extends Block implements Stackable {
    public static final String mapName = "\u001B[40m" + "bedrck" + "\u001B[0m";
    public static final String inventoryName = "\u001B[40m" + "Bedrock" + "\u001B[0m";
    private static final int HARDNESS = 0;
    private static final int MAX_QUANTITY = 64;
    private int quantity = 1;
    public Bedrock() {
        super(mapName, inventoryName, 0, 0);
    }
    public Bedrock(int quantity) {
        super(mapName, inventoryName, 0, 0);
        this.quantity = quantity;
    }

    public Bedrock(int positionX, int positionY) {
        super(mapName, inventoryName, positionX, positionY);
    }

    public Bedrock(int positionX, int positionY, int quantity) {
        super(mapName, inventoryName, positionX, positionY);
        this.quantity = quantity;
    }

    @Override
    public void place(Map map, Thing block, Player player, int slot) {

        block = player.getInventory().getPlayerInventory()[slot];
        int x = player.getMapCoordinatesX();
        int y = player.getMapCoordinatesY();

        if (map.findCorrectBlock(x,y) != null) {
            System.out.println("Cannot place a block there, something is already there!");
        }
        else if (!(block instanceof Block)) {
            System.out.println("Cannot place that!");
        } else map.addMapBlock((Block) block, x, y);
    }

    @Override
    public void mine(Map map, Tool tool){
        System.out.println("Too tough to break!");
    }

    @Override
    public void collect(Map map, Inventory inventory) {
        if (map.getRecentlyMinedBlock() == null) { System.out.println("There's nothing to collect!"); }
        else {
            inventory.addToInventory(map.getRecentlyMinedBlock());
            map.setRecentlyMinedBlock(null);
        }
    }

    @Override
    public int count() {
        return quantity;
    }

    @Override
    public void addToStack(int quantity) {
        if ((this.count() + quantity) <= MAX_QUANTITY) {
            this.setQuantity(this.count() + quantity);
        }
        else System.out.println("Can't fit this many items!");
    }
    @Override
    public void removeFromStack () {
        if (this.count() > 0) { this.setQuantity(this.count() - 1); }
        else System.out.println("Nothing to take away from!");
    }
    public void setQuantity ( int quantity){
        this.quantity = quantity;
    }
}
