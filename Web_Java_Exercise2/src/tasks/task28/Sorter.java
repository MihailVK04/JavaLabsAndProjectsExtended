package tasks.task28;

import java.util.*;

// Strategy interface
interface SortStrategy {
    void sort(int[] array);
    String name();
}

class BubbleSort implements SortStrategy {
    public void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - i - 1; j++)
                if (a[j] > a[j+1]) { int t = a[j]; a[j] = a[j+1]; a[j+1] = t; }
    }
    public String name() { return "Bubble Sort"; }
}

class SelectionSort implements SortStrategy {
    public void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) if (a[j] < a[min]) min = j;
            int t = a[min]; a[min] = a[i]; a[i] = t;
        }
    }
    public String name() { return "Selection Sort"; }
}

// Context class — delegates to the strategy
class Sorter {
    private SortStrategy strategy;

    Sorter(SortStrategy strategy) { this.strategy = strategy; }

    void setStrategy(SortStrategy strategy) { this.strategy = strategy; }

    void sortAndPrint(int[] data) {
        int[] copy = data.clone();
        strategy.sort(copy);
        System.out.println(strategy.name() + ": " + Arrays.toString(copy));
    }
}

class Main {
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        System.out.println("Original: " + Arrays.toString(data));

        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sortAndPrint(data);

        sorter.setStrategy(new SelectionSort()); // swap at runtime!
        sorter.sortAndPrint(data);
    }
}
