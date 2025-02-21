import java.util.*;

public class Graph1D {
    // members
    public int baseLength;
    public int[] map;

    // default constructor
    public Graph1D() {}

    // constructor
    public Graph1D(int capacity) {
        this.baseLength = capacity - 1;
        int arrLength = Formulas.toArrayLength(this.baseLength);
        this.map = new int[arrLength];

        Arrays.fill(this.map, Integer.MAX_VALUE);
    }

    /* get the base length */
    public int getBaseLength() { return this.baseLength; }

    /* get the length of the graph */
    public int getGraphLength() { return this.map.length; }

    /* display the graph */
    public void displayGraph() { System.out.printf("graph: %s\n", Arrays.toString(this.map)); }

    /* add an edge */
    public void addEdge(int v1, int v2, int w) {
        if(v2 <= v1) throw new IllegalArgumentException("self connection / illegal sequence");

        int index = Formulas.toIndex(this.baseLength, v1, v2);
        this.map[index] = w;
    }

    /* remove an edge */
    public void removeEdge(int v1, int v2) {
        if(v2 <= v1) throw new IllegalArgumentException("self connection / illegal sequence");

        int index = Formulas.toIndex(this.baseLength, v1, v2);
        this.map[index] = Integer.MAX_VALUE;
    }

    /* get the original 2D coordinate of an index */
    public List<Integer> getCoordinateByIndex(int idx) {
        if(idx < 0 || idx >= this.map.length) throw new IllegalArgumentException("index out of bounds");

        int v1 = Formulas.toCoordinateV1(idx, this.baseLength);
        int v2 = Formulas.toCoordinateV2(idx, this.baseLength, v1);

        return Arrays.asList(v1, v2);
    }

    /* get the value of MST */
    public int getMSTValue() {
        int[] mapCopy = Arrays.copyOf(this.map, this.map.length);
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < this.map.length; i++) list.add(i);
        QuickSort.quickSortJoint(mapCopy, list);

        int sum = 0, count = 0;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < this.map.length; i++) {
            int idx = list.get(i);
            int row = getCoordinateByIndex(idx).get(0), col = getCoordinateByIndex(idx).get(1);
            if(!set.contains(row) || !set.contains(col)) {
                sum += mapCopy[i];
                set.addAll(Set.of(row, col));
                count++;
                if(count == this.baseLength) break;
            }
        }
        return sum;
    }

    /* generate the MST */
    public void generateMST() {
        int[] mapCopy = Arrays.copyOf(this.map, this.map.length);
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < this.map.length; i++) list.add(i);
        QuickSort.quickSortJoint(mapCopy, list);

        int count = 0;
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();

        for(int i = 0; i < mapCopy.length; i++) {
            int idx = list.get(i);
            int row = getCoordinateByIndex(idx).get(0), col = getCoordinateByIndex(idx).get(1);
            if(!set1.contains(row) || !set1.contains(col)) {
                set1.addAll(Set.of(row, col));
                set2.add(idx);
                count++;
                if(count == this.baseLength) break;
            }
        }
        for(int i = 0; i < this.map.length; i++) if(!set2.contains(i)) this.map[i] = Integer.MAX_VALUE;
    }
}