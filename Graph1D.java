import java.util.*;

public class Graph1D {

    // member variables
    public int base_len;
    public int[] map;
    public List<List<Integer>> list;

    /** default constructor */
    public Graph1D () {}

    /** constructor */
    public Graph1D (int capacity) {
        // base length: the number of vertices of the first row in the initial 2D graph
        base_len = capacity - 1;

        // formula to calculate the length of the 1D array converted
        int arr_len = (base_len * (base_len + 1)) / 2;

        // initialize the 1D array (map) and fill by Integer.MAX_VALUE
        map = new int[arr_len];
        Arrays.fill(map, Integer.MAX_VALUE);

        // initialize the array list to sort the horizontal and vertical coordinates of every vertex
        list = new ArrayList<>(arr_len);
        for (int i = 0; i < arr_len; i++) list.add(new ArrayList<>(Arrays.asList(null, null)));
    }

    /** get the base length */
    public int getBaseLength () { return base_len; }

    /** get the length of the map */
    public int getMapLength () { return map.length; }

    /** print the list of the graph */
    public void displayList () { System.out.printf ("list: %s\n", list); }

    /** add two vertices with a weighted edge */
    public void addEdge (int vertex1, int vertex2, int weight) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
        int index = (vertex1 == 0) 
            ? vertex2 - 1 
            : vertex2 + vertex1 + vertex1 * (base_len - vertex1) - 2;

        map[index] = weight;

        list.get(index).set(0, vertex1);
        list.get(index).set(1, vertex2);
    }

    /** remove two vertices */
    public void removeEdge (int vertex1, int vertex2) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
        int index = (vertex1 == 0) 
            ? vertex2 - 1 
            : vertex2 + vertex1 + vertex1 * (base_len - vertex1) - 2;

        map[index] = Integer.MAX_VALUE;

        list.get(index).set(0, null);
        list.get(index).set(1, null);
    }

    /** get the sum of minimum spanning tree (MST) */
    public int getMSTValue () {
        Queue<Integer> queue = Arrays.stream(map)
                                     .boxed()
                                     .collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
        Set<Integer> set = new HashSet<>();
        int sum = 0, count = 0, size = queue.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).get(0) != null && !(set.contains(list.get(i).get(0)) && set.contains(list.get(i).get(1)))) {
                    sum += queue.poll();
                    set.add(list.get(i).get(0));
                    set.add(list.get(i).get(1));
                    count++;
            } else queue.poll();
            if (count == base_len) break;
        }
        return sum;
    }

    /** test used main function */
    public static void main(String[] args) {
        // example instance Graph1D for test
        Graph1D graph = new Graph1D(4);

        // add edges
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 9);
        graph.addEdge(0, 3, 3);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 1);

        // print the base length
        System.out.printf ("%-12s%d\n", "base length: ", graph.getBaseLength());

        // print the minimum spanning tree (MST) value
        System.out.printf ("%-10s%d\n", "MST value: ", graph.getMSTValue()); // expected output of this test case: 10

        // print the list
        graph.displayList();
    }
}