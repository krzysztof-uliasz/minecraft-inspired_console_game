package com.BlockTypes;

import com.Engine.Inventory;
import com.Engine.Map;
import com.Engine.Thing;
import com.EntityTypes.Player;
import com.Interfaces.Stackable;
import com.Tools.Tool;

public class Dirt extends Block implements Stackable {
    public static final String mapName = "\u001B[33m" + " dirt " + "\u001B[0m";
    public static final String inventoryName = "\u001B[33m" + "Dirt" + "\u001B[0m";

    private static final int HARDNESS = 8;
    private static final int MAX_QUANTITY = 64;
    private int quantity = 1;

    public Dirt() {
        super(mapName, inventoryName, 0, 0);
        designatedTool = "shovel";
    }

    public Dirt(int quantity) {
        super(mapName, inventoryName, 0, 0);
        this.quantity = quantity;
        designatedTool = "shovel";
    }

    public Dirt(int positionX, int positionY) {
        super(mapName, inventoryName, positionX, positionY);
        designatedTool = "shovel";
    }

    public Dirt(int positionX, int positionY, int quantity) {
        super(mapName, inventoryName, positionX, positionY);
        this.quantity = quantity;
        designatedTool = "shovel";
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
    public void mine(Map map, Tool tool) throws InterruptedException {
        int x = this.getMapCoordinatesX();
        int y = this.getMapCoordinatesY();
        Block block = map.findCorrectBlock(x, y);
        if (block == null) System.out.println("Nothing's here!");
        else if (!tool.use(block)) {
            System.out.println("Not the correct tool!");
        } else {
            System.out.println("Digging...");
            Thread.sleep(HARDNESS * (200) / tool.getUpgradeLevel());
            map.removeBlock(block);
            map.setRecentlyMinedBlock(block);
            System.out.println("Mined " + map.getRecentlyMinedBlock().getInventoryName() + ". Type 'collect' if you want to add it to your inventory.");
        }
    }

    @Override
    public void collect(Map map, Inventory inventory) {
        if (map.getRecentlyMinedBlock() == null) {
            System.out.println("There's nothing to collect!");
        } else {
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


