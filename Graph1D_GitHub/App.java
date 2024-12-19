public class App {
    /** test used main function */
    public static void main(String[] args) {

        // create instance Graph1D
        Graph1D graph1d = new Graph1D(4);

        // add edges
        graph1d.addEdge(0, 1, 10);
        graph1d.addEdge(0, 2, 8);
        graph1d.addEdge(0, 3, 7);
        graph1d.addEdge(1, 2, 6);
        graph1d.addEdge(1, 3, 14);
        graph1d.addEdge(2, 3, 2);

        // display the initial graph
        graph1d.displayGraph();

        // get the value of MST
        System.out.println (graph1d.getMSTValue());

        // generate MST
        graph1d.generateMST_Fast();

        // display the graph after MST generation
        graph1d.displayGraph();
    }
}