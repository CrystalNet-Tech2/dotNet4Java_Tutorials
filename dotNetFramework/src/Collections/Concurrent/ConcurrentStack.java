package Collections.Concurrent;

import com.dotNet4Java.TRefObject;
import system.Action;
import system.Console;
import system.collections.concurrent.GenericConcurrentStack;
import system.threading.tasks.Task;

public class ConcurrentStack {
    // Demonstrates:
    //      ConcurrentStack<T>.Push();
    //      ConcurrentStack<T>.TryPeek();
    //      ConcurrentStack<T>.TryPop();
    //      ConcurrentStack<T>.Clear();
    //      ConcurrentStack<T>.IsEmpty;
    public static void main(String[] arg) {
        try {
            int items = 10000;

            GenericConcurrentStack<Integer> stack = new GenericConcurrentStack<>(new String[]{"System.Int32"});

            // Create an action to push items onto the stack
            Action pusher = new Action() {
                @Override
                public void invoke() throws Exception {
                    for (int i = 0; i < items; i++) {
                        stack.Push(i);
                    }
                }
            };

            // Run the action once
            pusher.invoke();

            TRefObject<Integer> result = new TRefObject<>();
            if (stack.TryPeek(result)) {
                Console.WriteLine("TryPeek() saw {0} on top of the stack.", result.argValue);
            } else {
                Console.WriteLine("Could not peek most recently added number.");
            }

            // Empty the stack
            stack.Clear();

            if (stack.get_IsEmpty()) {
                Console.WriteLine("Cleared the stack.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}