package Collections.Generics;

import com.dotNet4Java.TClrObjects;
import system.Console;
import system.collections.generic.GenericIEnumerable;
import system.collections.generic.GenericList;
import system.collections.generic.GenericQueue;

public class Queue {
    public static void main(String[] arg) {
        try {
            GenericQueue<String> numbers = new GenericQueue<>(new String[]{"System.String"});
            numbers.Enqueue("one");
            numbers.Enqueue("two");
            numbers.Enqueue("three");
            numbers.Enqueue("four");
            numbers.Enqueue("five");

            // A queue can be enumerated without disturbing its contents.
            for (String number : numbers.ToArray().toArray()) {
                Console.WriteLine(number);
            }

            Console.WriteLine("\nDequeuing '{0}'", numbers.Dequeue());
            Console.WriteLine("Peek at next item to dequeue: {0}",
                    numbers.Peek());
            Console.WriteLine("Dequeuing '{0}'", numbers.Dequeue());

            // Create a copy of the queue, using the ToArray method and the
            // constructor that accepts an IEnumerable<T>.
            GenericQueue<String> queueCopy = new GenericQueue<>(new String[]{"System.String"}, TClrObjects.toObject(numbers.ToArray().toArray(), GenericIEnumerable.class));

            Console.WriteLine("\nContents of the first copy:");
            for (String number : queueCopy.ToArray().toArray()) {
                Console.WriteLine(number);
            }

            // Create an array twice the size of the queue and copy the
            // elements of the queue, starting at the middle of the
            // array.
            String[] array2 = new String[numbers.get_Count() * 2];
            numbers.CopyTo(array2, numbers.get_Count());

            // Create a second queue, using the constructor that accepts an
            // IEnumerable(Of T).
            GenericQueue<String> queueCopy2 = new GenericQueue<>(new String[]{"System.String"}, TClrObjects.toObject(array2, GenericIEnumerable.class));

            Console.WriteLine("\nContents of the second copy, with duplicates and nulls:");
            for (String number : queueCopy2.ToArray().toArray()) {
                Console.WriteLine(number);
            }

            Console.WriteLine("\nqueueCopy.Contains(\"four\") = {0}",
                    queueCopy.Contains("four"));

            Console.WriteLine("\nqueueCopy.Clear()");
            queueCopy.Clear();
            Console.WriteLine("\nqueueCopy.Count = {0}", queueCopy.get_Count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}