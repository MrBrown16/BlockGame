package me.mrbrown.blockgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Blocks {
    SpriteBatch batch;
    Texture img;
    Shape currentShape;
    Array<String> shapes = new Array<>(0);

    //im not sure what this is

    public void allShapes(){
        shapes = new Array<>(7);
        shapes.add("straight", "block", "lShape", "backwardsLShape");
        shapes.add("zigzag", "backwardsZigzag", "tinyT", "tinyU");
        shapes.add("longStraight");
    }
    public void classicShapes(){
        shapes = new Array<>(7);
        shapes.add("straight", "block", "lShape", "backwardsLShape");
        shapes.add("zigzag", "backwardsZigzag", "tinyT");
    }

    public Blocks(Texture img) {
        this.img = img;
        currentShape.block( 0);
    }

}
