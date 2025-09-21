package com.Engine;

import com.BlockTypes.Bedrock;
import com.BlockTypes.Block;
import com.BlockTypes.Cobblestone;
import com.BlockTypes.Dirt;
import com.EntityTypes.Player;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        game.playGame();

    }
}

