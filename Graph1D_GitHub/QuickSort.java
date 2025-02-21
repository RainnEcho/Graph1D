import java.util.*;

public class QuickSort {
    /* the directly accessed function, sort the map by element, the list follows the map */
    public static void quickSortJoint(int[] map, List<Integer> list) {
        helper(map, list, 0, map.length - 1);
    }

    /* the recursive sorting function */
    private static void helper(int[] map, List<Integer> list, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(map, list, low, high);
            helper(map, list, low, pivotIndex - 1);
            helper(map, list, pivotIndex + 1, high);
        }
    }

    /* the function for segmentation */
    private static int partition(int[] map, List<Integer> list, int low, int high) {
        int pivot = map[high];
        int i = low - 1;
        for(int j = low; j < high; j++) {
            if(map[j] <= pivot) {
                i++;
                swap(map, list, i, j);
            }
        }
        swap(map, list, i + 1, high);
        return i + 1;
    }

    /* the function to replace two elements with each other */
    private static void swap(int[] map, List<Integer> list, int i, int j) {
        int tmpMap = map[i];
        map[i] = map[j];
        map[j] = tmpMap;
        int tmpList = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmpList);
    }
}