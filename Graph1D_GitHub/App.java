public class App {
    /* a sample test-used main method */
    public static void main(String[] args) {
        // create instance Graph1D
        Graph1D graph = new Graph1D(4);

        // add edges
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(0, 3, 3);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 14);
        graph.addEdge(2, 3, 2);

        // display the initial graph
        graph.displayGraph();

        // get the value of MST
        System.out.printf("MST Value: %d\n", graph.getMSTValue());

        // generate MST
        graph.generateMST();

        // display the graph after MST generation
        graph.displayGraph();
    }
}