package com.Tools;

import com.BlockTypes.Block;
import com.Engine.Thing;
import com.Interfaces.Upgradable;

public abstract class Tool extends Thing implements Upgradable {

    int upgradeLevel = 1;

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public Tool(String publicName, String inventoryName) {
        super(publicName, inventoryName);
    }
    public abstract boolean use(Block block);
}
