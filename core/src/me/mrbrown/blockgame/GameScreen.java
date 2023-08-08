package me.mrbrown.blockgame;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    Random random;
    GameModel model;
    Shape currentShape;
    MyColors colors;
    SpriteBatch batch;
    Texture img;
    Vector2 intpos; // position x and y in the Consts.boardHeight * Consts.boardWidth board
    Vector2 pos; // same as intpos, just updated each render call, y coordinate can be float
    int shapeInt, rotation, speed;
    float time;
    float timer;
    
    public GameScreen(Texture img) {
        batch = new SpriteBatch();
        this.img = img;
        random = new Random();
        model = new GameModel();
        colors = new MyColors();
        speed = 1;
        timer = 0;
        chooseRandomShape();
        pos = intpos;
        getCurrentShape();
    }

    public void chooseRandomShape() { // resets the falling shape's attributes
        shapeInt = random.nextInt(9);
        rotation = 0;
        intpos = new Vector2((int) (Consts.boardWidth / 2), Consts.boardHeight); // initial position of falling shapes
    }

    public void shapeFalling(float delta) {
        time += (delta * speed);
        if (time >= 1) {
            if (!check(currentShape, Consts.bellow)) {
                intpos.y -= 1;
            } else {
                saveFallenShape();
                chooseRandomShape();
            }

            time = 0;
        }
    }

    private void saveFallenShape() {
        for (Vector2 pos : currentShape.positions) {
            System.out.println("x: " + pos.x + " y: " + pos.y);
            model.addBlock((int) pos.y, (int) pos.x, shapeInt);
        }
        model.checkFinRows(0);
    }

    public void drawShape() {
        // System.out.println(" pos: " + pos);
        Array<Vector2> coords = currentShape.positions;
        batch.begin();
        batch.setColor(colors.getColor(shapeInt));
        for (Vector2 coord : coords) {
            batch.draw(img, coord.x * Consts.blockSize, coord.y * Consts.blockSize, Consts.blockSize, Consts.blockSize);
        }
        batch.setColor(1, 1, 1, 1);
        batch.end();
    }

    @Override
    public void render(float delta) {
        getCurrentShape();
        handleEvents(delta);
        ScreenUtils.clear(0, 0, 1, 1);
        super.render(delta);
        createBoard();
        shapeFalling(delta);
        drawShape();
    }

    public void createBoard() { // later maybe give access to users to set width and height

        batch.begin();

        for (int y = 0; y < Consts.boardHeight; y++) {
            for (int x = 0; x < Consts.boardWidth; x++) {
                if (model.getTableCell(x, y) == -1) {
                    batch.setColor(colors.getGrey());
                } else {
                    batch.setColor(colors.getColor(model.getTableCell(x, y)));
                }

                batch.draw(img, x * Consts.blockSize, y * Consts.blockSize, Consts.blockSize, Consts.blockSize);
            }

        }
        batch.setColor(1, 1, 1, 1);
        batch.end();
    }

    private void getCurrentShape() {
        currentShape = new Shape(shapeInt, intpos, rotation);
    }

    public void choseShape() { // TODO: remove after testing Only for testing!!
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) {
            shapeInt = 0;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            shapeInt = 1;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            shapeInt = 2;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            shapeInt = 3;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            shapeInt = 4;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            shapeInt = 5;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            shapeInt = 6;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
            shapeInt = 7;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
            shapeInt = 8;
        }

    }

    public void rotate() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)) {

            Shape tmpShape = new Shape(shapeInt, intpos, rotation + 1); // the place it is falling from rotated 1*
            if (!check(tmpShape, Consts.in)) { // the upper one can be rotated

                tmpShape = new Shape(shapeInt, new Vector2(intpos.x, intpos.y), rotation + 1); // the place it is falling to rotated 1*
                if (!check(tmpShape, Consts.in)) { // both can be rotated
                    rotation++;
                }

            } else {// the upper one can't be rotated
                for (int i : Consts.checkablePositions) { // checking positions around pos upper
                    tmpShape = new Shape(shapeInt, new Vector2(intpos.x + i, intpos.y + 1), rotation + 1);
                    if (!check(tmpShape, Consts.in)) { // the upper pos is free
                        tmpShape = new Shape(shapeInt, new Vector2(intpos.x + i, intpos.y), rotation + 1);
                        if (!check(tmpShape, Consts.in)) { // lower pos is free, it can be used for currentShape
                            rotation++;
                            intpos.x += i;
                            // currentShape = new Shape(shapeInt, intpos, rotation); // currentshape got all the properties of tmpShape
                            break;
                        }
                    }
                }
            }
        }
    }

    public void lower() { // accelerates the shape's descend
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            speed = 12;
        } else {
            speed = 1;
        }
    }

    public void move(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            timer += delta;
            if (!check(currentShape, Consts.right) && timer > 0.15) {
                intpos.x += 1;
                timer = 0;
            }
        }else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            timer += delta;
            if (!check(currentShape, Consts.left) && timer > 0.15) {
                intpos.x -= 1;
                timer = 0;
            }
        }
    }

    private void handleEvents(float delta) { // collections for eventHandling
        rotate();
        choseShape();
        move(delta);
        lower();
    }

    private boolean check(Shape shape, int mode) {
        boolean tmp = false;
        switch (mode) {
            case 0:
                for (Vector2 pos : shape.positions) {
                    if (model.check((int) pos.x, (int) pos.y)) {
                        tmp = true;
                        break;
                    }
                }
                break;
            case 1:
                for (Vector2 pos : shape.positions) {
                    if (model.checkBellow((int) pos.x, (int) pos.y)) {
                        tmp = true;
                        break;
                    }
                }
                break;
            case 2:
                for (Vector2 pos : shape.positions) {
                    if (model.checkLeft((int) pos.x, (int) pos.y)) {
                        tmp = true;
                        break;
                    }
                }
                break;
            case 3:
                for (Vector2 pos : shape.positions) {
                    if (model.checkRight((int) pos.x, (int) pos.y)) {
                        tmp = true;
                        break;    
                    }
                }
                break;

        }
        return tmp;
    }
}
