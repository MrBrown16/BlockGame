package me.mrbrown.blockgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Shape{
    Array<Vector2> line; //shapes coordinates for each 1x1 block
    Color color;
    //modell-ish class contains the logic to different shapes 
    public Shape(int shapeversion, Vector2 pos, int rotation){
        line = new Array<>(0);
        switch (shapeversion){
            case 0:
                straight(pos, rotation);
                // color 
                break;
            case 1:
                block(pos, rotation);
                break;
            case 2:
                lShape(pos, rotation);
                break;
            case 3:
                backwardsLShape(pos, rotation);
                break;
            case 4:
                zigzag(pos, rotation);
                break;
            case 5:
                backwardsZigzag(pos, rotation);
                break;
            case 6:
                tinyT(pos, rotation);
                break;
            case 7:
                tinyU(pos, rotation);
                break;
            case 8:
                longStaight(pos, rotation);
                break;

        }
        
    }

    public Array<Vector2> straight(Vector2 pos, int rotation){
        
        int rot = rotation % 2;
        switch (rot) {
        case 0:
            line = new Array<>(4);
            for(int i = 0; i < 4; i++){
                
                line.add(new Vector2(pos.x, (pos.y + i * Consts.blockSize)));
            }
            break;
        case 1:
            line = new Array<>(4);
            for(int i = (-2); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            break;
        }
        return line;
    }
    public Array<Vector2> longStaight(Vector2 pos, int rotation){
        int rot = rotation % 2;
        switch (rot) {
        case 0:
            line = new Array<>(5);
            for(int i = 0; i < 5; i++){
                
                line.add(new Vector2(pos.x, (pos.y + i * Consts.blockSize)));
            }
            break;
        case 1:
            line = new Array<>(5);
            for(int i = (-2); i < 3; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            break;
        }
        return line;
    }
    public Array<Vector2> block(Vector2 pos, int rotation){
        line = new Array<>(4);

        line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y),
             new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize),
             new Vector2(pos.x, pos.y),
             new Vector2(pos.x, pos.y + Consts.blockSize));

        return line;
    }
    public Array<Vector2> lShape(Vector2 pos, int rotation){
        int rot = rotation % 4;
        switch (rot) {
        case 0:
        line = new Array<>(4);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, (pos.y + i * Consts.blockSize)));
            }
            line.add(new Vector2(pos.x + Consts.blockSize, pos.y));
            break;
        case 1:
            line = new Array<>(4);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            line.add(new Vector2((pos.x + Consts.blockSize), pos.y + Consts.blockSize));
            break;
        case 2:
            line = new Array<>(4);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, (pos.y + i * Consts.blockSize)));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + 2 *  Consts.blockSize));
            break;
        case 3:
            line = new Array<>(4);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y + Consts.blockSize));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y));
            break;
        
        }
        
        return line;
    }
    
    
    public Array<Vector2> backwardsLShape(Vector2 pos, int rotation){
        int rot = rotation % 4;

        switch (rot) {
        case 0:
        line = new Array<>(4);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, pos.y + i * Consts.blockSize));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y));
            break;
        case 1:
            line = new Array<>(4);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y + Consts.blockSize));
            }
            line.add(new Vector2(pos.x + Consts.blockSize, pos.y));
            break;
        case 2:
            line = new Array<>(4);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, pos.y + i * Consts.blockSize));
            }
            line.add(new Vector2(pos.x + Consts.blockSize, pos.y + 2 *  Consts.blockSize));
            break;
        case 3:
            line = new Array<>(4);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize));
            break;
        
        }
        
        return line;
    }

    public Array<Vector2> zigzag(Vector2 pos, int rotation){
        int rot = rotation % 4;
        switch (rot) {
        case 0:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y),
                 new Vector2(pos.x, pos.y),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x + Consts.blockSize, pos.y + Consts.blockSize));

            break;

        case 1:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + 2 * Consts.blockSize),
                 new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y));
            
            break;
        case 2:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y),
                 new Vector2(pos.x, pos.y),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x + Consts.blockSize, pos.y + Consts.blockSize));

            break;

        case 3:
            line = new Array<>(4);

            line.add(new Vector2(pos.x, pos.y + 2 * Consts.blockSize),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x + 1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x + 1 * Consts.blockSize, pos.y));
            
            break;
        }
        return line;
    }
        public Array<Vector2> backwardsZigzag(Vector2 pos, int rotation){
        int rot = rotation % 4;
        switch (rot) {
        case 0:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y),
                 new Vector2(pos.x + Consts.blockSize, pos.y));

            break;
        case 1:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y),
                 new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x , pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y + 2 * Consts.blockSize));

            break;
        case 2:
            line = new Array<>(4);

            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x, pos.y),
                 new Vector2(pos.x + Consts.blockSize, pos.y));

            break;
        case 3:
            line = new Array<>(4);

            line.add(new Vector2(pos.x, pos.y),
                 new Vector2(pos.x, pos.y + Consts.blockSize),
                 new Vector2(pos.x + 1 * Consts.blockSize, pos.y + Consts.blockSize),
                 new Vector2(pos.x + 1 * Consts.blockSize, pos.y + 2 * Consts.blockSize));

            break;
        }
        return line;
    }

    public Array<Vector2> tinyT(Vector2 pos, int rotation){
        int rot = rotation % 4;

        switch (rot) {
        case 0:
        line = new Array<>(4);
            for(int i = -1; i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y + Consts.blockSize));
            }
            line.add(new Vector2(pos.x, pos.y));
            break;
        case 1:
            line = new Array<>(4);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, (pos.y + i * Consts.blockSize)));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y +  Consts.blockSize));
            
            break;
        case 2:
            line = new Array<>(4);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            line.add(new Vector2(pos.x, pos.y + Consts.blockSize));
            break;
        case 3:
            line = new Array<>(4);
            for(int i = (0); i < 3; i++){
                
                line.add(new Vector2(pos.x, pos.y + i * Consts.blockSize));
            }
            line.add(new Vector2(pos.x + Consts.blockSize, pos.y + Consts.blockSize));
            break;
        
        }
        
        return line;
    }
    public Array<Vector2> tinyU(Vector2 pos, int rotation){
        int rot = rotation % 4;

        switch (rot) {
        case 0:
            line = new Array<>(5);
            for(int i = (-1); i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y + Consts.blockSize), new Vector2(pos.x + Consts.blockSize, pos.y + Consts.blockSize));
            break;
        case 1:
            line = new Array<>(5);
            for(int i = 0; i < 3; i++){
                
                line.add(new Vector2(pos.x, pos.y + i * Consts.blockSize));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y), new Vector2(pos.x -1 * Consts.blockSize, pos.y + 2 * Consts.blockSize));
            
            break;
        case 2:
            line = new Array<>(5);
            for(int i = -1; i < 2; i++){
                
                line.add(new Vector2(pos.x + i * Consts.blockSize, pos.y + Consts.blockSize));
            }
            line.add(new Vector2(pos.x -1 * Consts.blockSize, pos.y), new Vector2(pos.x + Consts.blockSize, pos.y));
            break;
        case 3:
            line = new Array<>(5);
            for(int i = (0); i < 3; i++){
                
                line.add(new Vector2(pos.x, pos.y + i * Consts.blockSize));
            }
            line.add(new Vector2(pos.x + Consts.blockSize, pos.y), new Vector2(pos.x + Consts.blockSize, pos.y + 2 * Consts.blockSize));
            break;
        
        }
        
        return line;
    }

}
