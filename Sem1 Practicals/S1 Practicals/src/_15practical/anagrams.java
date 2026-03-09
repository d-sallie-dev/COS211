

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class anagrams {
    static String signature (String word) {
        char[] charArray = word.toCharArray();

        Arrays.sort(charArray);
        String sig = new String(charArray); // the signature

        return sig;
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: ./anagrams inputfile.\n\tYou gave no arguments");
            return;
        }
        
        String inputFileName = args[0]; // should be a textfile name

        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        // read and organise file data
        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Split by anything that is NOT a letter
                String[] words = line.split("[^a-zA-Z]+");

                for (String word : words) {
                    if (word.isEmpty()) continue;
                    
                    String lowercaseWord = word.toLowerCase();
                    wordFrequencyMap.put(lowercaseWord, wordFrequencyMap.getOrDefault(lowercaseWord, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
            return;
        }

        System.out.println();

        // build the anagram mapping
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String word : wordFrequencyMap.keySet()) {
            String signature = signature(word);
            anagramMap.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }
}
        