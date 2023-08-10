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

public class ClassicGameScreen extends ScreenAdapter{
    //game board (23x11*blockSize), placed blocks, falling shape, shadow of falling shape
    //upcoming shapes
    //highscore, currentscore, time
    //gameController

    //use gamemode enum / separate gameScreen for each gamemode
    Random random;
    SpriteBatch batch;
    Texture tp;
    int boardOriginX;
    int boardOriginY;
    Vector2 pos;
    int shapeint;
    Shape currentShape;
    int rotation;
    MyColors colors;
    GameModel model;
    // Set<Integer> xDimensions;
    int time;
    int lowY;
    boolean stop;
    boolean left;
    boolean right;

    public ClassicGameScreen(){
        random = new Random();
        colors = new MyColors();
        model = new GameModel();
        time = 1;
        lowY = -1;
        stop = false;
        left = false;
        right = false;
        // boardViewport = new FitViewport(Consts.boardWidth * Consts.blockSize, Consts.boardHeight * Consts.blockSize);
        batch = new SpriteBatch();
        tp = new Texture("transparent.png");
        boardOriginX = (Gdx.graphics.getWidth() - (Consts.boardWidth * Consts.blockSize)) / 2;
        boardOriginY = (Gdx.graphics.getHeight() - (Consts.boardHeight * Consts.blockSize)) / 2;
        pos = new Vector2(Consts.boardWidth / 2 * Consts.blockSize, Consts.boardHeight * Consts.blockSize); //center top of board initial position of the falling pieces
        chooseRandomShape();
        getCurrentShape();
        // getLowestPoints();
    }

    
    public void createBoard(){ //later maybe give access to users to set width and height

        batch.begin();
        
        for(int y = 0; y < Consts.boardHeight; y++){
            for(int x = 0; x < Consts.boardWidth; x++){
                if (model.getTableCell(x, y) == -1) {
                    batch.setColor(colors.getGrey());
                }else{
                    batch.setColor(colors.getColor(model.getTableCell(x, y)));
                }
                
                batch.draw(tp, boardOriginX + x * Consts.blockSize, boardOriginY + y * Consts.blockSize, Consts.blockSize, Consts.blockSize);
            }    
            
        }
        batch.setColor(1, 1, 1, 1);
        batch.end();
    }
    
    private void getCurrentShape() {
        currentShape = new Shape(shapeint, pos, rotation);
    }

    public void shapeFalling(float delta){ 
        checkTouchDown2();
        System.out.println(" lowY: " + lowY + "stop: " + stop);
        if (!stop) { // no collision can fall
            pos.y = pos.y - Consts.blockSize * delta * time;
            System.out.println(" this should be the case most of the time ");
            // System.out.println(" stop false pos: " + pos);
        }else{
            if (pos.y != (int)pos.y && pos.y > lowY) {
                System.out.println(" there was a collision detected, but it still has to fall to it");
                pos.y = pos.y - Consts.blockSize * delta * time;
            }else{
                pos.y = Math.round(pos.y / Consts.blockSize);
                System.out.println("the collision was realised, save and spawn new shape");
                saveFallenShape();
                chooseRandomShape();
            }
        }
        
    }

    public void chooseRandomShape() { // resets the falling shape's attributes
        shapeint = random.nextInt(9);
        pos = new Vector2(Consts.boardWidth / 2 * Consts.blockSize, Consts.boardHeight * Consts.blockSize);
        rotation = 0;
        stop = false;
    }
    
    public void drawShape() {
        // System.out.println(" pos: " + pos);
        Array<Vector2> coords = currentShape.positions;
        batch.begin();
        batch.setColor(colors.getColor(shapeint));
        for (Vector2 coord : coords) {
            batch.draw(tp, coord.x + boardOriginX, coord.y + boardOriginY, Consts.blockSize, Consts.blockSize);
        }
        batch.setColor(1, 1, 1, 1);
        batch.end();
    }
    
    private void checkTouchDown2() {
        for(Vector2 pos : currentShape.positions){ //check each falling block if there is still a place to fall
            if(checkTouchDown(pos)==true){
                stop = true;
                break;
            }
            
        }
    }
    private boolean checkIn2(Shape tmpShape) {
        boolean tmp = false;
        for(Vector2 pos : tmpShape.positions){ //check each falling block if they overlap any others
            if(checkIn(pos)==true){
                tmp = true;
                break;
            }
        }
        if (tmp) {
            return true;
        }
        return false;
    }
    
    private void checkLeft2() {
        for(Vector2 pos : currentShape.positions){ //check each falling block if there is free space to its left
            if(checkLeft(pos)==true){
    
                left = true;
            }
        }
    }
    private void checkRight2() {
        for(Vector2 pos : currentShape.positions){ //check each falling block if there is free space to its right
            
            if(checkRight(pos)==true){
    
                right = true;
            }
        }
    }

    public boolean checkTouchDown(Vector2 pos){ //returns false if there is space, collision = true
        int y = (int) pos.y / Consts.blockSize;
        int x = (int) pos.x / Consts.blockSize;
        boolean collided = model.checkBellow(x, y);
        if (collided) {
            lowY = y;
        }
        return collided;
    }
    public boolean checkIn(Vector2 pos){ 
        int y = (int) pos.y / Consts.blockSize;
        int x = (int) pos.x / Consts.blockSize;
        
        return model.check(x, y);
        
    }
    public boolean checkLeft(Vector2 pos){ 
        int y = (int) pos.y / Consts.blockSize;
        int x = (int) pos.x / Consts.blockSize;
        
        return model.checkLeft(x, y);
        
    }
    public boolean checkRight(Vector2 pos){ //WARNING pos.x - boardOriginX and similarly with y is needed!!!
        int y = (int) pos.y / Consts.blockSize;
        int x = (int) pos.x / Consts.blockSize;
        
        return model.checkRight(x, y);
        
    }
    private void saveFallenShape(){
        for(Vector2 pos:currentShape.positions){
            int x = (int) pos.x / Consts.blockSize;
            int y = Math.round(pos.y / Consts.blockSize);
            System.out.println(pos.x + " : " + pos.y/ Consts.blockSize);
            System.out.println(x + " : " + y);
            model.addBlock(y, x, shapeint);
        }
    }
    
    @Override
    public void render(float delta) {
        getCurrentShape();
        handleEvents();        
        ScreenUtils.clear(0, 0, 1, 1);
        super.render(delta);
        createBoard();
        shapeFalling(delta);
        drawShape();
    }
    
    public void choseShape(){ //TODO: remove after testing Only for testing!!
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) {
            shapeint =0;
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            shapeint =1;            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            shapeint =2;            
            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            shapeint =3;            
            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            shapeint =4;            
            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            shapeint =5;            
            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            shapeint =6;            
            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
            shapeint =7;            
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
            shapeint =8;
        }
        
    }
    public void rotate(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)) {
            
            Shape tmpShape = new Shape(shapeint, pos, rotation); //the place it is falling from rotated 1*
            if (!checkIn2(tmpShape)) { //the upper one can be rotated
                tmpShape = new Shape(shapeint, pos, rotation); //the place it is falling to rotated 1*
                if (!checkIn2(tmpShape)) { // both can be rotated
                    rotation++;
                    left = false;
                    right = false;
                }
            }else{//the upper one can't be rotated
                for(int i : Consts.checkablePositions){ //checking positions around pos upper 
                    tmpShape = new Shape(shapeint, pos, rotation);
                    if (!checkIn2(tmpShape)) { //the upper pos is free
                        tmpShape = new Shape(shapeint, pos, rotation);
                        if (!checkIn2(tmpShape)) { //lower pos is free, it can be used for currentShape
                            rotation++;
                            // System.out.println("Deep in the ifs in rotate pos.x: " + pos.x + " i: " + i + " consts.blockSize: " + Consts.blockSize + " pos.x + i * Consts.blockSize: " + (pos.x + i * Consts.blockSize));
                            pos.x += i * Consts.blockSize; 
                            currentShape = new Shape(shapeint, pos, rotation); // currentshape got all the properties of tmpShape
                            left = false;
                            right = false;
                            break;
                        }           
                    }                    
                }
            }
        }
    }
    public void lower(){ //accelerates the shape's descend
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            time = 10;
        }else{
            time = 1;
        }
    }
    public void move(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
            checkRight2();
            if (!right) {
                pos.x += Consts.blockSize;
                right = false;
                left = false; 
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
            checkLeft2();
            if (!left) {
                pos.x -= Consts.blockSize;
                left = false;
                right = false;
            }
        }
    }
    
    private void handleEvents(){ //collections for eventHandling
        rotate();
        choseShape();
        move();
        lower();
    }
}
