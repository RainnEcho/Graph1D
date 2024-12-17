public class Formulas {
    public static int arrLength_Formula (int base_len) {
        return (base_len * (base_len + 1)) / 2;
    }

    public static int toIndex_Formula (int base_len, int vertex1, int vertex2) {
        return ((base_len + base_len - (vertex1 - 1)) * vertex1) / 2 + vertex2 - vertex1 - 1;
    }

    public static int toCoordinate_Formula_Vertex1 (int idx, int base_len) {
        double discriminant = Math.sqrt((2 * base_len + 1) * (2 * base_len + 1) - 8 * idx);
        return (int) ((2 * base_len + 1 - discriminant) / 2);
    }

    public static int toCoordinate_Formula_Vertex2 (int idx, int base_len, int vertex1) {
        int rowStart = vertex1 * (2 * base_len - vertex1 + 1) / 2;
        return vertex1 + 1 + (idx - rowStart);
    }
}