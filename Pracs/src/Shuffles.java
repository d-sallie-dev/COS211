import javax.xml.transform.Source;
import java.util.Arrays;

public class Shuffles {
    public int [] SlowShuffle(int N, int [] numbers) {
        int [] shuffled = new int[N];
        boolean [] isNotPresent = new boolean[N];
        Arrays.fill(isNotPresent, true);

        int i = 0;
        while (i < N) {
            int r = (int)(Math.random()*N) + 1;
            boolean numExists = false; // to track if the random generated num at position i already exists

            while (numExists == false) {
                if (isNotPresent[r - 1]) {
                    shuffled[i] = r;
                    isNotPresent[r - 1] = false;
                    numExists = true;
                    i += 1;
                } else {
                    r = (int) (Math.random() * N) + 1;
                }
            }
        }

        return shuffled;
    }

    public int [] BiasedShuffle (int N, int [] numbers) {
        int [] shuffled = new int[N];

        for (int i = 0; i < N; i++) {
            shuffled[i] = i;
        }

        int r;
        for (int i = 0; i < N; i++) {
            r = (int) (Math.random() * N) + 1;
            shuffled[i] = r;
        }

        return shuffled;
    }

    public int [] UnbiasedShuffle (int N, int [] numbers) {
        int [] shuffled = new int[N];

        for (int i = 0; i < N; i++) {}

        return shuffled;
    }

    public static void main(String[] args) {
        int [] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Shuffles sh = new Shuffles();
        System.out.println(Arrays.toString(sh.SlowShuffle(10, numbers)));

        System.out.println(Arrays.toString(sh.BiasedShuffle(10, numbers)));

        /*
        int [] B = {1, 2, 3};

        System.out.println(Arrays.toString(B));
        */
    }
}
