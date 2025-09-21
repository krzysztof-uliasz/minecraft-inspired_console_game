package com.Tools;

import com.BlockTypes.Block;

import java.util.Objects;

public class Shovel extends Tool{
    public static final String mapName = "shovel";
    public static final String inventoryName = "Shovel";
    public Shovel() {
        super(mapName, inventoryName);
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean use(Block block) {
        return Objects.equals(block.designatedTool, mapName);
    }

    @Override
    public void upgrade() {
        this.upgradeLevel++;
    }
}
