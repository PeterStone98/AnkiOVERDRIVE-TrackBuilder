package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Track implements Serializable {
    ArrayList<Piece> pieces = new ArrayList<>();
    String name;
    Grid[] map = new Grid[2];

    void addPiece(int layer, int x, int y,Piece piece){
        this.pieces.add(piece);
        piece.layer = layer;
        piece.setCoordinates(x,y);
        map[layer].addPiece(x, y, piece);
    }
    void addPiece(Piece p){
        this.pieces.add(p);

    }


    void removePiece(int layer, int x, int y,Piece p){
        this.pieces.remove(p);
        map[layer].removePiece(x, y);
    }

    public Track(){
        Grid g1 = new Grid();
        map[0] = g1;
        Grid g2 = new Grid();
        map[1] = g2;
    }

    public int numOfPieces(int type){
        int num = 0;
        for(Piece p: this.pieces){
            if(p.type == type){
                num++;
            }
        }
        return num;
    }



    void center(){
        int maxX = 0;
        int maxY = 0;
        for(Piece p: this.pieces){
            if(p.xValue>maxX){
                maxX = p.xValue;
            }
            if(p.yValue>maxY){
                maxY = p.yValue;
            }
        }
        int xOffsset = (10-maxX)/2;
        int yOffset = (8-maxY)/2;
        Grid[] map2 = new Grid[2];
        Grid g1 = new Grid();
        map2[0] = g1;
        Grid g2 = new Grid();
        map2[1] = g2;
        for(Piece p:this.pieces){
            p.xValue+= xOffsset;
            p.yValue += yOffset;
            map2[p.layer].grid[p.xValue][p.yValue] = p;
        }
        this.map = map2;
    }

    @Override
    public String toString(){
        return "Layer1:" +'\n'+map[0].toString() + "Layer2:" +'\n'+map[1].toString();
    }
}
