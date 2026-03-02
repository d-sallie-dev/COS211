package _14practical;

import _14practical.HashComparisons.Entry;
import java.util.LinkedList;

// Daiyaan Sallie - 4503224

public class chainedHash {
    // Array of linked lists to store Entry objects
    private LinkedList<Entry>[] table;
    private int m;

    @SuppressWarnings("unchecked")
    public chainedHash(int m) {
        this.m = m;
        // The table has a list of initially empty pointers 
        // We use size m + 1 for range [1..m]
        this.table = new LinkedList[m + 1];
        for (int i = 1; i <= m; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public int hash(String key) {
        int h = Math.abs(key.hashCode()) % m;
        return h + 1; // Result i is in [1..m]
    }

    // Add to list or update if exists
    public void insert(String key, String value) {
        int h = hash(key);
        int intKey = Integer.parseInt(key);

        // Search the list to ensure the key is not already there [cite: 38]
        for (Entry e : table[h]) {
            if (e.key == intKey) {
                e.value = value; // Update value if found 
                return;
            }
        }
        // If not found, add to the list [cite: 38]
        table[h].add(new Entry(intKey, value));
    }

    // Search the specific chain at index h
    public String lookup(String key) {
        int h = hash(key);
        int intKey = Integer.parseInt(key);

        for (Entry e : table[h]) {
            if (e.key == intKey) {
                return e.value;
            }
        }
        return null; // Return null if not found
    }

    // Deletes the pair and returns the value
    public String remove(String key) {
        int h = hash(key);
        int intKey = Integer.parseInt(key);
        
        // find and remove key's value
        for (int i = 0; i < table[h].size(); i++) {
            Entry e = table[h].get(i);
            if (e.key == intKey) {
                String val = e.value;
                table[h].remove(i);
                return val;
            }
        }
        return null;
    }

    public boolean isInTable(String key) {
        return lookup(key) != null;
    }

    public boolean isEmpty() {
        for (int i = 1; i <= m; i++) {
            if (!table[i].isEmpty()) return false;
        }
        return true;
    }
}
