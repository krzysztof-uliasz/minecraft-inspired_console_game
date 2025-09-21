package com.Engine;

import com.BlockTypes.Bedrock;
import com.BlockTypes.Cobblestone;
import com.BlockTypes.Dirt;
import com.Interfaces.Stackable;
import com.Tools.Pickaxe;
import com.Tools.Shovel;
import com.Tools.Tool;

import java.util.Objects;
import java.util.Scanner;

public class Inventory {

    private final Thing[] playerInventory = new Thing[36];

    public Inventory() {
    }

    public void addToInventory(Thing item){
        displayPlayerInventory();
        System.out.println("Choose an inventory slot: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Choose a number.");
            scanner.next();
        }
        int slot = scanner.nextInt();
        if (slot < 0 || slot > 35) {
            System.out.println("Incorrect slot.");
        }
        else if (playerInventory[slot] == null) {
            playerInventory[slot] = item;
            if (item instanceof Stackable && item.count() > 64){
                System.out.println("Can't hold this many items!");
                playerInventory[slot] = null;
            }
            else System.out.println("Added to inventory.");
            displayPlayerInventory();
        }
        else if (Objects.equals(item.getInventoryName(), playerInventory[slot].getInventoryName()) && playerInventory[slot] instanceof Stackable) {
            ((Stackable) playerInventory[slot]).addToStack(item.count());
        }
        else if (Objects.equals(item.getInventoryName(), playerInventory[slot].getInventoryName()) && !(playerInventory[slot] instanceof Stackable)) {
            System.out.println("Can't hold two Tools in the same slot.");
        }
        else if (!Objects.equals(item.getInventoryName(), playerInventory[slot].getInventoryName())) {
            System.out.println("Can't use this slot, something's already here!");
        }
    }

    public void displayPlayerInventory() {
        for (int i = 0; i < playerInventory.length; i++) {
            if (i%9 == 0) { System.out.println(); }
            if (playerInventory[i] == null) {
                System.out.print(i + ": empty slot");
            }
            else if (playerInventory[i] instanceof Stackable) {
                System.out.print(i + ": " + playerInventory[i].getInventoryName() + " - " + ((Stackable) playerInventory[i]).count());
            }
            else { System.out.print(i + ": " + playerInventory[i].getInventoryName()); }
            System.out.print(", ");
        }
        System.out.print("\n\n");
    }

    public Tool findCorrectTool(String toolname) {
        for (Thing item: playerInventory) {
            if (item == null) { continue; }
            if (Objects.equals(item.getMapName(), toolname)) { return (Tool) item; }
        }
        return null;
    }

    public void debugAddItems() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name the item you want to add: ");
        String item = scanner.nextLine().trim().toLowerCase();
        System.out.println("Choose the amount: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Choose a correct amount.");
            scanner.next();
        }
        int amount = scanner.nextInt();
        switch (item) {
            case "shovel" -> addToInventory(new Shovel());
            case "pickaxe" -> addToInventory(new Pickaxe());
            case "dirt" -> addToInventory(new Dirt(amount));
            case "cobble" -> addToInventory(new Cobblestone(amount));
            case "bedrock" -> addToInventory(new Bedrock(amount));
            default -> System.out.println("Unknown item.");
        }
    }
    public Thing[] getPlayerInventory() {
        return playerInventory;
    }
}
