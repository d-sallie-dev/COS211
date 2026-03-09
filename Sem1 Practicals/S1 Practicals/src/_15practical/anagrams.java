

import java.util.Arrays;

public class anagrams {
    static String signature (String word) {
        char[] charArray = word.toCharArray();

        Arrays.sort(charArray);
        String sig = new String(charArray); // the signature
        System.out.println("sig: " + sig);

        return sig;
    }

    public static void main(String[] args) {
        signature("dear");

        if (args.length < 1) {
            System.out.println("Usage: ./anagrams inputfile.\n\tYou gave no arguments");
            return;
        }
        
        String inputFileName = args[0]; // should be a textfile name

        System.out.println();
    }
}
