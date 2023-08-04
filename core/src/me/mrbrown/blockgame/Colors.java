package me.mrbrown.blockgame;

import com.badlogic.gdx.graphics.Color;

public class Colors {
    
    Color yellow = new Color(1, (float) 0.8, 0, 1);
    Color green = new Color(0, (float) 0.7, 0, 1);
    Color blue = new Color(0,  0, (float) 0.9, 1);
    Color red = new Color( (float) 0.8,  0, 0, 1);
    Color orange = new Color(1, (float) 0.2, 0, 1);
    Color pink = new Color(1, 0, (float) 0.5, 1);
    Color purple = new Color((float) 0.42, 0, (float) 0.7, 1);
    Color brown = new Color((float) 0.3, (float) 0.15, 0, 1);
    Color turq = new Color(0, (float) 0.9, (float) 0.9, 1);
    Color grey = new Color((float) 0.76, (float) 0.76, (float) 0.84, 1);
    
    Color[] colorList;
    
    
    public Colors(){
        colorList = new Color[10];
        colorList[0] = red;
        colorList[1] = green;
        colorList[2] = blue;
        colorList[3] = orange;
        colorList[4] = brown;
        colorList[5] = turq;
        colorList[6] = yellow;
        colorList[7] = pink;
        colorList[8] = purple;
        colorList[9] = grey;
    }
    
    public Color getYellow() {
        return yellow;
    }

    public Color getGreen() {
        return green;
    }

    public Color getBlue() {
        return blue;
    }

    public Color getRed() {
        return red;
    }

    public Color getOrange() {
        return orange;
    }

    public Color getPink() {
        return pink;
    }

    public Color getPurple() {
        return purple;
    }

    public Color getBrown() {
        return brown;
    }

    public Color getTurq() {
        return turq;
    }

    public Color getGrey() {
        return grey;
    }
    
    public Color getColor(int index) {
        return colorList[index];
    }
    
    
}
