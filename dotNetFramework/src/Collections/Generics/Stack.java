package Collections.Generics;

import com.dotNet4Java.TClrObjects;
import system.Console;
import system.collections.generic.GenericIEnumerable;
import system.collections.generic.GenericStack;

public class Stack {

    public static void main(String[] arg) {
        try {
            GenericStack<String> numbers = new GenericStack<>(new String[]{"System.String"});
            numbers.Push("one");
            numbers.Push("two");
            numbers.Push("three");
            numbers.Push("four");
            numbers.Push("five");

            // A stack can be enumerated without disturbing its contents.
            for (String number : numbers.ToArray().toArray()) {
                Console.WriteLine(number);
            }

            Console.WriteLine("\nPopping '{0}'", numbers.Pop());
            Console.WriteLine("Peek at next item to destack: {0}",
                    numbers.Peek());
            Console.WriteLine("Popping '{0}'", numbers.Pop());

            // Create a copy of the stack, using the ToArray method and the
            // constructor that accepts an IEnumerable<T>.
            GenericStack<String> stack2 = new GenericStack<String>(new String[]{"System.String"}, TClrObjects.toObject(numbers.ToArray().toArray(), GenericIEnumerable.class));

            Console.WriteLine("\nContents of the first copy:");
            for (String number : stack2.ToArray().toArray()) {
                Console.WriteLine(number);
            }

            Console.WriteLine("\nstack2.Contains(\"four\") = {0}",
                    stack2.Contains("four"));

            Console.WriteLine("\nstack2.Clear()");
            stack2.Clear();
            Console.WriteLine("\nstack2.Count = {0}", stack2.get_Count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}