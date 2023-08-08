package me.mrbrown.blockgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Shape {
    Array<Vector2> positions; // shapes coordinates for each 1x1 block
    Color color;
    Vector2 pos;

    public Shape(int shapeversion, Vector2 pos, int rotation) {
        this.pos = pos;
        positions = new Array<>(0);
        switch (shapeversion) {
            case 0:
                positions = place(straight(rotation), pos);
                break;
            case 1:
                positions = place(block(rotation), pos);
                break;
            case 2:
                positions = place(lShape(rotation), pos);
                break;
            case 3:
                positions = place(backwardsLShape(rotation), pos);
                break;
            case 4:
                positions = place(zigzag(rotation), pos);
                break;
            case 5:
                positions = place(backwardsZigzag(rotation), pos);
                break;
            case 6:
                positions = place(tinyT(rotation), pos);
                break;
            case 7:
                positions = place(tinyU(rotation), pos);
                break;
            case 8:
                positions = place(longStaight(rotation),pos);
                break;

        }

    }

    private Array<Vector2> place(Array<Vector2> positions, Vector2 pos){
        Array<Vector2> positionsUpdated = new Array<>(positions.size);
        for (Vector2 pos1 : positions){
            positionsUpdated.add(new Vector2(pos1.x + pos.x, pos1.y + pos.y));
        }
        return positionsUpdated;
    }

    public Array<Vector2> straight(int rotation) {
        Array<Vector2> line = new Array<>(0);
        int rot = rotation % 2;
        switch (rot) {
            case 0:
                line = new Array<>(4);
                for (int i = 0; i < 4; i++) {

                    line.add(new Vector2(0, i));
                }
                break;
            case 1:
                line = new Array<>(4);
                for (int i = (-2); i < 2; i++) {

                    line.add(new Vector2(i, 0));
                }
                break;
        }
        return line;
    }

    public Array<Vector2> longStaight(int rotation) {
        Array<Vector2> line = new Array<>(0);
        int rot = rotation % 2;
        switch (rot) {
            case 0:
                line = new Array<>(5);
                for (int i = 0; i < 5; i++) {

                    line.add(new Vector2(0, (i)));
                }
                break;
            case 1:
                line = new Array<>(5);
                for (int i = (-2); i < 3; i++) {

                    line.add(new Vector2(i, 0));
                }
                break;
        }
        return line;
    }

    public Array<Vector2> block(int rotation) {
        Array<Vector2> line = new Array<>(4);

        line.add(new Vector2(-1, 0),
                new Vector2(-1, 1),
                new Vector2(0, 0),
                new Vector2(0, 1));

        return line;
    }

    public Array<Vector2> lShape(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;
        switch (rot) {
            case 0:
                line = new Array<>(4);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(1, 0));
                break;
            case 1:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 0));
                }
                line.add(new Vector2(1, 1));
                break;
            case 2:
                line = new Array<>(4);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(-1, 2));
                break;
            case 3:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 1));
                }
                line.add(new Vector2(-1, 0));
                break;

        }

        return line;
    }

    public Array<Vector2> backwardsLShape(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;

        switch (rot) {
            case 0:
                line = new Array<>(4);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(-1, 0));
                break;
            case 1:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 1));
                }
                line.add(new Vector2(1, 0));
                break;
            case 2:
                line = new Array<>(4);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(1, 2));
                break;
            case 3:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 0));
                }
                line.add(new Vector2(-1, 1));
                break;

        }

        return line;
    }

    public Array<Vector2> zigzag(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;
        switch (rot) {
            case 0:
                line = new Array<>(4);

                line.add(new Vector2(-1, 0),
                        new Vector2(0, 0),
                        new Vector2(0, 1),
                        new Vector2(1, 1));

                break;

            case 1:
                line = new Array<>(4);

                line.add(new Vector2(-1, 2),
                        new Vector2(-1, 1),
                        new Vector2(0, 1),
                        new Vector2(0, 0));

                break;
            case 2:
                line = new Array<>(4);

                line.add(new Vector2(-1, 0),
                        new Vector2(0, 0),
                        new Vector2(0, 1),
                        new Vector2(1, 1));

                break;

            case 3:
                line = new Array<>(4);

                line.add(new Vector2(0, 2),
                        new Vector2(0, 1),
                        new Vector2(1, 1),
                        new Vector2(1, 0));

                break;
        }
        return line;
    }

    public Array<Vector2> backwardsZigzag(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;
        switch (rot) {
            case 0:
                line = new Array<>(4);

                line.add(new Vector2(-1, 1),
                        new Vector2(0, 1),
                        new Vector2(0, 0),
                        new Vector2(1, 0));

                break;
            case 1:
                line = new Array<>(4);

                line.add(new Vector2(-1, 0),
                        new Vector2(-1, 1),
                        new Vector2(0, 1),
                        new Vector2(0, 2));

                break;
            case 2:
                line = new Array<>(4);

                line.add(new Vector2(-1, 1),
                        new Vector2(0, 1),
                        new Vector2(0, 0),
                        new Vector2(1, 0));

                break;
            case 3:
                line = new Array<>(4);

                line.add(new Vector2(0, 0),
                        new Vector2(0, 1),
                        new Vector2(1, 1),
                        new Vector2(1, 2));

                break;
        }
        return line;
    }

    public Array<Vector2> tinyT(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;

        switch (rot) {
            case 0:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 1));
                }
                line.add(new Vector2(0, 0));
                break;
            case 1:
                line = new Array<>(4);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(-1, 1));

                break;
            case 2:
                line = new Array<>(4);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 0));
                }
                line.add(new Vector2(0, 1));
                break;
            case 3:
                line = new Array<>(4);
                for (int i = (0); i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(1, 1));
                break;

        }

        return line;
    }

    public Array<Vector2> tinyU(int rotation) {
        Array<Vector2> line = new Array<>(0);

        int rot = rotation % 4;

        switch (rot) {
            case 0:
                line = new Array<>(5);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 0));
                }
                line.add(new Vector2(-1, 1), new Vector2(1, 1));
                break;
            case 1:
                line = new Array<>(5);
                for (int i = 0; i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(-1, 0), new Vector2(-1, 2));

                break;
            case 2:
                line = new Array<>(5);
                for (int i = -1; i < 2; i++) {

                    line.add(new Vector2(i, 1));
                }
                line.add(new Vector2(-1, 0), new Vector2(1, 0));
                break;
            case 3:
                line = new Array<>(5);
                for (int i = (0); i < 3; i++) {

                    line.add(new Vector2(0, i));
                }
                line.add(new Vector2(1, 0), new Vector2(1, 2));
                break;

        }

        return line;
    }

}
