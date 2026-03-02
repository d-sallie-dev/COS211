package _14practical;

import java.util.Random;

// Daiyaan Sallie - 4503224

public class HashComparisons {
    public static void main(String[] args) {
        int N = 1 << 20;
        int[] keys = new int[N];
        
        for (int i = 0; i < N; i++) {
            keys[i] = i + 1;
        }

        System.out.println("Commencing shuffle");
        shuffleArray(keys);

        Entry[] data = new Entry[N];

        for (int i = 0; i < N; i++) {
            data[i] = new Entry(keys[i], String.valueOf(i + 1));
        }

        // Experiment parameters
        int[] testN = {750000, 800000, 850000, 900000, 950000};
        double[] alphas = {0.75, 0.80, 0.85, 0.90, 0.95};
        int repetitions = 30;

        System.out.println("\nAverage time in seconds");
        System.out.println("Alpha\tN\tOpen hash\tChained hash");
        System.out.println("----------------------------------------------");

        for (int idx = 0; idx < alphas.length; idx++) {
            double alpha = alphas[idx];
            int currentN = testN[idx];
            int m = findNextPrime((int) (currentN / alpha));

            long totalOpen = 0;
            long totalChained = 0;

            for (int r = 0; r < repetitions; r++) {
                openHash oh = new openHash(m);
                chainedHash ch = new chainedHash(m);

                // Populate tables
                for (int j = 0; j < currentN; j++) {
                    String sKey = String.valueOf(data[j].key);
                    oh.insert(sKey, data[j].value);
                    ch.insert(sKey, data[j].value);
                }

                // Time search experiments
                long start = System.currentTimeMillis();
                for (int k = 0; k < 10000; k++) {
                    oh.lookup(String.valueOf(data[k].key));
                }
                totalOpen += (System.currentTimeMillis() - start);

                start = System.currentTimeMillis();
                for (int k = 0; k < 10000; k++) {
                    ch.lookup(String.valueOf(data[k].key));
                }
                totalChained += (System.currentTimeMillis() - start);
            }

            // Display results in seconds
            System.out.printf("%.2f\t%d\t%.6f\t%.6f\n", 
                alpha, currentN, (totalOpen / 1000.0) / repetitions, (totalChained / 1000.0) / repetitions);
        }
    }

    public static class Entry {
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static int findNextPrime(int n) {
        while (true) {
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) { isPrime = false; break; }
            }
            if (isPrime && n > 1) return n;
            n++;
        }
    }
}