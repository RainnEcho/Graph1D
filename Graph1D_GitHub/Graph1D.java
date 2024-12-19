import java.util.*;

public class Graph1D {

    public int base_len;
    public int[] map;

    /** default constructor */
    public Graph1D () {}

    /** constructor */
    public Graph1D (int capacity) {
        base_len = capacity - 1;

        int arr_len = Formulas.Formula_toArrayLength(base_len);
        map = new int[arr_len];
        Arrays.fill(map, Integer.MAX_VALUE);
    }

    /** get the base length */
    public int getBaseLength () { return base_len; }

    /** get the length of the graph */
    public int getGraphLength () { return map.length; }

    /** display the graph */
    public void displayGraph () { System.out.printf ("graph: %s\n", Arrays.toString(map)); }

    /** add an edge */
    public void addEdge (int vertex1, int vertex2, int weight) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = Formulas.Formula_toIndex(base_len, vertex1, vertex2);
        map[index] = weight;
    }

    /** remove an edge */
    public void removeEdge (int vertex1, int vertex2) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = Formulas.Formula_toIndex(base_len, vertex1, vertex2);
        map[index] = Integer.MAX_VALUE;
    }

    /** get the original 2D coordinate of an index */
    public List<Integer> getCoordinateByIndex (int idx) {
        if (idx < 0 || idx >= map.length) throw new IllegalArgumentException("index out of bounds");

        int vertex1 = Formulas.Formula_toCoordinateVertex1(idx, base_len);
        int vertex2 = Formulas.Formula_toCoordinateVertex2(idx, base_len, vertex1);

        return Arrays.asList(vertex1, vertex2);
    }

    /** get the value of MST */
    public int getMSTValue () {
        int[] map_copy = Arrays.copyOf(map, map.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < map.length; i++) list.add(i);
        QuickSort.quickSort(map_copy, list);

        int sum = 0, count = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < map.length; i++) {
            if (!set.contains(getCoordinateByIndex(list.get(i)).get(0)) || !set.contains(getCoordinateByIndex(list.get(i)).get(1))) {
                sum += map_copy[i];
                set.add(getCoordinateByIndex(list.get(i)).get(0));
                set.add(getCoordinateByIndex(list.get(i)).get(1));
                count++;
                if (count == base_len) break;
            }
        }
        return sum;
    }

    /** generate MST */
    public void generateMST_Fast () {
        int[] map_copy = Arrays.copyOf(map, map.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < map.length; i++) list.add(i);
        QuickSort.quickSort(map_copy, list);

        int count = 0;
        Set<Integer> set_1 = new HashSet<>(), set_2 = new HashSet<>();

        for (int i = 0; i < map_copy.length; i++) {
            boolean addable = (!set_1.contains(getCoordinateByIndex(list.get(i)).get(0))
                               || !set_1.contains(getCoordinateByIndex(list.get(i)).get(1)));
            if (addable) {
                set_1.add(getCoordinateByIndex(list.get(i)).get(0));
                set_1.add(getCoordinateByIndex(list.get(i)).get(1));
                set_2.add(list.get(i));
                count++;
                if (count == base_len) break;
            }
        }
        for (int i = 0; i < map.length; i++) if (!set_2.contains(i)) map[i] = Integer.MAX_VALUE;
    }
}