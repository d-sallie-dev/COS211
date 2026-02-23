import java.lang.Math.*;
import java.io.*;
import java.text.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class timeMethods {
    public static int N = 32654;
    public static void main(String args[]){

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0, time;
        double totalTime = 0.0;
        int n = N;
        int repetition, repetitions = 1;

        // Get our array of data (since this is a mock scenario, only the
        // keys of the data is retrieved and not the string value associated
        int[] data = getData();

        Random rand = new Random();

        runTime = 0;
        for(repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();

            int randomKey = rand.nextInt(1, N + 1);
            System.out.println("Random Key: " + randomKey);

            // call the procedures to time here:
            linearsearch(randomKey, data);
            binarysearch(randomKey, data);

            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTime += time;
            runTime2 += (time*time);
        }

        double aveRuntime = runTime/repetitions;
        double stdDeviation =
                Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

        System.out.printf("\n\n\fStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTime/1000 + "s.");
        System.out.println("Total time\u00b2  =           " + runTime2);
        System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000)
                + '\u00B5' + "s. ");

        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println();
    }

    /**
     * Reads the text file, extracts the integer value and stores into an integer array
     * @return data array
     */
    static int[] getData () {
        int[] data = new int[N];
        int i = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Pracs/13practical/src/ulysses.numbered"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Extract data from each line
                String[] parts = line.split(" ");

                if (i >= N) break; // data array should not go out of index bound
                data[i] = Integer.parseInt(parts[0].trim());

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    static int linearsearch(int key, int data[]) {
        int length = data.length;

        for (int i = 0; i < length; i++) {
            if (data[i] == key) {
                return i;
            }
        }

        return -1;
    }

    static int binarysearch(int key, int data[]) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (data[mid] == key) {
                return mid;
            } else if (data[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}