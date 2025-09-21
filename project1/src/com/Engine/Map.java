package com.Engine;

import com.BlockTypes.Bedrock;
import com.BlockTypes.Block;
import com.BlockTypes.Cobblestone;
import com.BlockTypes.Dirt;
import com.EntityTypes.Creeper;
import com.EntityTypes.Enderman;
import com.EntityTypes.Entity;
import com.EntityTypes.Player;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Map {

    public static int mapSize = 8;
    private final Entity[][] mapEntities = new Entity[mapSize][mapSize];
    private final Thing[][] mapBlocks = new Thing[mapSize][mapSize];
    private List<Entity> entityList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();
    private Block recentlyMinedBlock = null;

    public Map(Player player1) {
        generateMapEntitiesList(player1);
        generateMapBlocksList();
        generateMapBlocks();
        generateMapEntities();
    }

    public void generateMapEntitiesList(Player player1) {
        entityList.add(player1);
        int rand1 = (int) (Math.random() * mapSize + mapSize);
        for (int i = 0; i < rand1; i++) {
            int rand2 = (int) (Math.random() * 2);
            switch (rand2) {
                case 0 -> {
                    int x = (int) (Math.random() * mapSize);
                    int y = (int) (Math.random() * mapSize);
                    if (findCorrectEntity(x, y) == null) {entityList.add(new Creeper(x, y));}
                }
                case 1 -> {
                    int x = (int) (Math.random() * mapSize);
                    int y = (int) (Math.random() * mapSize);
                    if (findCorrectEntity(x, y) == null) {entityList.add(new Enderman(x, y));}
                }
                default -> {}
            }
        }
    }

    public void generateMapEntities() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapEntities[i][j] = findCorrectEntity(i, j);
            }
        }
    }

    public void generateMapBlocks() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapBlocks[i][j] = findCorrectBlock(i, j);
            }
        }
    }

    public void generateMapBlocksList() {
        int rand1 = (int) (Math.random() * mapSize + mapSize * 4);
        for (int i = 0; i < rand1; i++) {
            int rand2 = (int) (Math.random() * 3);
            switch (rand2) {
                case 0 -> {
                    int x = (int) (Math.random() * mapSize);
                    int y = (int) (Math.random() * mapSize);
                    if (findCorrectBlock(x, y) == null) {blockList.add(new Dirt(x, y));}
                }
                case 1 -> {
                    int x = (int) (Math.random() * mapSize);
                    int y = (int) (Math.random() * mapSize);
                    if (findCorrectBlock(x, y) == null) {blockList.add(new Cobblestone(x, y));}
                }
                case 2 -> {
                    int x = (int) (Math.random() * mapSize);
                    int y = (int) (Math.random() * mapSize);
                    if (findCorrectBlock(x, y) == null) {blockList.add(new Bedrock(x, y));}
                }
                default -> {}
            }
        }
    }

    public Entity findCorrectEntity(int x, int y){
        return entityList.stream()
                .filter(w -> w.getMapCoordinatesX() == x && w.getMapCoordinatesY() == y)
                .findFirst()
                .orElse(null);
    }

    public Block findCorrectBlock(int x, int y){
        return blockList.stream()
                .filter(w -> w.getMapCoordinatesX() == x && w.getMapCoordinatesY() == y)
                .findFirst()
                .orElse(null);
    }


    public void removeEntity(Entity entity) {
        entityList.remove(entity);
        generateMapEntities();
    }

    public void removeBlock(Thing block) {
        blockList.remove(block);
        generateMapBlocks();
    }


    public void drawMap() {
        System.out.println();
        for (int g = 0; g < mapSize * 3 + 1; g++) {
            if (g % 3 == 0) drawBorderHorizontal();
            else if (g % 3 == 1) drawBoxesUpper(g / 3);
            else drawBoxesLower(g / 3);
        }
        System.out.println();
    }

    public void drawEmptySquare() {
        System.out.print("        ");
    }

    public void drawSquareWithBlock(int row, int column) {
        System.out.print(" " + mapBlocks[row][column].getMapName() + " ");
    }

    public void drawSquareWithEntity(int row, int column) {
        System.out.print(" " + mapEntities[row][column].getMapName() + " ");
    }

    public void drawBoxesUpper(int row) {
        for (int column = 0; column < mapEntities[row].length; column++) {
            if (mapEntities[row][column] == null) {
                System.out.print("|");
                drawEmptySquare();
            } else {
                System.out.print("|");
                drawSquareWithEntity(row, column);
            }
        }
        System.out.print("|");
        System.out.println();
    }

    public void drawBoxesLower(int row) {
        for (int column = 0; column < mapBlocks[row].length; column++) {
            if (mapBlocks[row][column] == null) {
                System.out.print("|");
                drawEmptySquare();
            } else {
                System.out.print("|");
                drawSquareWithBlock(row, column);
            }
        }
        System.out.print("|");
        System.out.println();
    }

    public void drawBorderHorizontal() {
        System.out.print("|");
        for (int h = 0; h < mapSize; h++) {
            System.out.print("________|");
        }
        System.out.println();
    }

    public void teleport(Entity entity, int X, int Y) {
        int newX = entity.getMapCoordinatesX() + X;
        int newY = entity.getMapCoordinatesY() + Y;
        if (newX < 0 || newX >= mapSize || newY < 0 || newY >= mapSize) System.out.println("Cannot go this way! You reached the end of the world!");
        else if (mapEntities[newX][newY] != null) System.out.println("Cannot go there, something is in your way!");
        else {
            entity.setMapCoordinatesX(newX);
            entity.setMapCoordinatesY(newY);
            generateMapEntities();
        }
    }

    public void moveUp(Entity entity) {
        teleport(entity, -1, 0);
    }

    public void moveDown(Entity entity) {
        teleport(entity, 1, 0);
    }

    public void moveLeft(Entity entity) {
        teleport(entity, 0, -1);
    }

    public void moveRight(Entity entity) {
        teleport(entity, 0, 1);
    }

    public void showMobsOnMap() {
        for (Entity entity: entityList) {
            if (findCorrectBlock(entity.getMapCoordinatesX(), entity.getMapCoordinatesY()) != null) {
                System.out.println(entity.getFullName() + " is standing on " + findCorrectBlock(entity.getMapCoordinatesX(), entity.getMapCoordinatesY()).getInventoryName() + "!");
            }
            else { System.out.println(entity.getFullName() + " is floating in mid-" + "\u001B[36m" + "air" + "\u001B[0m" + "..."); }
        }
        System.out.println();
    }

    public static void setMapSize(int mapSize) {
        Map.mapSize = mapSize;
    }
    public void setRecentlyMinedBlock(Block recentlyMinedBlock) {
        this.recentlyMinedBlock = recentlyMinedBlock;
    }

    public Block getRecentlyMinedBlock() {
        return recentlyMinedBlock;
    }

    public int getMapSize() {
        return mapSize;
    }
    public Thing[][] getMapBlocks() {
        return mapBlocks;
    }
    public Entity[][] getMapEntities() {
        return mapEntities;
    }

    public void addMapBlock(Block block,int x, int y) {
        block.setMapCoordinatesX(x);
        block.setMapCoordinatesY(y);
        blockList.add(block);
    }
}
