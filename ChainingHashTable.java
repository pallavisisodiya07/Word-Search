import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChainingHashTable<K, V> implements DeletelessDictionary<K, V> {
    private List<Item<K, V>>[] table;
    private int size;
    private static int[] primes = {11, 23, 47, 97, 197, 397};

    
    public ChainingHashTable() {
        table = (LinkedList<Item<K, V>>[]) Array.newInstance(LinkedList.class, primes[0]);
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    // Get the hash index for a given key.
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }

    //rehashing and resizing when load factor exceed
    private void rehash() {
       //System.out.println("Rehashing from " + table.length + " to new size");
        int n = 0;
        for (int prime : primes) {
            if (prime > table.length) {
                n = prime;
                break;
            }
        }
        if (n == 0) {
            n = table.length * 2 + 1;
        }
        List<Item<K, V>>[] oldTable = table;
        table = (LinkedList<Item<K, V>>[]) Array.newInstance(LinkedList.class, n);
        for (int i = 0; i < n; i++) {
            table[i] = new LinkedList<>();
        }

        size = 0; // reset size because insert() will increment it
        for (List<Item<K, V>> bucket : oldTable) {
            for (Item<K, V> item : bucket) {
                insert(item.key, item.value);
            }
        }
        //System.out.println("Rehash complete. New size: " + table.length);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public V insert(K key, V value) {
        int i = hash(key);
        for (Item<K, V> it : table[i]) {
            if (it.key.equals(key)) {
                V oldValue = it.value;
                it.value = value;
                return oldValue;
            }
        }

        // Rehash BEFORE insertion if load factor exceeds
        if ((double)(size + 1) / table.length > 1.0) {
            rehash();
            i = hash(key); 
        }

        table[i].add(new Item<>(key, value));
        size++;
        return null;
    }


    //Finds the value of with a given key.
    public V find(K key) {
        int i = hash(key);
        for (Item<K, V> item : table[i]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    public boolean contains(K key) {
        return find(key) != null;
    }

    //return the list of keys available in the hash table
    public List<K> getKeys() {
        List<K> keys = new ArrayList<>();
        for (List<Item<K, V>> bucket : table) {
            for (Item<K, V> item : bucket) {
                keys.add(item.key);
            }
        }
        return keys;
    }

    //return the list of values available in the hash table
    public List<V> getValues() {
        List<V> values = new ArrayList<>();
        for (List<Item<K, V>> bucket : table) {
            for (Item<K, V> item : bucket) {
                values.add(item.value);
            }
        }
        return values;
    }

    public int hashTableSize() {
        return table.length;
    }

    public String toString() {
        String s = "{";
        s += table[0];
        for (int i = 1; i < table.length; i++) {
            s += "," + table[i];
        }
        return s + "}";
    }
}
