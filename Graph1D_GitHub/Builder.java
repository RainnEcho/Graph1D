import java.util.*;

class Builder {
    /** build an instance Graph1D and conduct operations automatically */
    public static void buildGraph1D () {

        try (Scanner scan = new Scanner(System.in)) {
            System.out.printf("Please input the capacity:\n");
            int capacity = scan.nextInt();
            scan.nextLine();
            Graph1D instanceGraph1D = new Graph1D(capacity);

            boolean checkpoint = true;

            while (checkpoint) {
                System.out.printf("Enter command in console (add/remove/end) ->\n");
                String cmd = scan.nextLine().trim().toLowerCase();

                switch (cmd) {

                    // add an edge
                    case "add":
                        System.out.printf("Add 2 vertices:\n");
                        int vertex1 = scan.nextInt();
                        int vertex2 = scan.nextInt();
                        if (vertex2 <= vertex1) throw new IllegalArgumentException("illegal parameter sequence");
                        scan.nextLine();

                        System.out.printf("Add weight:\n");
                        int weight = scan.nextInt();
                        if (weight <= 0) throw new IllegalArgumentException("invalid weight");
                        scan.nextLine();

                        instanceGraph1D.addEdge(vertex1, vertex2, weight);
                        System.out.printf("Added\n");
                        break;

                    // remove an edge
                    case "remove":
                        System.out.printf("Remove 2 vertices:\n");
                        int _vertex1 = scan.nextInt();
                        int _vertex2 = scan.nextInt();
                        if (_vertex2 <= _vertex1) throw new IllegalArgumentException("illegal parameter sequence");
                        scan.nextLine();

                        instanceGraph1D.removeEdge(_vertex1, _vertex2);
                        System.out.printf("Removed\n");
                        break;

                    // finish operation
                    case "end":
                        checkpoint = false;
                        break;

                    // invalid input exception
                    default:
                        System.out.println ("Invalid command (Exception in cases: add/remove/end)");
                        break;
                }
            }
        }
    }
}