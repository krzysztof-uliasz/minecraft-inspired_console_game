package com.Tools;

import com.BlockTypes.Block;

import java.util.Objects;

public class Pickaxe extends Tool{
    public static final String mapName = "pickaxe";
    public static final String inventoryName = "Pickaxe";
    public Pickaxe() {
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
