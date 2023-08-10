package me.mrbrown.blockgame;

import com.badlogic.gdx.utils.Array;

public class GameModel {
    
    //table contains the table and all the blocks are represented by ints -1 = empty space
    //only deals with small ints that are the coordinates(indexes) in the table
    int[][] table = new int[Consts.boardHeight][Consts.boardWidth];

    
    public GameModel() {
        setupArray();
        // printTable();
    }
    
    private void setupArray(){
        for(int i = 0; i < Consts.boardHeight; i++){
            for(int j = 0; j < Consts.boardWidth; j++){
                table[i][j] = -1;
            }
        }
    }
    public void addBlock(int row, int cell, int block){
        // System.out.println(" row: " + row + " cell: " + cell);
        if (row >= Consts.boardHeight) {
            //TODO: lost screen
        }else if (cell >= Consts.boardWidth){
            //Do Nothing
        }else if (cell < 0){
            //Do Nothing
        }else{
            //TODO: check for finished rows
            table[row][cell] = block;
            // checkFinRows(0);
        }
    }
    
    public boolean check(int x, int y){ // true = collision
        if ((y < 0) || (x < 0) || (x > Consts.boardWidth-1)) {
            //collision with the floor || Left wall || Right wall
            return true;
        }else if (y >= Consts.boardHeight){ 
            //not jet on the board
            return false;
        }else if (table[y][x] == -1) {
            //Empty space
            return false;
        }else if (table[y][x] > -1){
            //Occupied space, there is a Collision
            // System.out.println("occupied x : " + x + " y: " + y);
            return true;
        }else{
            //there shouldn't be an else
            System.err.println("check(x,y) invalid values");
            System.out.println("error x : " + x + " y: " + y);
            return false;
        }
    }
    
    public boolean checkBellow(int x, int y){ //true = collision
        if (y > 0){
            return check(x, y-1);
        }
        return true;
    }
    public boolean checkLeft(int x, int y){ //true = collision
        if (x > 0){
            return check(x-1, y);
        }
        return true;
    }
    public boolean checkRight(int x, int y){ //true = collision
        if (x < Consts.boardWidth){
            return check(x+1, y);
        }
        return true;
    }    
    public int getTableCell(int x, int y) {
        return table[y][x];
    }

    public void checkFinRows(int startRowIndex){ //tmpfull = true -> row is full
        boolean tmpempty; // true = all -1 no need to propagate
        boolean tmpfull; // starts with true but one empty cell sets it to false
        Array<Integer> fullRows = new Array<>(Consts.maxRowsCompletedByOneShape);
        int emptyRow = Consts.boardHeight-1;
        int counter = 0;
        for (int i = startRowIndex; i < Consts.boardHeight; i++){
            tmpfull = true;
            tmpempty = true;
            
            for (int j = 0; j < Consts.boardWidth; j++){
                if (table[i][j] == -1){
                    tmpfull = false;//should do nothing
                }else if (table[i][j] > -1){
                    tmpempty = false;
                }
                counter++;
            }
            if (tmpfull) { // row is full, copies i+1 indexed row to present and checks for other full rows 
                fullRows.add(i);
                counter++;
            }
            if (tmpempty) {
                if (emptyRow > i) {
                    emptyRow = i;
                    counter++;
                }
                break;       
            }
        }
        for (int i : fullRows){
            counter += copyRows(i, emptyRow);
        }
        // System.out.println(" counter at the End: " + counter);
        // return false;
    }

    private int copyRows(int rowIndex, int maxRow){
        // System.out.println( " 109 " + " rowindex: " + rowIndex + " maxRow: " + maxRow);
        int counter = 0;
        // if (rowIndex < Consts.boardHeight) {
            for (int j = 0; j < maxRow; j++){
                if(rowIndex+j+1 < Consts.boardHeight){
                    for (int i = 0; i < Consts.boardWidth; i++){
                        table[rowIndex+j][i] = table[rowIndex+1+j][i];
                        // System.out.println(" row Copied: " + (rowIndex+j));
                        counter++;
                    }
                }
                // else{
                //     System.out.println("ERROR too big rows tried to be accessed");
                //     counter++;
                //     break;
                // }
            }
            // System.out.println(" counter: " + counter);
        // }else{
        //     //TODO: lost screen
        // }
        return counter;
    }

    public void printTable(){

        // for(int i = 0; i < Consts.boardHeight; i++){
        //     for(int j = 0; j < Consts.boardWidth; j++){
        //     System.out.println(table[i][j]);
        //     }
        // }
        System.out.println(table.length);
        for (int i = 0; i < Consts.boardWidth; i++){
            System.out.println(table[0][i]);

        }

    }
}
