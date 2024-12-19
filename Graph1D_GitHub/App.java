public class App {
    /** test used main function */
    public static void main(String[] args) {
        // create a new instance Graph1D
        Graph1D graph = new Graph1D(4);

        // add edges
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 15);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 7);

        // get MST value
        System.out.println (graph.getMSTValue());

        // generate MST
        graph.generateMST_Fast();

        // display the graph
        graph.displayGraph();
    }
}