import java.util.*;

public class QuickSort {
    public static void quickSort (int[] map, List<Integer> list) {
        quickSortHelper(map, list, 0, map.length - 1);
    }

    private static void quickSortHelper (int[] map, List<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition (map, list, low, high);
            quickSortHelper (map, list, low, pivotIndex - 1);
            quickSortHelper (map, list, pivotIndex + 1, high);
        }
    }

    private static int partition (int[] map, List<Integer> list, int low, int high) {
        int pivot = map[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (map[j] <= pivot) {
                i++;
                swap (map, list, i, j);
            }
        }
        swap (map, list, i + 1, high);
        return i + 1;
    }

    private static void swap (int[] map, List<Integer> list, int i, int j) {
        int tempMap = map[i];
        map[i] = map[j];
        map[j] = tempMap;

        int tempList = list.get(i);
        list.set (i, list.get(j));
        list.set (j, tempList);
    }
}