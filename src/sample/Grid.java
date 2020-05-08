package sample;

import java.io.Serializable;

public class Grid implements Serializable {
    final int MAX_GRID_SIZE = 10;
    public Piece[][] grid = new Piece[MAX_GRID_SIZE][MAX_GRID_SIZE];


    void addPiece(int x, int y,Piece piece){
        grid[x][y] = piece;
    }
    void removePiece(int x, int y){
        grid[x][y] = null;
    }


    @Override
    public String toString(){
        String s ="";
        for(int y =0;y<this.MAX_GRID_SIZE;y++){
            for(int x =0;x<this.MAX_GRID_SIZE;x++){
                if(this.grid[x][y] != null){
                    s += this.grid[x][y].toString();
                }else{
                    s+= "-";
                }
            }
            s+= '\n';
        }
        return s;
    }
}
