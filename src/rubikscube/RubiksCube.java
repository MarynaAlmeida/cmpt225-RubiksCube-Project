package rubikscube;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RubiksCube implements Comparator<RubiksCube>{

    private final char[][] currentState = new char[9][];
    private static final String TARGET =
                    "   OOO\n" +
                    "   OOO\n" +
                    "   OOO\n" +
                    "GGGWWWBBBYYY\n" +
                    "GGGWWWBBBYYY\n" +
                    "GGGWWWBBBYYY\n" +
                    "   RRR\n" +
                    "   RRR\n" +
                    "   RRR\n";

    public RubiksCube() {
        currentState[0] = new char[]{' ', ' ', ' ', 'O', 'O', 'O'};
        currentState[1] = new char[]{' ', ' ', ' ', 'O', 'O', 'O'};
        currentState[2] = new char[]{' ', ' ', ' ', 'O', 'O', 'O'};
        currentState[3] = new char[]{'G','G','G','W','W','W','B','B','B','Y','Y','Y'};
        currentState[4] = new char[]{'G','G','G','W','W','W','B','B','B','Y','Y','Y'};
        currentState[5] = new char[]{'G','G','G','W','W','W','B','B','B','Y','Y','Y'};
        currentState[6] = new char[]{' ', ' ', ' ', 'R', 'R', 'R'};
        currentState[7] = new char[]{' ', ' ', ' ', 'R', 'R', 'R'};
        currentState[8] = new char[]{' ', ' ', ' ', 'R', 'R', 'R'};
    }

    public RubiksCube(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        int rowCounter = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            currentState[rowCounter] = line.toCharArray();
            rowCounter++;
        }
        reader.close();
    }

    // Front movement method
    private void F(){
        char[] left, right, up, down, leftcol, rightcol, top, bottom;
        rightcol = new char[]{currentState[3][5], currentState[4][5], currentState[5][5]};
        leftcol = new char[]{currentState[3][3], currentState[4][3], currentState[5][3]};
        top = new char[]{currentState[3][3], currentState[3][4], currentState[3][5]};
        bottom = new char[]{currentState[5][3], currentState[5][4], currentState[5][5]};

        left = new char[]{currentState[3][2], currentState[4][2], currentState[5][2]};
        right = new char[]{currentState[3][6], currentState[4][6], currentState[5][6]};
        up = new char[]{currentState[2][3], currentState[2][4], currentState[2][5]};
        down = new char[]{currentState[6][3], currentState[6][4], currentState[6][5]};

        currentState[2][3] = left[2]; currentState[2][4] = left[1]; currentState[2][5] = left[0];
        currentState[3][6] = up[0]; currentState[4][6]= up[1]; currentState[5][6] = up[0];
        currentState[6][5] = right[0]; currentState[6][4] = right[1];  currentState[6][3] = right[0];
        currentState[3][2] = down[0]; currentState[4][2] = down[1]; currentState[5][2] = down[2];

        currentState[3][3] = leftcol[2]; currentState[3][4] = leftcol[1];
        currentState[3][5] = top[0]; currentState[4][5] = top[1];
        currentState[5][3] = rightcol[2]; currentState[5][4] = rightcol[1];   currentState[5][5] = rightcol[0];
        currentState[3][3] = bottom[0]; currentState[4][3] = bottom[1]; currentState[5][3] = bottom[2];

    }

    // Back movement method
    private void B(){
        char[] left, right, up, down, leftcol, rightcol, top, bottom;

        left = new char[]{currentState[3][0], currentState[4][0], currentState[5][0]};
        right = new char[]{currentState[3][8],  currentState[4][8], currentState[5][8]};
        up = new char[]{currentState[0][3], currentState[0][4], currentState[0][5]};
        down = new char[]{currentState[8][3], currentState[8][4], currentState[8][5]};

        leftcol = new char[]{currentState[3][9], currentState[4][9], currentState[5][9]};
        rightcol = new char[]{currentState[3][11], currentState[4][11], currentState[5][11]};
        top = new char[]{currentState[3][9], currentState[3][10], currentState[3][11]};
        bottom = new char[]{currentState[5][9], currentState[5][10], currentState[5][11]};

        currentState[3][8] = down[2]; currentState[4][8] = down[1]; currentState[5][8] = down[0];
        currentState[8][3] = left[0]; currentState[8][4] = left[1];  currentState[8][5] = left[2];
        currentState[3][0] = up[0]; currentState[4][0] = up[1]; currentState[5][0] = up[2];

        currentState[0][3] = right[0]; currentState[0][4] = right[1]; currentState[0][5] = right[2];

        currentState[3][9] = leftcol[2]; currentState[3][10] = leftcol[1];
        currentState[3][11] = top[0]; currentState[4][11] = top[1];
        currentState[5][9] = rightcol[2]; currentState[5][10] = rightcol[1]; currentState[5][11] = rightcol[0];
        currentState[3][9] = bottom[0]; currentState[4][9] = bottom[1]; currentState[5][9] = bottom[2];
    }

    // Right movement method
    private void R(){
        char[] front, back, up, down;
        char [] leftcol, rightcol, top, bottom;

        front = new char[]{currentState[3][5], currentState[4][5], currentState[5][5]};
        back = new char[]{currentState[3][9], currentState[4][9], currentState[5][9]};
        up = new char[]{currentState[0][5], currentState[1][5], currentState[2][5]};
        down = new char[]{currentState[6][5], currentState[7][5], currentState[8][5]};

        leftcol = new char[]{currentState[3][6], currentState[4][6], currentState[5][6]};
        rightcol = new char[]{currentState[3][8], currentState[4][8], currentState[5][8]};
        top = new char[]{currentState[3][6], currentState[3][7], currentState[3][8]};
        bottom = new char[]{currentState[5][6], currentState[5][7], currentState[5][8]};

        currentState[3][9] = up[2]; currentState[4][9] = up[1]; currentState[5][9] = up[0];
        currentState[6][5] = back[2]; currentState[7][5] = back[1];  currentState[8][5] = back[0];
        currentState[3][5] = down[0]; currentState[4][5] = down[1]; currentState[5][5] = down[2];
        currentState[0][5] = front[0]; currentState[1][5] = front[1]; currentState[2][5] = front[2];

        currentState[3][7] = leftcol[1]; currentState[3][6] = leftcol[2];
        currentState[3][8] = top[0]; currentState[4][8] = top[1];
        currentState[5][6] = rightcol[2]; currentState[5][7] = rightcol[1]; currentState[5][8] = rightcol[0];
        currentState[3][6] = bottom[0]; currentState[4][6] = bottom[1]; currentState[5][6] = bottom[2];
    }

    // Left movement method
    private void L(){
        char[] front, back, up, down, leftcol, rightcol, top, bottom;

        front =  new char[]{currentState[3][3], currentState[4][3], currentState[5][3]};
        back = new char[]{currentState[3][11], currentState[4][11], currentState[5][11]};
        up = new char[]{currentState[0][3], currentState[1][3], currentState[2][3]};
        down = new char[]{currentState[6][3], currentState[7][3], currentState[8][3]};

        leftcol = new char[]{currentState[3][0], currentState[4][0], currentState[5][0]};
        rightcol = new char[]{currentState[3][2], currentState[4][2], currentState[5][2]};
        top = new char[]{currentState[3][0], currentState[3][1], currentState[3][2]};
        bottom = new char[]{currentState[5][0], currentState[5][1], currentState[5][2]};

        currentState[3][3] = up[0]; currentState[4][3] = up[1]; currentState[5][3] = up[2];
        currentState[6][3] = front[0]; currentState[7][3] = front[1]; currentState[8][3] = front[2];
        currentState[3][11] = down[2]; currentState[4][11] = down[1]; currentState[5][11] = down[2];
        currentState[0][3] = back[2]; currentState[1][3] = back[1]; currentState[2][3] = back[0];

        currentState[3][0] = leftcol[2]; currentState[3][1] = leftcol[1];
        currentState[3][2] = top[0]; currentState[4][2] = top[1];
        currentState[5][0] = rightcol[2]; currentState[5][1] = rightcol[1];  currentState[5][2] = rightcol[0];
        currentState[3][0] = bottom[0]; currentState[4][0] = bottom[1];  currentState[5][0] = bottom[2];

    }

    // Up movement method
    private void U(){
        char[] front, left, right, back, rightcol, leftcol, top, bottom;

        front = new char[]{currentState[3][3], currentState[3][4], currentState[3][5]};
        left = new char[]{currentState[3][0], currentState[3][1], currentState[3][2]};
        right = new char[]{currentState[3][6], currentState[3][7], currentState[3][8]};
        back = new char[]{currentState[3][9], currentState[3][10], currentState[3][11]};

        leftcol = new char[]{currentState[0][3], currentState[1][3], currentState[2][3]};
        rightcol = new char[]{currentState[0][5], currentState[1][5], currentState[2][5]};
        top = new char[]{currentState[0][3], currentState[0][4], currentState[0][5]};
        bottom = new char[]{currentState[2][3], currentState[2][4], currentState[2][5]};

        currentState[3][0] = front[0]; currentState[3][1] = front[1]; currentState[3][2] = front[2];
        currentState[3][9] = left[0];  currentState[3][10] = left[1]; currentState[3][11] = left[2];
        currentState[3][6] = back[0];  currentState[3][7] = back[1]; currentState[3][8] = back[2];
        currentState[3][3] = right[0]; currentState[3][4] = right[1]; currentState[3][5] = right[2];

        currentState[0][3] = leftcol[2]; currentState[0][4] = leftcol[1];
        currentState[0][5] = top[0]; currentState[1][5] = top[1];
        currentState[2][3] = rightcol[2]; currentState[2][4] = rightcol[1];  currentState[2][5] = rightcol[0];
        currentState[0][3] = bottom[0]; currentState[1][3] = bottom[1]; currentState[2][3] = bottom[2];
    }

    // Down movement method
    private void D(){
        char[] left, right, front, back, rightcol, leftcol, top, bottom;
        left = new char[]{currentState[5][0], currentState[5][1], currentState[5][2]};
        right = new char[]{currentState[5][6], currentState[5][7], currentState[5][8]};
        front = new char[]{currentState[5][3], currentState[5][4], currentState[5][5]};
        back = new char[]{currentState[5][9], currentState[5][10], currentState[5][11]};

        leftcol = new char[]{currentState[6][3], currentState[7][3], currentState[8][3]};
        rightcol = new char[]{currentState[6][5],  currentState[7][5], currentState[8][5]};
        top = new char[]{currentState[6][3], currentState[6][4], currentState[6][5]};
        bottom = new char[]{currentState[8][3], currentState[8][4], currentState[8][5]};

        currentState[5][6] = front[0]; currentState[5][7] = front[1]; currentState[5][8] = front[2];
        currentState[5][9] = right[0]; currentState[5][10] = right[1];  currentState[5][11] = right[2];
        currentState[5][0] = back[0]; currentState[5][1] = back[1]; currentState[5][2] = back[2];
        currentState[5][3] = left[0]; currentState[5][4] = left[1]; currentState[5][5] = left[2];

        currentState[6][3] = leftcol[2]; currentState[6][4] = leftcol[1];
        currentState[6][5] = top[0]; currentState[7][5] = top[1];
        currentState[8][3] = rightcol[2]; currentState[8][4] = rightcol[1];  currentState[8][5] = rightcol[0];
        currentState[6][3] = bottom[0]; currentState[7][3] = bottom[1]; currentState[8][3] = bottom[2];
    }
    public boolean isSolved() {
        return this.toString().equals(TARGET);
    }

    @Override
    public String toString() {
        // information for converting from char to string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentState.length; i++) {
            sb.append(currentState[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubiksCube)) return false;
        RubiksCube other = (RubiksCube) o;

        for (int r = 0; r < currentState.length; r++) {
            if (!Arrays.equals(this.currentState[r], other.currentState[r])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int r = 0; r < currentState.length; r++) {
            result = 31 * result + Arrays.hashCode(currentState[r]);
        }
        return result;
    }

    // heuristic calculates how many unmatched cubes from the target cube
    public int heuristic(RubiksCube target) {
        int unmatched = 0;
        int c, r;

        for (r = 0; r <= 2; r++) {
            for (c = 0; c <= 5; c++) {
                if (this.currentState[r][c] != target.currentState[r][c]) {
                    unmatched++;
                }
            }
        }
        for (r = 3; r <= 5; r++) {
            for (c = 0; c <= 11; c++) {
                if (this.currentState[r][c] != target.currentState[r][c]) {
                    unmatched++;
                }
            }
        }
        for (r = 6; r <= 8; r++) {
            for (c = 0; c <= 5; c++) {
                if (this.currentState[r][c] != target.currentState[r][c]) {
                    unmatched++;
                }
            }
        }

        return unmatched;
    }

    @Override
    public RubiksCube clone(){
        // when copy is created it starts from the target cube
        RubiksCube copy = new RubiksCube();
        // for loop changes the copy to match the currentState cube we're on
        for( int i = 0; i < currentState.length; i++){
            copy.currentState[i] = String.valueOf(currentState[i]).toCharArray();
        }
        return copy;
    }

    int h, g, f;
    RubiksCube parent, child;

    @Override
    public int compare(RubiksCube cube1, RubiksCube cube2) {
        int f1 = cube1.f;
        int f2 = cube2.f;
        Integer f1Compare = f1;
        Integer f2Compare = f2;
        return f1Compare.compareTo(f2Compare);
    }

    public char[] aStarAlgorithm() {
        RubiksCube meetState = null;
        RubiksCube start = this.clone();
        RubiksCube solved = new RubiksCube();

        // forward search initialization
        this.h = this.heuristic(solved);
        this.g = 0;
        this.f = this.g + this.h;
        int moveCost = 1;

        PriorityQueue<RubiksCube> discoveredStates = new PriorityQueue<>(this);      // open set
        HashSet<RubiksCube> visitedStates = new HashSet<>();                                   // closed set
        HashMap<RubiksCube, RubiksCube> parent = new HashMap<>();                              // to retrace our steps
        HashMap<RubiksCube, Integer> movesUsed = new HashMap<>();
        HashMap<RubiksCube, Integer> bestG = new HashMap<>();
        bestG.put(this, 0);
        discoveredStates.add(this);

        // backward search initialization
        solved.g = 0;
        solved.h = solved.heuristic(start);
        solved.f = solved.g + solved.h;

        PriorityQueue<RubiksCube> discoveredStates2 = new PriorityQueue<>(this);      // open set
        HashSet<RubiksCube> visitedStates2 = new HashSet<>();                                   // closed set
        HashMap<RubiksCube, RubiksCube> parent2 = new HashMap<>();                              // to retrace our steps
        HashMap<RubiksCube, Integer> movesUsed2 = new HashMap<>();
        HashMap<RubiksCube, Integer> bestG2 = new HashMap<>();
        bestG2.put(solved, 0);
        discoveredStates2.add(solved);
        parent2.put(solved, null);

        // search loop
        while (!discoveredStates.isEmpty() && !discoveredStates2.isEmpty()) {

            // going forwards
            RubiksCube currentState = discoveredStates.poll();
            if (currentState != null && !visitedStates.contains(currentState)) {
                visitedStates.add(currentState);

                // check if we found a common state (meeting point)
                if (visitedStates2.contains(currentState)) {
                    meetState = currentState;
                    return printPath(meetState, parent, movesUsed, parent2, movesUsed2);
                }

                // making move forward
                for (int i = 1; i <= 6; i++) {
                    child = currentState.clone();

                    switch (i) {
                        case 1: child.F(); break;   // Front
                        case 2: child.B(); break;   // Back
                        case 3: child.L(); break;   // Left
                        case 4: child.R(); break;   // Right
                        case 5: child.U(); break;   // Up
                        case 6: child.D(); break;   // Down
                    }

                    // update resulting child info
                    child.h = child.heuristic(solved);
                    child.g = currentState.g + moveCost;
                    child.f = child.g + child.h;

                    // if we have visited path before, skip it
                    if (visitedStates.contains(child)) {
                        continue;
                    }

                    if (!bestG.containsKey(child) || child.g < bestG.get(child)) {
                        child.g = currentState.g + moveCost;
                        child.h = child.heuristic(solved);
                        child.f = child.g + child.h;
                        bestG.put(child, child.g);
                        parent.put(child, currentState);
                        movesUsed.put(child, i);
                        discoveredStates.add(child);

                    }
                }
            }

            // going backwards
            RubiksCube currentState2 = discoveredStates2.poll();
            if (currentState2 != null && !visitedStates2.contains(currentState2)) {
                visitedStates2.add(currentState2);

                // check if we found a common state (meeting point)
                if (visitedStates.contains(currentState2)) {
                    meetState = currentState2;
                    return printPath(meetState, parent, movesUsed, parent2, movesUsed2);
                }

                    // making move forward
                for (int i = 1; i <= 6; i++) {
                    child = currentState2.clone();

                    switch (i) {
                        case 1: child.F(); break;   // Front
                        case 2: child.B(); break;   // Back
                        case 3: child.L(); break;   // Left
                        case 4: child.R(); break;   // Right
                        case 5: child.U(); break;   // Up
                        case 6: child.D(); break;   // Down
                    }

                    // update resulting child info
                    child.h = child.heuristic(start);
                    child.g = currentState2.g + moveCost;
                    child.f = child.g + child.h;

                    // if we have visited path before, skip it
                    if (visitedStates2.contains(child)) {
                        continue;
                    }

                    if (!bestG2.containsKey(child) || child.g < bestG2.get(child)) {
                        child.g = currentState2.g + moveCost;
                        child.h = child.heuristic(start);
                        child.f = child.g + child.h;
                        bestG2.put(child, child.g);
                        parent2.put(child, currentState2);
                        movesUsed2.put(child, i);
                        discoveredStates2.add(child);

                    }
                }
            }
        }
        return null;
    }

    // translating code move into a character
    private char moveCodeToChar(int moveCode) {
        switch (moveCode) {
            case 1: return 'F';
            case 2: return 'B';
            case 3: return 'L';
            case 4: return 'R';
            case 5: return 'U';
            case 6: return 'D';
        }
        return '?'; // safety default
    }

    // reconstructs path bidirectionally
    private char[] printPath(
            RubiksCube meetState,
            HashMap<RubiksCube, RubiksCube> parent,
            HashMap<RubiksCube, Integer> movesUsed,
            HashMap<RubiksCube, RubiksCube> parent2,
            HashMap<RubiksCube, Integer> movesUsed2
    ) {

        // forward half
        ArrayList<Integer> path = new ArrayList<>();
        RubiksCube currentState = meetState;

        while (parent.get(currentState) != null) {
            path.add(movesUsed.get(currentState));
            currentState = parent.get(currentState);
        }

        Collections.reverse(path);

        char[] pathChars = new char[path.size()];
        for (int i = 0; i < pathChars.length; i++) {
            pathChars[i] = moveCodeToChar(path.get(i));
        }


        // backward half:
        ArrayList<Integer> path2 = new ArrayList<>();
        RubiksCube currentState2 = meetState;

        while (parent2.get(currentState2) != null) {
            path2.add(movesUsed2.get(currentState2));
            currentState2 = parent2.get(currentState2);
        }

        // backward retrace gives solved -> meet, so reverse to get meet -> solved
        Collections.reverse(path2);

        char[] pathChars2 = new char[path2.size()];
        for (int i = 0; i < pathChars2.length; i++) {
            pathChars2[i] = moveCodeToChar(path2.get(i));
        }


        // put both halves together
        char[] fullPathChars = new char[pathChars.length + pathChars2.length];
        int idx = 0;

        for (int i = 0; i < pathChars.length; i++) {
            fullPathChars[idx++] = pathChars[i];
        }
        for (int i = 0; i < pathChars2.length; i++) {
            fullPathChars[idx++] = pathChars2[i];
        }

        System.out.println(fullPathChars);
        return fullPathChars;
    }

    public static void main(String[] args) throws FileNotFoundException{
        RubiksCube cube = new RubiksCube("testcases/scramble03.txt");
        char[] solution = cube.aStarAlgorithm();
    }



}