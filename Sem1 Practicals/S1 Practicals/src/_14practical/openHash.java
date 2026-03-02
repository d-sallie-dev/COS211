package _14practical;
import _14practical.HashComparisons.Entry;

// Daiyaan Sallie - 4503224

public class openHash {
    private Entry[] table;
    private int m;
    private int size;
    private final int EMPTY_KEY = -1; // Represents an empty slot

    public openHash(int m) {
        this.m = m;
        // The table is d[m+1]. We do not necessarily use d[0] 
        this.table = new Entry[m + 1]; 
        this.size = 0;
    }

    public int hash(String key) {
        // Ensure the index is positive
        int h = Math.abs(key.hashCode()) % m; 
        return h + 1; // Ensures i is in [1..m] range
    }

    public void insert(String key, String value) {
        int h = hash(key);
        int p = 31; // A suitable prime for stepping 
        
        for (int r = 0; r < m; r++) {
            // Formula: ik = (ij + r * p) mod m + 1 
            int i = ((h + r * p - 1) % m) + 1;

            if (table[i] == null) {
                table[i] = new Entry(Integer.parseInt(key), value);
                size++;
                return;
            }
            
            // If key already exists
            if (String.valueOf(table[i].key).equals(key)) {
                table[i].value = value;
                return;
            }
        }
    }

    public String lookup(String key) {
        int h = hash(key);
        int p = 31;
        for (int r = 0; r < m; r++) {
            int i = ((h + r * p - 1) % m) + 1;
            
            // Reaching an empty entry means item is not in the list
            if (table[i] == null) return null;
            
            if (String.valueOf(table[i].key).equals(key)) {
                return table[i].value;
            }
        }
        return null;
    }

    public String remove(String key) {
        int h = hash(key);
        int p = 31;
        for (int r = 0; r < m; r++) {
            int i = ((h + r * p - 1) % m) + 1;
            if (table[i] == null) return null;
            
            if (String.valueOf(table[i].key).equals(key)) {
                String val = table[i].value;
                // Mark as "Deleted" using a special Entry
                table[i] = new Entry(-2, "DELETED"); 
                size--;
                return val;
            }
        }
        return null;
    }

    public boolean isFull() { return size >= m; }
    public boolean isEmpty() { return size == 0; }
    public boolean isInTable(String key) { return lookup(key) != null; }
}