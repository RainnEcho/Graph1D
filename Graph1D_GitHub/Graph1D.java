import java.util.*;

public class Graph1D {

    // member variables
    public int base_len;
    public int[] map;

    // default constructor
    public Graph1D () {}

    // constructor
    public Graph1D (int capacity) {
        base_len = capacity - 1;

        int arr_len = Formulas.arrLength_Formula(base_len);
        map = new int[arr_len];
        Arrays.fill(map, Integer.MAX_VALUE);
    }

    /** get the base length */
    public int getBaseLength () { return base_len; }

    /** get the length of the graph */
    public int getGraphLength () { return map.length; }

    /** print the graph */
    public void displayGraph () { System.out.printf ("graph: %s\n", Arrays.toString(map)); }

    /** add two vertices */
    public void addEdge (int vertex1, int vertex2, int weight) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = Formulas.toIndex_Formula(base_len, vertex1, vertex2);
        map[index] = weight;
    }

    /** remove two vertices */
    public void removeEdge (int vertex1, int vertex2) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = Formulas.toIndex_Formula(base_len, vertex1, vertex2);
        map[index] = Integer.MAX_VALUE;
    }

    /** get the original 2D coordinate of an index */
    public List<Integer> getCoordinateByIndex (int idx) {
        if (idx < 0 || idx >= map.length) throw new IllegalArgumentException("index out of bounds");

        int vertex1 = Formulas.toCoordinate_Formula_Vertex1(idx, base_len);
        int vertex2 = Formulas.toCoordinate_Formula_Vertex2(idx, base_len, vertex1);

        return Arrays.asList(vertex1, vertex2);
    }

    /** get the value of MST */
    public int getMSTValue () {
        int[] mapCopy = Arrays.copyOf(map, map.length);
        List<Integer> listOfIndex = new ArrayList<>();
        for (int i = 0; i < map.length; i++) listOfIndex.add(i);
        QuickSort.quickSort(mapCopy, listOfIndex);

        int sum = 0, count = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < map.length; i++) {
            if (!set.contains(getCoordinateByIndex(listOfIndex.get(i)).get(0)) 
                || !set.contains(getCoordinateByIndex(listOfIndex.get(i)).get(1))) {

                sum += mapCopy[i];
                set.add(getCoordinateByIndex(listOfIndex.get(i)).get(0));
                set.add(getCoordinateByIndex(listOfIndex.get(i)).get(1));
                count++;
                if (count == base_len) break;

            }
        }
        return sum;
    }

    /** generate MST */
    public void generateMST_Fast () {
        int[] mapCopy = Arrays.copyOf(map, map.length);
        List<Integer> listOfIndex = new ArrayList<>();
        for (int i = 0; i < map.length; i++) listOfIndex.add(i);
        QuickSort.quickSort(mapCopy, listOfIndex);

        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Boolean>> hash_map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < map.length; i++) {
            boolean checkpoint = (!set.contains(getCoordinateByIndex(listOfIndex.get(i)).get(0)) 
                                  || !set.contains(getCoordinateByIndex(listOfIndex.get(i)).get(1)));

            if (hash_map.containsKey(map[i])) hash_map.get(map[i]).add(checkpoint);
            else hash_map.put(map[i], new ArrayList<>(Arrays.asList(checkpoint)));

            if (checkpoint) {
                set.add(getCoordinateByIndex(listOfIndex.get(i)).get(0));
                set.add(getCoordinateByIndex(listOfIndex.get(i)).get(1));
                count++;
                if (count == base_len) break;
            }
        }

        for (int i = 0; i < map.length; i++) {
            if (hash_map.containsKey(mapCopy[i])) {
                List<Boolean> values = hash_map.get(mapCopy[i]);
                if (! values.isEmpty()) {
                    if (! values.get(0)) map[i] = Integer.MAX_VALUE;
                    values.remove(0);
                    if (values.isEmpty()) hash_map.remove(mapCopy[i]);
                }
            } else map[i] = Integer.MAX_VALUE;
        }
    }
}