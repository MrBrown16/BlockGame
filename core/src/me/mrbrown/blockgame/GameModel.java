package me.mrbrown.blockgame;

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
            System.out.println("occupied x : " + x + " y: " + y);
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
        System.out.println("checkBellow");
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
        boolean tmpempty = true; // true = all -1 no need to propagate
        boolean tmpfull = true; // starts with true but one empty cell sets it to false
        for (int i = startRowIndex; i < Consts.boardHeight; i++){
            tmpfull = true;
            tmpempty = true;
            
            for (int j = 0; j < Consts.boardWidth; j++){
                if (table[i][j] == -1){
                    tmpfull = false;//should do nothing
                    // System.out.println("==-1 i: " + i + " j: " + j);
                }else if (table[i][j] > -1){
                    tmpempty = false;
                    // tmpfull = true; //copy higher i rows to one lower starting i+1 -> i 
                }
                System.out.println(" i: " + i + " j: " + j);
            }
            if (tmpfull) {
                // for (int k = i; k < Consts.boardHeight-1; k++){
                //     System.out.println("not-1 i: " + i + " k: " + k);
                //     System.out.println(k);
                //     copyRows(k);
                // }
                System.out.println("tmpfull: " + tmpfull);
                copyRows(i);
                checkFinRows(i);
            }
            if (tmpempty) {
                System.out.println("empty: " + tmpempty);
                break;
                
            }
        }
    }

    private void copyRows(int rowIndex){
        System.out.println(rowIndex);
        if (rowIndex < Consts.boardHeight) {
            for (int j = 0; j < Consts.boardHeight-rowIndex-1; j++){
                for (int i = 0; i < Consts.boardWidth; i++){
                    table[rowIndex+j][i] = table[rowIndex+1+j][i];
                }
            }
        }else{
            //TODO: lost screen
        }
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
