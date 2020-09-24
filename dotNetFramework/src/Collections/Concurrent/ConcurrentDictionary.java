package Collections.Concurrent;

import com.dotNet4Java.api.EClrError;
import system.Console;
import system.Environment;
import system.collections.concurrent.GenericConcurrentDictionary;

public class ConcurrentDictionary {
    // Demonstrates:
    //      ConcurrentDictionary<TKey, TValue> ctor(concurrencyLevel, initialCapacity)
    //      ConcurrentDictionary<TKey, TValue>[TKey]
    public static void main(String[] arg) {
        // We know how many items we want to insert into the ConcurrentDictionary.
        // So set the initial capacity to some prime number above that, to ensure that
        // the ConcurrentDictionary does not need to be resized while initializing it.
        int NUMITEMS = 64;
        int initialCapacity = 101;

        // The higher the concurrencyLevel, the higher the theoretical number of operations
        // that could be performed concurrently on the ConcurrentDictionary.  However, global
        // operations like resizing the dictionary take longer as the concurrencyLevel rises.
        // For the purposes of this example, we'll compromise at numCores * 2.
        int numProcs = 0;

        try {
            numProcs = Environment.get_ProcessorCount();

            int concurrencyLevel = numProcs * 2;

            // Construct the dictionary with the desired concurrencyLevel and initialCapacity
            GenericConcurrentDictionary<Integer, Integer> cd = new GenericConcurrentDictionary<>(new String[]{"System.Int32", "System.Int32"}, concurrencyLevel, initialCapacity);

            // Initialize the dictionary
            for (int i = 0; i < NUMITEMS; i++) cd.set_Item(i, i * i);

            Console.WriteLine("The square of 23 is {0} (should be {1})", cd.get_Item(23), 23 * 23);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}