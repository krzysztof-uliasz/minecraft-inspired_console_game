package com.Engine;
import com.BlockTypes.Block;
import com.EntityTypes.Player;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    Player player;
    Map map;
    boolean game;

    public Game() {
        this.player = new Player();
        this.map = new Map(player);
        this.game = true;
    }

    public void playGame() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        map.drawMap();
        while (this.game && this.player.isAlive()) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "mine" -> {
                    if (map.findCorrectBlock(player.getMapCoordinatesX(), player.getMapCoordinatesY()) == null) {
                        System.out.println("Nothing to mine!"); }
                    else {
                        Block block = map.findCorrectBlock(player.getMapCoordinatesX(), player.getMapCoordinatesY());
                        if (player.getInventory().findCorrectTool(block.designatedTool) == null) {
                            System.out.println("You don't have the correct tool to break this block!");
                        }
                        else {
                            block.mine(map, player.getInventory().findCorrectTool(block.designatedTool));
                        }
                    }
                }
                case "collect" -> {
                    if (map.getRecentlyMinedBlock() == null) { System.out.println("There's nothing to collect!"); }
                    else {
                        Block block = map.getRecentlyMinedBlock();
                        block.collect(map, player.getInventory());
                        //Thread.sleep(1000);
                        map.drawMap();
                    }
                }
                case "place" -> {
                    System.out.println("Choose the amount: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Choose a correct amount.");
                        scanner.next();
                    }
                    int slot = scanner.nextInt();
                    //Block block
                }
                case "upgrade pickaxe" -> player.getInventory().findCorrectTool("pickaxe").upgrade();
                case "upgrade shovel" -> player.getInventory().findCorrectTool("shovel").upgrade();
                case "inventory" -> player.getInventory().displayPlayerInventory();
                case "debug add" -> player.getInventory().debugAddItems();
                case "show map" -> map.drawMap();
                case "show mobs" -> map.showMobsOnMap();
                case "w" -> {
                    map.moveUp(player);
                    map.drawMap(); }
                case "a" -> {
                    map.moveLeft(player);
                    map.drawMap(); }
                case "s" -> {
                    map.moveDown(player);
                    map.drawMap(); }
                case "d" -> {
                    map.moveRight(player);
                    map.drawMap(); }
                case "end game" -> {
                    System.out.println("Game Over! Thanks for playing!");
                    this.game = false;
                }
                default -> {
                    System.out.println("Unknown command, please try again or use 'help' to get help.");
                }
            }
        }
    }
}
