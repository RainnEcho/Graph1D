import java.util.*;

public class Graph1D {

    // member variabels
    public int base_len;
    public int[] map;
    public List<List<Integer>> list;

    // default constructor
    public Graph1D () {}

    // constructor
    public Graph1D (int capacity) {
        base_len = capacity - 1;
        int arr_len = (int) (Math.pow(base_len, 2) + base_len) / 2;
        map = new int[arr_len];
        Arrays.fill(map, Integer.MAX_VALUE);
        list = new ArrayList<>(arr_len);
        for (int i = 0; i < arr_len; i++) list.add(new ArrayList<>(Arrays.asList(null, null)));
    }

    /** function: return the base length */
    public int getBaseLength () { return base_len; }

    /** function: add two vertices with a weighted edge */
    public void addEdge (int vertex1, int vertex2, int weight) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
        int index = (vertex1 == 0) 
            ? vertex2 - 1 
            : (vertex2 - vertex1) + (base_len * vertex1) - (int) Math.pow((vertex1 - 1), 2) - 1;

        map[index] = weight;

        list.get(index).set(0, vertex1);
        list.get(index).set(1, vertex2);
    }

    /** function: remove two vertices */
    public void removeEdge (int vertex1, int vertex2) {
        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
        int index = (vertex1 == 0) 
            ? vertex2 - 1 
            : (vertex2 - vertex1) + (base_len * vertex1) - (int) Math.pow((vertex1 - 1), 2) - 1;
        map[index] = Integer.MAX_VALUE;

        list.get(index).set(0, null);
        list.get(index).set(1, null);
    }

    /** function: get the sum of minimum spanning tree (MST) */
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
        Graph1D graph = new Graph1D(4);

        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 3);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 9);

        System.out.printf ("%-12s%d", "base length: ", graph.getBaseLength());

        System.out.printf ("%-10s%d", "MST value: ", graph.getMSTValue());
    }
}