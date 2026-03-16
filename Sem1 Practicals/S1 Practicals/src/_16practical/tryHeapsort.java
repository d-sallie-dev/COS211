package _16practical;

/**
 * Project: Practical 6 - Heapsort Bottom-Up vs Top-Down
 */

public class tryHeapsort {
    private String[] A;
    private int n;

    // Helper to swap two elements in the array 
    private void swap(int i, int j) {
        String temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // --- PART A: BOTTOM-UP ---
    public void heapify(int i, int size) {
        int left = 2 * i + 1;
        while (left < size) {
            int child = left;
            // Check if right child exists and is "larger" (alphabetically later)
            if (child + 1 < size && A[child].compareTo(A[child + 1]) < 0) {
                child++;
            }
            // If parent is already larger than the biggest child, we are done
            if (A[i].compareTo(A[child]) >= 0) break;

            swap(i, child);
            i = child;
            left = 2 * i + 1;
        }
    }

    public void buildBottomUp(String[] words) {
        this.A = words.clone();
        this.n = A.length;
        // Start from the last parent and move up
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    // --- PART B: TOP-DOWN ---
    public void buildTopDown(String[] words) {
        this.A = new String[words.length];
        this.n = 0;
        for (String word : words) {
            insert(word); // Repeatedly insert
        }
    }

    private void insert(String word) {
        A[n] = word;
        int current = n;
        n++;
        // "Swim" up to the correct spot
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (A[current].compareTo(A[parent]) > 0) {
                swap(current, parent);
                current = parent;
            } else break;
        }
    }

    // --- SHARED SORTING LOGIC ---
    public void sort() {
        for (int i = A.length - 1; i > 0; i--) {
            swap(0, i); // Move top (largest) to the end
            heapify(0, i); // Fix the remaining heap
        }
    }

    public static void main(String[] args) {
        // 1. Example words for testing
        String[] myWords = {"abba", "able", "aboard", "ac", "ache", "act", "acts",
            "arce", "being", "blake", "blow", "boats"}; // Example 

        tryHeapsort heapS = new tryHeapsort();

        // 2. Time Bottom-Up
        long start = System.nanoTime();
        heapS.buildBottomUp(myWords);
        heapS.sort();
        long end = System.nanoTime();
        System.out.println("Bottom-Up Time: " + (end - start) + " ns");

        // 3. Time Top-Down
        start = System.nanoTime();
        heapS.buildTopDown(myWords);
        heapS.sort();
        end = System.nanoTime();
        System.out.println("Top-Down Time: " + (end - start) + " ns");
    }
}
