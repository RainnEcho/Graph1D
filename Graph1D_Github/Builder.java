
import java.util.*;
@SuppressWarnings("ConvertToTryWithResources")

class Builder {
    /** build an instance Graph1D automatically by a Scanner */
    public static void buildGraph1D() {

        Scanner scan = new Scanner(System.in);

        // set the capacity
        System.out.printf ("Please input the capacity:\n");
        int capacity = scan.nextInt();
        scan.nextLine();
        Graph1D instanceGraph1D = new Graph1D(capacity);

        boolean checkpoint = true;

        while (checkpoint) {
            // input a command
            System.out.printf ("Enter Command in Console (add/remove/end) ->\n");
            String cmd = scan.nextLine().trim().toLowerCase();

            switch (cmd) {
                // add vertices
                case "add":
                    System.out.printf ("Add 2 Vertices:\n");
                    int vertex1 = scan.nextInt();
                    int vertex2 = scan.nextInt();
                    if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
                    scan.nextLine();

                    System.out.printf ("Add Weight:\n");
                    int weight = scan.nextInt();
                    if (weight <= 0) throw new IllegalArgumentException("invalid weight");
                    scan.nextLine();
                    
                    instanceGraph1D.addEdge(vertex1, vertex2, weight);
                    System.out.printf ("Added\n");
                    
                    break;

                // remove vertices
                case "remove":
                    System.out.printf ("Remove 2 Vertices:\n");
                    int _vertex1 = scan.nextInt();
                    int _vertex2 = scan.nextInt();
                    if (_vertex2 <= _vertex1) throw new IllegalArgumentException("illegal parameter sequence");
                    scan.nextLine();

                    instanceGraph1D.removeEdge(_vertex1, _vertex2);
                    System.out.printf ("Removed\n");
                    
                    break;

                // finish operation
                case "end":
                    checkpoint = false;
                    break;

                // invalid input exception
                default:
                    System.out.printf ("Invalid Command (Exception in Cases: add/remove/end)\n");
                    break;
            }
        }
        
        scan.close();
    }
}