package sample;

import java.io.Serializable;

public class Piece implements Serializable {
    public int type = 0;
    //type 0 is an intersection
    //type 1 is a straight piece
    //type 2 is a turn
    //type 3 is a jump
    //type 4 is a fastzone(same as straight just different image)
    //type 5 is a start
    //should goes up be a int that we change or its own piece
    public int direction = 0;
    //direction stright/speed/start pieces: 0,2 is horizontal 1,3 is vertical
    //direction for jump: 0 horizonal with ladning to right
    //                    1 veritcal with landing above
    //                    2 horizontal with landing to left
    //                    3 veritcal with landing below
    //direction for curves:0 connection on bottom and left
    //                     1 connection on bottom and right
    //                     2 connection on top and right
    //                     3 connection on top and left
    //direction for intersection:doesn't matter
    int xValue;
    int yValue;
    int layer = 0;
    String name = "";

    public Piece() {
    }

    public Piece(int type) {
        //Changes what piece it is
        this.type = type;
    }


    public void rotate(int x, int type) {
        //if statement is essentially a mod function
        direction = direction + x;
        if (direction >= 4) {
            direction = 0;
        }
        if (direction < 0) {
            direction = 3;
        }
    }


    public void setCoordinates(int x, int y) {
        this.xValue = x;
        this.yValue = y;
    }

    @Override
    public String toString() {
        return Integer.toString(this.type);//"type:" + this.type + " direction:" + this.direction;
    }



}
