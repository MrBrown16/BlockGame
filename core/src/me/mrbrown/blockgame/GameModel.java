package me.mrbrown.blockgame;

public class GameModel {
    
    //table contains the table and all the blocks are represented by ints -1 = empty space

    int[][] table = new int[Consts.boardHeight][Consts.boardWidth];

    
    public GameModel() {
        setupArray();
        // printTable();
    }
    
    public void setupArray(){
        for(int i = 0; i < Consts.boardHeight; i++){
            for(int j = 0; j < Consts.boardWidth; j++){
                // System.out.println(i + " : " + j);
                table[i][j] = -1;
            }
        }
    }
    public void addBlock(int row, int cell, int block){
        table[row][cell] = block;
    }
    
    public boolean checkBellow(int x, int y){ // checks if the space bellow is occupied, returns false if empty
        
        if (y <= 0) {
            // System.out.println("y=0");
            //TODO: spawn new shape
            return true;
        }
        if (y >= Consts.boardHeight){ //not jet on the board so it can fall
            // System.out.println("y bigger than boardHeight");
            return false;
        }
        if ((y < Consts.boardHeight) && (y >= 0)) {
            // System.out.println(x + " : " + y);
            // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
            if (table[y-1][x] == -1) { //the space bellow contains -1 (empty space) 
                // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
                // System.out.println("contains -1");
                return false;
            }else{
                // System.out.println("doesn't contain -1");
                return true;
            }
        }
        
        // System.out.println("default");
        return true;
    }
    public boolean checkIn(int x, int y){ // checks if the space in shape is occupied, returns false if empty
        
        if ((y < 0) || (x < 0) || (x > Consts.boardWidth-1)) {
            // System.out.println(" checkIn y<0 or x<0 or x>bw");
            return true;
        }
        if (y >= Consts.boardHeight){ //not jet on the board so it can fall
            // System.out.println("y bigger than boardHeight");
            return false;
        }
        if ((y < Consts.boardHeight) && (y >= 0)) {
            // System.out.println(x + " : " + y);
            // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
            if (table[y][x] == -1) { //the space bellow contains -1 (empty space) 
                // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
                // System.out.println("contains -1");
                return false;
            }else{
                // System.out.println("doesn't contain -1");
                return true;
            }
        }
        
        // System.out.println("default");
        return true;
    }
    public boolean checkLeft(int x, int y){ 
        // System.out.println(" checkLeft x: " + x);
        // if (x < Consts.boardWidth) {
        //     System.out.println(" checkLeft x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" checkLeft x<bw");
        // }        
        // if (x > Consts.boardWidth) {
        //     System.out.println(" checkLeft x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" checkLeft x>bw");
        // }        
        // if (x == Consts.boardWidth) {
        //     System.out.println(" x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" checkLeft x==bw");
        // }  

        if (y >= Consts.boardHeight){ //not jet on the board so it can fall
            // System.out.println(" checkLeft y bigger than boardHeight");
            return false;
        }
        if ((x < Consts.boardWidth) && (x > 0)) {
            // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
            if (table[y][x-1] == -1) { //the space left contains -1 (empty space) 
                // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
                // System.out.println(" checkLeft contains -1");
                return false;
            }else{
                // System.out.println(" checkLeft doesn't contain -1");
                return true;
            }
        }
        
        // System.out.println(" checkLeft default");
        return true;
    }
    public boolean checkRight(int x, int y){ // checks if the space bellow is occupied, returns false if empty
        // System.out.println(" x: " + x + " CheckRight");
        // if (x < Consts.boardWidth) {
        //     System.out.println(" CheckRight x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" CheckRight x<bw");
        // }        
        // if (x > Consts.boardWidth) {
        //     System.out.println(" CheckRight x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" CheckRight x>bw");
        // }        
        // if (x == Consts.boardWidth) {
        //     System.out.println(" CheckRight x: " + x + " boardWidth: " + Consts.boardWidth);
        //     System.out.println(" CheckRight x==bw");
        // }        

        if (x >= Consts.boardWidth-1){ //on the right
            // System.out.println("this doesn't seem to work");
            return true;
        }
        if (y >= Consts.boardHeight){ //not jet on the board so it can fall
            // System.out.println(" CheckRight y bigger than boardHeight");
            // return false;
            y = Consts.boardHeight-1;
        }
        if ((x <= Consts.boardWidth-1) && (x >= 0)) {
            // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
            if (table[y][x+1] == -1) { //the space left contains -1 (empty space) 
                // System.out.println("Table: " + table[y][x] + " : " + x + " : " + y);
                // System.out.println(" CheckRight contains -1");
                return false;
            }else{
                // System.out.println(" CheckRight doesn't contain -1");
                return true;
            }
        }
        
        // System.out.println(" CheckRight default");
        return true;
    }
    
    public int getTableCell(int x, int y) {
        return table[y][x];
    }

    public void printTable(){

        for(int i = 0; i < Consts.boardHeight; i++){
            for(int j = 0; j < Consts.boardWidth; j++){
            System.out.println(table[i][j]);
            }
        }
        System.out.println(table.length);
        System.out.println(table[22][10]);

    }
}
