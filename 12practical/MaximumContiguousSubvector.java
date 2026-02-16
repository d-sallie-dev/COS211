
import java.util.Arrays;
import java.util.Random;

public class MaximumContiguousSubvector {
    public static void main(String[] args) {
        int n = 10^3; 
	    int[] X = new int[n];
        Random rand = new Random();

        int countP = 0;
	    int countM = 0;
        
        // Generate array of random integers
        for (int i = 0; i < n; i++) {
            int randomValue = rand.nextInt(n) + 1;
            int sign = rand.nextBoolean() ? -1: 1;

            X[i] = randomValue * sign;

            // Count positives and negatives (minuses)
            if (X[i] < 0) {
                countM++;
            } else {
                countP++;
            }

            // [randint(1,n)*(-1)**randint(2,4) for k in range(n)]
        }

        System.out.printf("countM = %d  countP = %d%n", countM, countP);
        System.out.println(Arrays.toString(X));
        
        System.out.println(mcsOn3(X));
        System.out.println(mcsOn2A(X));
        System.out.println(mcsOn2B(X));
        System.out.println(mcsOn(X));
        
        System.out.println("-".repeat(20));
    }

    /**
     * O(n^3) implementation
     */
    private static int mcsOn3(int[] X) {
        int n = X.length;
        int maxsofar = 0;
        int sum;
        int count = 0;  // to count number of repetitions

        for (int low = 0; low < n; low++) {  // [0, n)
            for (int high = low; high < n; high++) {  // [low, n)
                sum = 0;
                for (int r = low; r < high; r++) {  // [low, high)
                    count++;

                    sum += X[r];
                    if (sum > maxsofar) {
                        maxsofar = sum;
                    }
                }
            }
        }

        System.out.println("mcsOn3 Count: " + count);
        return maxsofar;
    }
 
    /**
     * O(n^2) implementation
     */
    private static int mcsOn2A(int[] X) {
        int n = X.length;
        int maxsofar = 0;
        int count = 0;

        for (int low = 0; low < n; low++) {  // [0, n)
            int sum = 0;

            for (int r = low; r < n; r++) {  // [low, n)
                count++;

                sum += X[r];
                if (sum > maxsofar) {
                    maxsofar = sum;
                }
            }
        }

        System.out.println("mcsOn2A Count: " + count);
        return maxsofar;
    }
    /**
     * O(n^2) implementation
     */
    private static int mcsOn2B(int[] X) {
        int n = X.length;
        int[] sumTo = new int[n + 1];
        int count = 0;
        
        sumTo[0] = 0;
        for (int i = 0; i < n; i++) {  // [0, n)
            sumTo[i + 1] = sumTo[i] + X[i];
        }
        
        int maxsofar = 0;
        for (int low = 0; low < n; low++) {  // [0, n)
            for (int high = low; high < n; high++) {  // [low, n)
                count++;

                int sum = sumTo[high + 1] - sumTo[low]; // sum of X[low..high]
                if (sum > maxsofar) {
                    maxsofar = sum;
                }    
            }
        }

        System.out.println("mcsOn2B Count: " + count);
        return maxsofar;
    }

    /**
     * O(n) implementation
     */ 
    private static int mcsOn(int[] X) {
        int n = X.length;
        int maxSoFar = 0;
        int maxToHere = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {  // [0, n)
            count++;

            maxToHere = Math.max(maxToHere + X[i], 0);
            maxSoFar = Math.max(maxSoFar, maxToHere);
        }

        System.out.println("mcsOn Count: " + count);
        return maxSoFar;
    }

}