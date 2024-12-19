public class Formulas {
    /** the formula to calculate the length of the 1D array */
    public static int Formula_toArrayLength (int base_len) {
        return (base_len * (base_len + 1)) / 2;
    }

    /** the formula to convert a 2D coordinate to an index */
    public static int Formula_toIndex (int base_len, int vertex1, int vertex2) {
        return ((base_len + base_len - (vertex1 - 1)) * vertex1) / 2 + vertex2 - vertex1 - 1;
    }

    /** the formula to calculate vertex1 reversely by the index */
    public static int Formula_toCoordinateVertex1 (int idx, int base_len) {
        double discriminant = Math.sqrt((2 * base_len + 1) * (2 * base_len + 1) - 8 * idx);
        return (int) ((2 * base_len + 1 - discriminant) / 2);
    }

    /** the formula to calculate vertex2 reversely by the index */
    public static int Formula_toCoordinateVertex2 (int idx, int base_len, int vertex1) {
        int rowStart = vertex1 * (2 * base_len - vertex1 + 1) / 2;
        return vertex1 + 1 + (idx - rowStart);
    }
}