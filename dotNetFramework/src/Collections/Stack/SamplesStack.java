package Collections.Stack;

import system.Console;
import system.collections.IEnumerable;
import system.collections.IEnumerator;
import system.collections.Stack;

public class SamplesStack {

    public static void main(String[] arg) {
        try {
            // Creates and initializes a new Collections.Stack.
            Stack myStack = new Stack();
            myStack.Push("Hello");
            myStack.Push("World");
            myStack.Push("!");

            // Displays the properties and values of the Collections.Stack.
            Console.WriteLine("myStack");
            Console.WriteLine("\tCount:    {0}", myStack.get_Count());
            Console.Write("\tValues:");
            PrintValues(myStack.AsType(IEnumerable.class));
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

myStack
    Count:    3
    Values:    !    World    Hello
*/