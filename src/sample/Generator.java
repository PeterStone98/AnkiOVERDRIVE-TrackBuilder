
package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generator {

    ArrayList<Track> generate(int numIntersection, int numStraight, int numCurves, int numJump, int numFast) throws IOException {
        ArrayList<Track> allTracks = new ArrayList<>();
        boolean working = true;

        if (numCurves % 2 != 0) {
            numCurves -= 1;
        }

        while (working) {
            if (numIntersection * 6 <= numStraight && numIntersection > 0) {
                numIntersection -= 1;
            } else {
                working = false;
            }
        }

        int[] numPieces = {numIntersection, numStraight, numCurves, numJump, numFast};
        int sum = Arrays.stream(numPieces).sum();
        int[] totalPieces = getTotal(numPieces);
        List<int[]> combinations = new ArrayList<>();
        if (sum <= 9) {
            //get every good ordering of pieces
            combine(combinations, totalPieces, 0);
        } else {
            //always start with curve(2)
            if(numIntersection ==0 && numJump ==0) {
                for (int i = 2; i <= 8; i++) {
                    for (int j = 2; j <= 8;j++) {
                        int cIndex = numCurves / i;
                        int sIndex = (numStraight + numFast + 1) / j;
                        int added = 0;
                        int[] combo = new int[sum + 1];
                        while (added < sum) {
                            for (int k = 0; k < cIndex; k++) {
                                if (added == sum) {
                                    break;
                                }
                                combo[added] = 2;
                                added++;
                            }
                            for (int k = 0; k < sIndex; k++) {
                                if (added == sum) {
                                    break;
                                }
                                combo[added] = 1;
                                added++;
                            }
                        }
                        boolean noStart = true;
                        int tempFast = numFast;

                        for(int g=0;g<combo.length;g++){
                            if (combo[g] == 1 && noStart) {
                                combo[g] = 5;
                                noStart = false;
                                g++;
                            } else if (combo[g] == 1) {
                                combo[g] = 4;
                                g++;
                                tempFast--;
                            }
                            if(tempFast==0){
                                break;
                            }
                        }
                        combinations.add(combo.clone());
                    }
                }
            }else if(numIntersection ==0){
                //include jump
                for (int i = 2; i <= 8; i++) {
                    for (int j = 2; j <= 8; j++) {
                        int cIndex = numCurves / i;
                        int sIndex = (numStraight + numFast + 1) / j;
                        int jIndex = numJump/j;
                        int added = 0;
                        int[] combo = new int[sum + 1];
                        int tempJ = 0;
                        while (added < sum) {
                            for (int k = 0; k < cIndex; k++) {
                                if (added == sum) {
                                    break;
                                }
                                combo[added] = 2;
                                added++;
                            }
                            for (int k = 0; k < sIndex; k++) {
                                if (added == sum) {
                                    break;
                                }
                                combo[added] = 1;
                                added++;
                            }
                            for (int k = 0; k < jIndex; k++) {
                                if (added == sum||tempJ == numJump) {
                                    break;
                                }
                                tempJ++;
                                combo[added] = 3;
                                added++;
                            }
                        }
                        boolean noStart = true;
                        int tempFast = numFast;

                        for(int g=0;g<combo.length;g++){
                            if (combo[g] == 1 && noStart) {
                                combo[g] = 5;
                                noStart = false;
                                g++;
                            } else if (combo[g] == 1) {
                                combo[g] = 4;
                                g++;
                                tempFast--;
                            }
                            if(tempFast==0){
                                break;
                            }
                        }
                        combinations.add(combo.clone());
                    }
                }
            }
        }
        //use track orderings to place pieces
        int[] layout = generateLayoutSize(sum);
        for (int[] n : combinations) {
            ArrayList<Piece> pieces = makePieces(n);
            allTracks.addAll(fillLayouts(layout, pieces));
        }
        return allTracks;
    }

    public ArrayList<Piece> makePieces(int[] quantity) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int num = 0; num < quantity.length; num++) {
            Piece p = new Piece(quantity[num]);
            pieces.add(p);
            p.name = Integer.toString(num);
        }
        return pieces;
    }

    int[] getTotal(int[] p) {
        List<Integer> total = new ArrayList<>();
        total.add(5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < p[i]; j++) {
                total.add(i);
            }
        }
        int[] pieces = total.stream().mapToInt(i -> i).toArray();
        return pieces;
    }

    void combine(List<int[]> combinations, int[] a, int k) {
        if (k == a.length) {
            int[] data = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                data[i] = a[i];
            }
            int[] combination = data;//.clone();
            if (unique(combination, combinations)) {
                combinations.add(combination);
            }
        } else {
            for (int i = k; i < a.length; i++) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;

                combine(combinations, a, k + 1);

                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }

    boolean unique(int[] test, List<int[]> all) {
        boolean uniqu = true;
        if (test[0] != 2) {
            return false;
        }
        for (int[] nums : all) {
            if (Arrays.equals(nums, test)) {
                uniqu = false;
            }
        }
        return uniqu;
    }

    int[] generateLayoutSize(int pieces) {
        int maxX = 0;
        int maxY = 0;

        boolean keepGoing = true;
        while (keepGoing) {
            if (maxX * maxX < pieces) {
                maxY += 1;
            }
            if (maxX * maxX < pieces) {
                maxX += 1;
            }
            if (maxX * maxX >= pieces) {
                keepGoing = false;
                break;
            }
        }


        int[] boundaries = {maxX, maxX};
        return boundaries;
    }


    ArrayList<Track> fillLayouts(int[] layout, ArrayList<Piece> pieces) {
        ArrayList<Track> allTracks = new ArrayList<>();
        //create eligible paths
        int layer = 0;
        Track newTrack = new Track();
        Piece startingCurve = pieces.get(0);
        startingCurve.rotate(1, startingCurve.type);
        newTrack.addPiece(0, 0, 0, startingCurve);
        pieces.remove(startingCurve);
        int[] coordinates = {0, 0};
        getNextSpot(startingCurve, coordinates, "right");
        makeTrack(allTracks, newTrack, pieces, layout, coordinates, "right", 0);
        return allTracks;
    }


    void makeTrack(ArrayList<Track> tracks, Track track, ArrayList<Piece> pieces, int[] layout, int[] coordinates, String pointer, int piecesIndex) {
        int x = coordinates[0];
        int y = coordinates[1];
        String mainPointer = pointer;
        //if complete track or on last piece
        if (coordinates[0] == 0 && coordinates[1] == 0 || (pieces.size() == piecesIndex)) {
            if (coordinates[0] == 0 && coordinates[1] == 0 && track.pieces.size() >= 6) {
                Track copy = new Track();
                copyTrack(track, copy);
                if (notIn(tracks, copy)) {
                    tracks.add(copy);
                }
            }
            return;
        } else {
            if (x < layout[0] && x >= 0 && y < layout[1] && y >= 0) {
                if (track.map[0].grid[x][y] == null) {
                    Piece newPiece = pieces.get(piecesIndex);
                    for (int i = 0; i < 4; i++) {
                        if (checkRotation(newPiece, mainPointer)) {
                            track.addPiece(0, x, y, newPiece);
                            String newPointer = updatePointer(newPiece, pointer);
                            piecesIndex += 1;
                            getNextSpot(newPiece, coordinates, newPointer);
                            makeTrack(tracks, track, pieces, layout, coordinates, newPointer, piecesIndex);
                            piecesIndex -= 1;
                            track.map[0].grid[x][y] = null;
                            track.removePiece(0, x, y, newPiece);
                        }
                        coordinates = new int[]{x, y};
                        newPiece.rotate(1, newPiece.type);
                    }
                } else if (false&&track.map[1].grid[x][y] == null) {
                    Piece newPiece = pieces.get(piecesIndex);
                    for (int i = 0; i < 4; i++) {
                        if (checkRotation(newPiece, mainPointer)) {
                            track.addPiece(1, x, y, newPiece);
                            String newPointer = updatePointer(newPiece, pointer);
                            piecesIndex += 1;
                            getNextSpot(newPiece, coordinates, newPointer);
                            makeTrack(tracks, track, pieces, layout, coordinates, newPointer, piecesIndex);
                            piecesIndex -= 1;
                            track.map[1].grid[x][y] = null;
                            track.removePiece(1, x, y, newPiece);
                        }
                        coordinates = new int[]{x, y};
                        newPiece.rotate(1, newPiece.type);
                    }
                }
            }
        }
    }


    void getNextSpot(Piece p, int[] coordinates, String pointer) {
        //straight/start/speed/intersection
        if (p.type != 3) {
            if (pointer.equals("right")) {
                coordinates[0] += 1;
            } else if (pointer.equals("left")) {
                coordinates[0] -= 1;
            } else if (pointer.equals("up")) {
                coordinates[1] -= 1;
            } else if (pointer.equals("down")) {
                coordinates[1] += 1;
            }
        }
        //jump
        if (p.type == 3) {
            if (pointer.equals("right")) {
                coordinates[0] += 2;
            } else if (pointer.equals("left")) {
                coordinates[0] -= 2;
            } else if (pointer.equals("up")) {
                coordinates[1] -= 2;
            } else if (pointer.equals("down")) {
                coordinates[1] += 2;
            }
        }
    }

    boolean checkRotation(Piece p, String pointer) {
        if (p.type != 2) {
            if (pointer.equals("right")) {
                if (p.direction == 0 || p.direction == 2) {
                    return true;
                }
            } else if (pointer.equals("left")) {
                if (p.direction == 0 || p.direction == 2) {
                    return true;
                }
            } else if (pointer.equals("up")) {
                if (p.direction == 1 || p.direction == 3) {
                    return true;
                }
            } else if (pointer.equals("down")) {
                if (p.direction == 1 || p.direction == 3) {
                    return true;
                }
            }
        }
        //curve
        if (p.type == 2) {
            if (pointer.equals("right")) {
                if (p.direction == 0 || p.direction == 3) {
                    return true;
                }
            } else if (pointer.equals("left")) {
                if (p.direction == 1 || p.direction == 2) {
                    return true;
                }
            } else if (pointer.equals("up")) {
                if (p.direction == 1 || p.direction == 0) {
                    return true;
                }
            } else if (pointer.equals("down")) {
                if (p.direction == 2 || p.direction == 3) {
                    return true;
                }
            }
        }
        return false;

    }

    String updatePointer(Piece p, String pointer) {
        if (p.type != 2) {
            if (p.direction == 0 || p.direction == 2) {
                if (pointer == "right" || pointer == "left") {
                }
            } else if (p.direction == 1 || p.direction == 3) {
                if (pointer == "up" || pointer == "down") {
                }
            }
        }
        //curve
        if (p.type == 2) {
            if (pointer.equals("right")) {
                if (p.direction == 0) {
                    pointer = "down";
                } else if (p.direction == 3) {
                    pointer = "up";
                }
            } else if (pointer.equals("left")) {
                if (p.direction == 1) {
                    pointer = "down";
                } else if (p.direction == 2) {
                    pointer = "up";
                }
            } else if (pointer.equals("up")) {
                if (p.direction == 1) {
                    pointer = "right";
                } else if (p.direction == 0) {
                    pointer = "left";
                }
            } else if (pointer.equals("down")) {
                if (p.direction == 2) {
                    pointer = "right";
                } else if (p.direction == 3) {
                    pointer = "left";
                }
            }
        }
        return pointer;
    }

    void copyTrack(Track oldTrack, Track copy) {
        boolean hasStart = false;
        int checkpoint = 0;
        int i=0;
        for (Piece p : oldTrack.pieces) {
            Piece newPiece = new Piece(p.type);
            int direction = p.direction;
            int x = p.xValue;
            int y = p.yValue;
            int layer = p.layer;
            newPiece.direction = direction;
            newPiece.xValue = x;
            newPiece.yValue = y;
            newPiece.layer = layer;
            copy.addPiece(newPiece.layer, newPiece.xValue, newPiece.yValue, newPiece);
            if(p.type==5){
                hasStart= true;
            }else if (p.type == 1 && !hasStart){
                checkpoint = i;
            }
            i++;
        }
        if(!hasStart){
            Piece oldPiece = copy.pieces.get(checkpoint);
            Piece start = new Piece(5);
            int direction = oldPiece.direction;
            int x = oldPiece.xValue;
            int y = oldPiece.yValue;
            int layer = oldPiece.layer;
            start.direction = direction;
            start.xValue = x;
            start.yValue = y;
            start.layer = layer;
            copy.removePiece(oldPiece.layer,oldPiece.xValue,oldPiece.yValue,oldPiece);
            copy.addPiece(start.layer, start.xValue, start.yValue, start);
        }
        copy.center();

    }

    private boolean notIn(ArrayList<Track> tracks, Track copy) {
        boolean result = false;
        if (tracks.isEmpty()) {
            return true;
        }
        for (Track t : tracks) {
            int index = 0;
            if (t.pieces.size() == copy.pieces.size()) {
                for (Piece p : t.pieces) {
                    if (p.type != copy.pieces.get(index).type) {
                        result = true;
                    }
                    index += 1;
                }

            } else {
                result = true;
            }
        }
        return result;
    }
}
