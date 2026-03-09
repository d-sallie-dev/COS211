// Daiyaan Sallie - 4503224

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

        // Read and organise file data
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

        // Build the anagram mapping
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String word : wordFrequencyMap.keySet()) {
            String signature = signature(word);
            anagramMap.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }

        // Process anagrams for output
        List<String> formattedAnagramLines = new ArrayList<>();
        for (String signature : anagramMap.keySet()) {
            List<String> matches = anagramMap.get(signature);
            
            // Only output if the group contains actual anagrams (more than 1 word)
            if (matches.size() > 1) {
                String spaceSeparatedWords = String.join(" ", matches);
                formattedAnagramLines.add(spaceSeparatedWords + "\\\\");

                String rotatingString = spaceSeparatedWords;
                for (int i = 0; i < matches.size() - 1; i++) {
                    int firstSpace = rotatingString.indexOf(' ');
                    rotatingString = rotatingString.substring(firstSpace + 1) + " " + rotatingString.substring(0, firstSpace);
                    formattedAnagramLines.add(rotatingString + "\\\\");
                }
            }
        }

        // Sort results alphabetically
        Collections.sort(formattedAnagramLines);

        // Generate the LaTeX formatted output file
        File outputDir = new File("latex");
        if (!outputDir.exists()) outputDir.mkdir();

        try (PrintWriter writer = new PrintWriter(new FileWriter("latex/theAnagrams.tex"))) {
            char currentHeaderLetter = ' ';
            for (String entry : formattedAnagramLines) {
                char firstChar = entry.charAt(0);
                
                // Add alphabetical section headers
                if (Character.toLowerCase(firstChar) != Character.toLowerCase(currentHeaderLetter)) {
                    currentHeaderLetter = firstChar;
                    writer.printf("\n\\vspace{14pt}\n\\noindent\\textbf{\\Large %s}\\\\*[+12pt]\n", 
                                  Character.toUpperCase(currentHeaderLetter));
                }
                writer.println(entry);
            }
            System.out.println("Process complete. Output saved to latex/theAnagrams.tex");
        } catch (IOException e) {
            System.err.println("Error writing the LaTeX file: " + e.getMessage());
        }
    }
}
        