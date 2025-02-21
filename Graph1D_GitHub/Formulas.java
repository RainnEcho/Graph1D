public class Formulas {
    /* the formula to calculate the length of the 1D array */
    public static int toArrayLength(int baseLength) {
        return (baseLength * (baseLength + 1)) / 2;
    }

    /* the formula to convert a 2D coordinate to an 1D index */
    public static int toIndex(int baseLength, int v1, int v2) {
        return ((baseLength + baseLength - (v1 - 1)) * v1) / 2 + v2 - v1 - 1;
    }

    /* the formula to calculate vertex1 reversely by the index */
    public static int toCoordinateV1(int idx, int baseLength) {
        double discriminant = Math.sqrt((2 * baseLength + 1) * (2 * baseLength + 1) - 8 * idx);
        return (int) ((2 * baseLength + 1 - discriminant) / 2);
    }

    /* the formula to calculate vertex2 reversely by the index */
    public static int toCoordinateV2(int idx, int baseLength, int v1) {
        int rowStart = v1 * (2 * baseLength - v1 + 1) / 2;
        return v1 + 1 + (idx - rowStart);
    }
}