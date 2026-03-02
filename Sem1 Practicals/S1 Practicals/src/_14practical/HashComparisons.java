package _14practical;

import java.util.Random;

public class HashComparisons {
    public static void main(String[] args) {
        int N = 1<<20;
        int[] keys = new int[N];
        
        for (int i = 0; i < N; i++) {
            keys[i] = i + 1;
        }

        System.out.println("Commencing shuffle");
        shuffleArray(keys);

        Entry[] data = new Entry[N];

        for (int i = 0; i < N; i++) {
            // The key is the shuffled number, the value is its rank (1..N) 
            data[i] = new Entry(keys[i], String.valueOf(i + 1));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(data[i].key + "\t" + data[i].value);
        }
    }

    static class Entry {
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Efficiently shuffles an int array in O(n) time complexity.
     * (Fisher-Yates Shuffle)
     */
    static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = rand.nextInt(i + 1);

            // Swap array[i] with the element at random index j
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
