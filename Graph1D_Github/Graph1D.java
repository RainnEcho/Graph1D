import java.util.*;

public class Graph1D {

    // member variables
    public int base_len;
    public int[] map;

    // default constructor
    public Graph1D () {}

    // constructor
    public Graph1D (int capacity) {
        // base length is the number of vertices of the first row in the initial 2D graph
        base_len = capacity - 1;

        // the formula to calculate the length of the 1D array converted
        int arr_len = (base_len * (base_len + 1)) / 2;

        // initialize the map and fill by Integer.MAX_VALUE, represents no connection between vertices
        map = new int[arr_len];
        Arrays.fill(map, Integer.MAX_VALUE);
    }

    /** get the base length */
    public int getBaseLength () { return base_len; }

    /** get the length of the graph */
    public int getGraphLength () { return map.length; }

    /** get the original 2D coordinate of an index */
    public List<Integer> getCoordinateViaIndex (int idx) {
        if (idx < 0 || idx >= map.length) throw new IllegalArgumentException("index out of bounds");

        double discriminant = Math.sqrt((2 * base_len + 1) * (2 * base_len + 1) - 8 * idx);

        int vertex1 = (int) ((2 * base_len + 1 - discriminant) / 2);
        int rowStart = vertex1 * (2 * base_len - vertex1 + 1) / 2;
        int vertex2 = vertex1 + 1 + (idx - rowStart);

        return Arrays.asList(vertex1, vertex2);
    }

    /** print the graph */
    public void displayGraph () { System.out.printf ("graph: %s\n", Arrays.toString(map)); }

    /** add two vertices */
    public void addEdge (int vertex1, int vertex2, int weight) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = ((base_len + base_len - (vertex1 - 1)) * vertex1) / 2 + vertex2 - vertex1 - 1;
        map[index] = weight;
    }

    /** remove two vertices */
    public void removeEdge (int vertex1, int vertex2) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");

        int index = ((base_len + base_len - (vertex1 - 1)) * vertex1) / 2 + (vertex2 - vertex1) - 1;
        map[index] = Integer.MAX_VALUE;
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
            if (!set.contains(getCoordinateViaIndex(listOfIndex.get(i)).get(0)) 
                || !set.contains(getCoordinateViaIndex(listOfIndex.get(i)).get(1))) {

                sum += mapCopy[i];
                set.add(getCoordinateViaIndex(listOfIndex.get(i)).get(0));
                set.add(getCoordinateViaIndex(listOfIndex.get(i)).get(1));
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
            boolean checkpoint = (!set.contains(getCoordinateViaIndex(listOfIndex.get(i)).get(0)) 
                                  || !set.contains(getCoordinateViaIndex(listOfIndex.get(i)).get(1)));

            if (hash_map.containsKey(map[i])) hash_map.get(map[i]).add(checkpoint);
            else hash_map.put(map[i], new ArrayList<>(Arrays.asList(checkpoint)));

            if (checkpoint) {
                set.add(getCoordinateViaIndex(listOfIndex.get(i)).get(0));
                set.add(getCoordinateViaIndex(listOfIndex.get(i)).get(1));
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

    /** test used main function */
    public static void main(String[] args) {
        // instance Graph1D for test
        Graph1D graph = new Graph1D(4);

        // add edges
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 15);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 7);

        // get the MST value
        System.out.println (graph.getMSTValue());

        // generate MST
        graph.generateMST_Fast();

        // display the graph
        graph.displayGraph();
    }
}