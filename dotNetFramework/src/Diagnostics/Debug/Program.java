package Diagnostics.Debug;

import system.Console;
import system.diagnostics.Debug;
import system.diagnostics.TextWriterTraceListener;

public class Program {

   public static void main(String[] arg) {
        try {
            Debug.get_Listeners().Add(new TextWriterTraceListener(Console.get_Out()));

            Debug.set_AutoFlush(true);
            Debug.Indent();
            Debug.WriteLine("Entering Main");
            Console.WriteLine("Hello World.");
            Debug.WriteLine("Exiting Main");
            Debug.Unindent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}