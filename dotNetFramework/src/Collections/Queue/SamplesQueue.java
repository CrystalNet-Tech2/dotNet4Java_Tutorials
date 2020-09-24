package Collections.Queue;

import com.dotNet4Java.api.EClrError;
import system.Console;
import system.collections.IEnumerable;
import system.collections.IEnumerator;
import system.collections.Queue;

public class SamplesQueue {

    public static void main(String[] arg) {
        try {
            // Creates and initializes a new Collections.Queue.
            Queue myQ = new Queue();
            myQ.Enqueue("Hello");
            myQ.Enqueue("World");
            myQ.Enqueue("!");

            // Displays the properties and values of the Collections.Queue.
            Console.WriteLine("myQ");
            Console.WriteLine("\tCount:    {0}", myQ.get_Count());
            Console.Write("\tValues:");
            PrintValues(myQ.AsType(IEnumerable.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PrintValues(IEnumerable myCollection) throws Exception {
        IEnumerator enumerator = myCollection.GetEnumerator();
        while (enumerator.MoveNext())
            Console.Write("    {0}", enumerator.get_Current());

        Console.WriteLine();
    }
}

 /*
 This code produces the following output.

 myQ
     Count:    3
     Values:    Hello    World    !
*/