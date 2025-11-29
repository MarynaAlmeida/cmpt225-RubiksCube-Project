package rubikscube;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


interface Piece {
    String getId();
    boolean isPlaceCorrect(RubicsCube cube);
}

class Edge implements Piece {
    String id;
    Pair pair1;
    Pair pair2;
    Pair center1;
    Pair center2;

    Edge(
            String id,
            Pair pair1,
            Pair pair2,
            Pair center1,
            Pair center2) {
        this.id = id;
        this.pair1 = pair1;
        this.pair2 = pair2;
        this.center1 = center1;
        this.center2 = center2;
    }

    public boolean isPlaceCorrect(RubicsCube cube) {
        boolean t1 = cube.cube[pair1.row][pair1.column] == cube.cube[center1.row][center1.column];
        boolean t2 = cube.cube[pair2.row][pair2.column] == cube.cube[center2.row][center2.column];
        return t1 && t2;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge e = (Edge) obj;
        return id.equals(e.id);
    }

    public int hashCode() {
        return id.hashCode();
    }
}

class Corner implements Piece {
    String id;
    Pair pair1;
    Pair pair2;
    Pair pair3;
    Pair center1;
    Pair center2;
    Pair center3;

    Corner(
            String id,
            Pair pair1,
            Pair pair2,
            Pair pair3,
            Pair center1,
            Pair center2,
            Pair center3
    ) {
        this.id = id;
        this.pair1 = pair1;
        this.pair2 = pair2;
        this.pair3 = pair3;
        this.center1 = center1;
        this.center2 = center2;
        this.center3 = center3;
    }

    public boolean isPlaceCorrect(RubicsCube cube) {
        boolean t1 = cube.cube[pair1.row][pair1.column] == cube.cube[center1.row][center1.column];
        boolean t2 = cube.cube[pair2.row][pair2.column] == cube.cube[center2.row][center2.column];
        boolean t3 = cube.cube[pair3.row][pair3.column] == cube.cube[center3.row][center3.column];
        return t1 && t2 && t3;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Corner)) {
            return false;
        }
        Corner c = (Corner) obj;
        return id.equals(c.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

}

class RubicsCube {
    private static final CubeSide UP =
            new CubeSide(
                    new Pair(0, 3),
                    new Pair(2, 5),
                    false,
                    true,
                    false,
                    true,
                    new Segment(new Pair(3, 9), new Pair(3, 11)),
                    new Segment(new Pair(3, 3), new Pair(3, 5)),
                    new Segment(new Pair(3, 0), new Pair(3, 2)),
                    new Segment(new Pair(3, 6), new Pair(3, 8)),
                    false,
                    false,
                    false,
                    false,
                    'U');
    private static final CubeSide DOWN =
            new CubeSide(
                    new Pair(6, 3),
                    new Pair(8, 5),
                    false,
                    true,
                    false,
                    true,
                    new Segment(new Pair(5, 3), new Pair(5, 5)),
                    new Segment(new Pair(5, 9), new Pair(5, 11)),
                    new Segment(new Pair(5, 0), new Pair(5, 2)),
                    new Segment(new Pair(5, 6), new Pair(5, 8)),
                    false,
                    false,
                    false,
                    false,
                    'D');
    private static final CubeSide FRONT =
            new CubeSide(
                    new Pair(3, 3),
                    new Pair(5, 5),
                    false,
                    true,
                    false,
                    true,
                    new Segment(new Pair(2, 3), new Pair(2, 5)),
                    new Segment(new Pair(6, 3), new Pair(6, 5)),
                    new Segment(new Pair(3, 2), new Pair(5, 2)),
                    new Segment(new Pair(3, 6), new Pair(5, 6)),
                    false,
                    true,
                    false,
                    true,
                    'F');
    private static final CubeSide BACK =
            new CubeSide(
                    new Pair(3, 9),
                    new Pair(5, 11),
                    false,
                    true,
                    false,
                    true,
                    new Segment(new Pair(0, 3), new Pair(0, 5)),
                    new Segment(new Pair(8, 3), new Pair(8, 5)),
                    new Segment(new Pair(3, 8), new Pair(5, 8)),
                    new Segment(new Pair(3, 0), new Pair(5, 0)),
                    false,
                    false,
                    true,
                    false,
                    'B');
    private static final CubeSide LEFT =
            new CubeSide(
                    new Pair(3, 0),
                    new Pair(5, 2),
                    false,
                    true,
                    false,
                    true,
                    new SegmentBuilder().start(new Pair(0, 3)).end(new Pair(2, 3)).build(),
                    new SegmentBuilder().start(new Pair(6, 3)).end(new Pair(8, 3)).build(),
                    new SegmentBuilder().start(new Pair(3, 11)).end(new Pair(5, 11)).build(),
                    new SegmentBuilder().start(new Pair(3, 3)).end(new Pair(5, 3)).build(),
                    false,
                    false,
                    true,
                    true,
                    'L');
    private static final CubeSide RIGHT =
            new CubeSide(
                    new Pair(3, 6),
                    new Pair(5, 8),
                    false,
                    true,
                    false,
                    true,
                    new SegmentBuilder().start(new Pair(0, 5)).end(new Pair(2, 5)).build(),
                    new SegmentBuilder().start(new Pair(6, 5)).end(new Pair(8, 5)).build(),
                    new SegmentBuilder().start(new Pair(3, 5)).end(new Pair(5, 5)).build(),
                    new SegmentBuilder().start(new Pair(3, 9)).end(new Pair(5, 9)).build(),
                    true,
                    true,
                    false,
                    false,
                    'R');

    private String initialState;
    char[][] cube;
    private Map<Character, CubeSide> sides;
    private RubicsCube parent;
    private char move;
    List<Piece> pieces = new ArrayList<>();

    List<Corner> corners = new ArrayList<>();

    public RubicsCube(String initialState) {
        this.cube = new char[9][];
        this.initialState = initialState;
        int i = 0;
        for (String line : initialState.split(System.lineSeparator())) {
            this.cube[i++] = line.toCharArray();
        }
        sides = new HashMap<>();
        sides.put('U', UP);
        sides.put('D', DOWN);
        sides.put('L', LEFT);
        sides.put('R', RIGHT);
        sides.put('F', FRONT);
        sides.put('B', BACK);

        pieces.add(new Edge("E1", new Pair(0, 4), new Pair(4, 11), new Pair(1, 4), new Pair(4, 10)));
        pieces.add(new Edge("E2", new Pair(1, 3), new Pair(3, 1), new Pair(1, 4), new Pair(4, 1)));
        pieces.add(new Edge("E3", new Pair(1, 5), new Pair(3, 7), new Pair(1, 4), new Pair(4, 7)));
        pieces.add(new Edge("E4", new Pair(2, 4), new Pair(3, 4), new Pair(1, 4), new Pair(4, 4)));
        pieces.add(new Edge("E5", new Pair(4, 0), new Pair(4, 11), new Pair(4, 1), new Pair(4, 10)));
        pieces.add(new Edge("E6", new Pair(4, 2), new Pair(4, 3), new Pair(4, 1), new Pair(4, 4)));
        pieces.add(new Edge("E7", new Pair(4, 8), new Pair(4, 9), new Pair(4, 7), new Pair(4, 10)));
        pieces.add(new Edge("E8", new Pair(4, 0), new Pair(4, 11), new Pair(4, 1), new Pair(4, 10)));
        pieces.add(new Edge("E9", new Pair(5, 1), new Pair(7, 3), new Pair(4, 1), new Pair(7, 4)));
        pieces.add(new Edge("E10", new Pair(5, 4), new Pair(6, 4), new Pair(4, 4), new Pair(7, 4)));
        pieces.add(new Edge("E11", new Pair(5, 7), new Pair(7, 5), new Pair(4, 7), new Pair(7, 4)));
        pieces.add(new Edge("E12", new Pair(8, 4), new Pair(5, 10), new Pair(7, 4), new Pair(4, 10)));

        pieces.add(new Corner("C1", new Pair(2, 3), new Pair(3, 2), new Pair(3, 3), new Pair(1, 4), new Pair(4, 1), new Pair(4, 4)));
        pieces.add(new Corner("C2", new Pair(2, 5), new Pair(3, 5), new Pair(3, 6), new Pair(1, 4), new Pair(4, 4), new Pair(4, 7)));
        pieces.add(new Corner("C3", new Pair(0, 3), new Pair(3, 0), new Pair(3, 11), new Pair(1, 4), new Pair(4, 1), new Pair(4, 10)));
        pieces.add(new Corner("C4", new Pair(0, 5), new Pair(3, 8), new Pair(3, 9), new Pair(1, 4), new Pair(4, 7), new Pair(4, 10)));
        pieces.add(new Corner("C5", new Pair(5, 2), new Pair(5, 3), new Pair(6, 3), new Pair(4, 1), new Pair(4, 4), new Pair(7, 4)));
        pieces.add(new Corner("C6", new Pair(5, 5), new Pair(5, 6), new Pair(6, 5), new Pair(4, 4), new Pair(4, 7), new Pair(7, 4)));
        pieces.add(new Corner("C7", new Pair(5, 0), new Pair(8, 3), new Pair(5, 11), new Pair(4, 1), new Pair(7, 4), new Pair(4, 10)));
        pieces.add(new Corner("C8", new Pair(8, 5), new Pair(5, 8), new Pair(5, 9), new Pair(7, 4), new Pair(4, 7), new Pair(4, 10)));

    }

    public int compare(RubicsCube other) {
        int diff = 0;
        for (int i = 0; i < 9; i++) {
            char[] c1 = cube[i];
            char[] c2 = other.cube[i];
            for (int j = 0; j < c1.length; j++) {
                if (c1[j] != c2[j]) {
                    diff++;
                }
            }
        }
        return diff;
    }

    Set<Piece> getPiecesInWrongPlace() {
        Set<Piece> r = new HashSet<>();
        for (Piece p: pieces) {
            if (!p.isPlaceCorrect(this)) {
                r.add(p);
            }
        }
        return r;
    }

    int heuristic() {
        Set<Piece> piecesWrongPlace = getPiecesInWrongPlace();
        if (piecesWrongPlace.isEmpty()) {
            return 0;
        }
        Map<RubicsCube, Integer> moves = new HashMap<>();
        moves.put(this, 0);
        Queue<RubicsCube> q = new LinkedList<>();
        q.add(this);
        Map<Piece, Integer> minMoves = new HashMap<>();

        while (!q.isEmpty()) {
            RubicsCube c = q.poll();
            int m = moves.get(c) + 1;
            for (CubeSide s : sides.values()) {
                RubicsCube t = new RubicsCube(c.toString());
                t.move(s);
                if (moves.get(t) == null) {
                    moves.put(t, m);
                    q.add(t);
                    for (Piece p: piecesWrongPlace) {
                        if (p.isPlaceCorrect(t)) {
                            minMoves.put(p, m);
                        }
                    }
                    piecesWrongPlace.removeAll(minMoves.keySet());
                }
                if (piecesWrongPlace.isEmpty()) {
                    break;
                }
            }
        }
        int max = 0;
        for (int i: minMoves.values()) {
            if(i > max) {
                max = i;
            }
        }
        return max;
    }



    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof RubicsCube)) {
            return false;
        }
        RubicsCube otherCube = (RubicsCube) other;
        return compare(otherCube) == 0;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    private static final String SOLVED =
            "   OOO"
                    + System.lineSeparator()
                    + "   OOO"
                    + System.lineSeparator()
                    + "   OOO"
                    + System.lineSeparator()
                    + "GGGWWWBBBYYY"
                    + System.lineSeparator()
                    + "GGGWWWBBBYYY"
                    + System.lineSeparator()
                    + "GGGWWWBBBYYY"
                    + System.lineSeparator()
                    + "   RRR"
                    + System.lineSeparator()
                    + "   RRR"
                    + System.lineSeparator()
                    + "   RRR"
                    + System.lineSeparator();

    public String solve() {
        RubicsCube target = new RubicsCube(SOLVED);
        RubicsCube r = solve(target);
        StringBuilder sb = new StringBuilder();
        while (r.parent != null) {
            sb.append(r.move);
            r = r.parent;
        }
        return sb.reverse().toString();
    }

    public String solveMeetInTheMiddle() {
        RubicsCube target = new RubicsCube(SOLVED);
        SolverState forward = new SolverState(this, new RubicsCube(SOLVED));
        SolverState backward = new SolverState(new RubicsCube(SOLVED), new RubicsCube(toString()));
        do {
            RubicsCube n = forward.next();
            RubicsCube m = backward.next();
            Set<RubicsCube> fSet = new HashSet<>(forward.openQueue);
            Set<RubicsCube> bSet = new HashSet<>(backward.openQueue);
            fSet.retainAll(bSet);
            if (!fSet.isEmpty()) {
                RubicsCube c = fSet.iterator().next();
                n = forward.openQueueMap.get(c);
                m = backward.openQueueMap.get(c);
                StringBuilder sbForward = new StringBuilder();
                while (n.parent != null) {
                    sbForward.append(n.move);
                    n = n.parent;
                }
                StringBuilder sbBackward = new StringBuilder();
                while (m.parent != null) {
                    sbBackward.append(m.move);
                    sbBackward.append(m.move);
                    sbBackward.append(m.move);
                    m = m.parent;
                }
                return sbForward.reverse().toString() + sbBackward.toString();
            }
        } while (true);
    }

    class SolverState {
        Map<RubicsCube, Integer> g;
        Map<RubicsCube, Integer> h;
        Map<RubicsCube, Integer> f;
        PriorityQueue<RubicsCube> openQueue;
        Set<RubicsCube> closedSet;
        RubicsCube start;
        RubicsCube target;
        Map<RubicsCube, RubicsCube> openQueueMap;

        SolverState(RubicsCube start, RubicsCube target) {
            this.start = start;
            this.target = target;
            g = new HashMap<>();
            g.put(start, 0);
            h = new HashMap<>();
            h.put(start, start.heuristic());
            f = new HashMap<>();
            f.put(start, start.heuristic());
            openQueue =
                    new PriorityQueue<RubicsCube>(
                            (k1, k2) -> Integer.compare(f.getOrDefault(k1, 0), f.getOrDefault(k2, 0)));
            openQueue.add(start);
            closedSet = new HashSet<>();
            openQueueMap = new HashMap<>();
            openQueueMap.put(start, start);
        }

        public RubicsCube next() {
            if (openQueue.isEmpty()) {
                throw new RuntimeException("No more nodes to explore!");
            }
            RubicsCube n = openQueue.poll();
            openQueueMap.remove(n);
            for (CubeSide side : sides.values()) {
                RubicsCube next = new RubicsCube(n.toString());
                next.move(side);
                int nextG = g.get(n) + 1;
                int nextH = next.heuristic();
                g.put(next, nextG);
                h.put(next, nextH);
                next.move = side.side;
                next.parent = n;
                if (openQueue.contains(next)) {
                    int nextF = f.get(next);
                    if (nextF > nextG + nextH) {
                        openQueue.remove(next);
                        f.put(next, nextG + nextH);
                        openQueue.add(next);
                        openQueueMap.put(next, next);
                    }
                } else if (closedSet.contains(next)) {
                    int nextF = f.getOrDefault(next, 0);
                    if (nextF > nextG + nextH) {
                        closedSet.remove(next);
                        f.put(next, nextG + nextH);
                        openQueue.add(next);
                        openQueueMap.put(next, next);

                    }
                } else {
                    f.put(next, nextG + nextH);
                    openQueue.add(next);
                    openQueueMap.put(next, next);

                }
            }
            closedSet.add(n);
            return n;
        }
    }

    /**
     * f(n), which is the sum of two components: g(n): The actual, known cost (distance) from the
     * starting node to the current node n. h(n): The heuristic estimate of the cost (distance) from
     * the current node n to the goal node. f(n) = g(n) + h(n)
     *
     * <p>n: node (RubicsCube) f: function g: function distance from start h: function
     */
    public RubicsCube solve(RubicsCube target) {
        Map<RubicsCube, Integer> g = new HashMap<>();
        g.put(this, 0);
        Map<RubicsCube, Integer> h = new HashMap<>();
        h.put(this, this.compare(target));
        Map<RubicsCube, Integer> f = new HashMap<>();
        f.put(this, this.compare(target));
        PriorityQueue<RubicsCube> openQueue =
                new PriorityQueue<RubicsCube>(
                        (k1, k2) -> Integer.compare(f.getOrDefault(k1, 0), f.getOrDefault(k2, 0)));
        openQueue.add(this);
        Set<RubicsCube> closedSet = new HashSet<>();
        while (!openQueue.isEmpty()) {
            RubicsCube n = openQueue.poll();
            for (CubeSide side : sides.values()) {
                RubicsCube next = new RubicsCube(n.toString());
                next.move(side);
                if (next.equals(target)) {
                    next.parent = n;
                    next.move = side.side;
                    g.put(next, g.get(n) + 1);
                    h.put(next, next.compare(target));
                    f.put(next, g.get(next) + h.get(next));
                    return next;
                }

                int nextG = g.get(n) + 1;
                int nextH = next.compare(target);
                g.put(next, nextG);
                h.put(next, nextH);
                next.move = side.side;
                next.parent = n;
                if (openQueue.contains(next)) {
                    int nextF = f.get(next);
                    if (nextF > nextG + nextH) {
                        openQueue.remove(next);
                        f.put(next, nextG + nextH);
                        openQueue.add(next);
                    }
                }else {
                    f.put(next, nextG + nextH);
                    openQueue.add(next);
                }
            }
            openQueue.remove(n);
            closedSet.add(n);
        }
        throw new RuntimeException("Target not found!");
    }

    private void move(CubeSide side) {
        side.move(cube);
    }

    public void move(String moves) {
        for (char c : moves.toUpperCase().toCharArray()) {
            move(sides.get(c));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] c : cube) {
            sb.append(new String(c)).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

class Pair {
    int row;
    int column;

    public Pair(int r, int c) {
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}

class Segment {
    Pair start;
    Pair end;

    public Segment(Pair start, Pair end) {
        this.start = start;
        this.end = end;
    }

    public char[] toCharArray(char[][] cube) {
        char[] r = new char[3];
        if (start.row == end.row) {
            for (int i = 0; i < 3; i++) {
                r[i] = cube[start.row][start.column + i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                r[i] = cube[start.row + i][start.column];
            }
        }
        return r;
    }

    public void apply(char[] items, char[][] cube, boolean inverted) {
        if (start.row == end.row) {
            if (inverted) {
                for (int i = 0; i < 3; i++) {
                    cube[start.row][start.column + i] = items[2 - i];
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    cube[start.row][start.column + i] = items[i];
                }
            }
        } else {
            if (inverted) {
                for (int i = 0; i < 3; i++) {
                    cube[start.row + i][start.column] = items[2 - i];
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    cube[start.row + i][start.column] = items[i];
                }
            }
        }
    }

    public String toString() {
        return "Segment(" + start + "," + end + ")";
    }
}

class SegmentBuilder {
    Pair start;
    Pair end;

    public SegmentBuilder() {}

    public SegmentBuilder start(Pair start) {
        this.start = start;
        return this;
    }

    public SegmentBuilder end(Pair end) {
        this.end = end;
        return this;
    }

    public Segment build() {
        return new Segment(start, end);
    }
}

enum Direction {
    Up,
    Down,
    Left,
    Right;
}

class CubeSide {
    Pair start;
    Pair end;
    Segment up;
    Segment down;
    Segment right;
    Segment left;

    boolean upToRightInverted;
    boolean rightToDownInverted;
    boolean downToLeftInverted;
    boolean leftToUpInverted;

    Segment outerUp;
    Segment outerDown;
    Segment outerRight;
    Segment outerLeft;

    boolean outerUpToRightInverted;
    boolean outerRightToDownInverted;
    boolean outerDownToLeftInverted;
    boolean outerLeftToUpInverted;
    char side;

    CubeSide(
            Pair start,
            Pair end,
            boolean upToRightInverted,
            boolean rightToDownInverted,
            boolean downToLeftInverted,
            boolean leftToUpInverted,
            Segment outerUp,
            Segment outerDown,
            Segment outerLeft,
            Segment outerRight,
            boolean outerUpToRightInverted,
            boolean outerRightToDownInverted,
            boolean outerDownToLeftInverted,
            boolean outerLeftToUpInverted,
            char side) {
        this.start = start;
        this.end = end;
        Map<Direction, Segment> segs = getSegments();
        up = segs.get(Direction.Up);
        down = segs.get(Direction.Down);
        right = segs.get(Direction.Right);
        left = segs.get(Direction.Left);
        this.upToRightInverted = upToRightInverted;
        this.rightToDownInverted = rightToDownInverted;
        this.downToLeftInverted = downToLeftInverted;
        this.leftToUpInverted = leftToUpInverted;

        this.outerUp = outerUp;
        this.outerDown = outerDown;
        this.outerRight = outerRight;
        this.outerLeft = outerLeft;

        this.outerUpToRightInverted = outerUpToRightInverted;
        this.outerRightToDownInverted = outerRightToDownInverted;
        this.outerDownToLeftInverted = outerDownToLeftInverted;
        this.outerLeftToUpInverted = outerLeftToUpInverted;
        this.side = side;
    }

    public void move(char[][] cube) {
        char[] copyUp = up.toCharArray(cube);
        char[] copyRight = right.toCharArray(cube);
        char[] copyDown = down.toCharArray(cube);
        char[] copyLeft = left.toCharArray(cube);

        char[] copyOuterUp = outerUp.toCharArray(cube);
        char[] copyOuterRight = outerRight.toCharArray(cube);
        char[] copyOuterDown = outerDown.toCharArray(cube);
        char[] copyOuterLeft = outerLeft.toCharArray(cube);

        right.apply(copyUp, cube, upToRightInverted);
        outerRight.apply(copyOuterUp, cube, outerUpToRightInverted);

        down.apply(copyRight, cube, rightToDownInverted);
        outerDown.apply(copyOuterRight, cube, outerRightToDownInverted);

        left.apply(copyDown, cube, downToLeftInverted);
        outerLeft.apply(copyOuterDown, cube, outerDownToLeftInverted);

        up.apply(copyLeft, cube, leftToUpInverted);
        outerUp.apply(copyOuterLeft, cube, outerLeftToUpInverted);
    }

    public Map<Direction, Segment> getSegments() {
        Map<Direction, Segment> map = new HashMap<>();
        map.put(
                Direction.Up,
                new SegmentBuilder().start(start).end(new Pair(start.row, end.column)).build());
        map.put(
                Direction.Down,
                new SegmentBuilder().start(new Pair(end.row, start.column)).end(end).build());
        map.put(
                Direction.Right,
                new SegmentBuilder().start(new Pair(start.row, end.column)).end(end).build());
        map.put(
                Direction.Left,
                new SegmentBuilder().start(start).end(new Pair(end.row, start.column)).build());
        return map;
    }
}

class Main {
    public static void main(String[] args) {
        String initialState =
                "   OOG"
                        + System.lineSeparator()
                        + "   OOW"
                        + System.lineSeparator()
                        + "   OOW"
                        + System.lineSeparator()
                        + "YGGWWRBBOYBB"
                        + System.lineSeparator()
                        + "GGGWWOYBBYYY"
                        + System.lineSeparator()
                        + "GGGWWWOBBYYY"
                        + System.lineSeparator()
                        + "   RRB"
                        + System.lineSeparator()
                        + "   RRR"
                        + System.lineSeparator()
                        + "   RRR"
                        + System.lineSeparator();
        RubicsCube rubicsCube = new RubicsCube(initialState);
        String solution = rubicsCube.solveMeetInTheMiddle();
        System.out.println("Solution: " + solution);

        // System.out.println(rubicsCube);
        // rubicsCube.move(solution);
        // System.out.println(rubicsCube);

         System.out.println("-------------------------------");
         initialState =
             "   BBB"
                 + System.lineSeparator()
                 + "   OOO"
                 + System.lineSeparator()
                 + "   BBB"
                 + System.lineSeparator()
                 + "OGOWWWRBRYYY"
                 + System.lineSeparator()
                 + "OGOWWWRBRYYY"
                 + System.lineSeparator()
                 + "OGOWWWRBRYYY"
                 + System.lineSeparator()
                 + "   GGG"
                 + System.lineSeparator()
                 + "   RRR"
                 + System.lineSeparator()
                 + "   GGG"
                 + System.lineSeparator();
         rubicsCube = new RubicsCube(initialState);
         solution = rubicsCube.solveMeetInTheMiddle();
         System.out.println("Solution: " + solution);

        // System.out.println(rubicsCube);
        // rubicsCube.move(solution);
        // System.out.println(rubicsCube);


         System.out.println("-------------------------------");
         initialState =
             "   WOG"
                 + System.lineSeparator()
                 + "   WOG"
                 + System.lineSeparator()
                 + "   RRY"
                 + System.lineSeparator()
                 + "OOYBBOBWWOBB"
                 + System.lineSeparator()
                 + "GGRWWYBBGOYY"
                 + System.lineSeparator()
                 + "GGRWWGYYRGYY"
                 + System.lineSeparator()
                 + "   BBO"
                 + System.lineSeparator()
                 + "   RRO"
                 + System.lineSeparator()
                 + "   RRW"
                 + System.lineSeparator();
         rubicsCube = new RubicsCube(initialState);
         solution = rubicsCube.solveMeetInTheMiddle();
         System.out.println("Solution: " + solution);

        // System.out.println(rubicsCube);
        // rubicsCube.move(solution);
        // System.out.println(rubicsCube);

        initialState =
                "   OGB"
                        + System.lineSeparator()
                        + "   WOR"
                        + System.lineSeparator()
                        + "   WGR"
                        + System.lineSeparator()
                        + "YRRBWYBBOWOB"
                        + System.lineSeparator()
                        + "GGGRWYBBOWYY"
                        + System.lineSeparator()
                        + "GOOGWGWBOWYY"
                        + System.lineSeparator()
                        + "   YBR"
                        + System.lineSeparator()
                        + "   YRO"
                        + System.lineSeparator()
                        + "   RRG"
                        + System.lineSeparator();

        rubicsCube = new RubicsCube(initialState);
        solution = rubicsCube.solveMeetInTheMiddle();
        System.out.println("Solution: " + solution);


//         "   OOO"
//             + System.lineSeparator()
//   + "   OOO"
//             + System.lineSeparator()
//   + "   BBB"
//             + System.lineSeparator()
// + "GGOWWWRBBYYY"
//             + System.lineSeparator()
// + "GGOWWWRBBYYY"
//             + System.lineSeparator()
// + "GGOWWWRBBYYY"
//             + System.lineSeparator()
//   + "   GGG"
//             + System.lineSeparator()
//   + "   RRR"
//             + System.lineSeparator()
//   + "   RRR"
//             + System.lineSeparator() ;

//     "   OOO"
//             + System.lineSeparator()
// + "   GOB"
//             + System.lineSeparator()
// + "   OOO"
//             + System.lineSeparator()
// + "GOGWWWBOBYYY"
//             + System.lineSeparator()
// + "YGWGWBWBYBYG"
//             + System.lineSeparator()
// + "GRGWWWBRBYYY"
//             + System.lineSeparator()
//   + "   RRR"
//             + System.lineSeparator()
//   + "   GRB"
//             + System.lineSeparator()
//   + "   RRR"
//             + System.lineSeparator();
//        RubicsCube rubicsCube = new RubicsCube(initialState);
//        String solution = rubicsCube.solveMeetInTheMiddle();
//        System.out.println("Solution: " + solution);

//        System.out.println(rubicsCube);
//        rubicsCube.move(solution);
//        System.out.println(rubicsCube);
//    }
    }}
