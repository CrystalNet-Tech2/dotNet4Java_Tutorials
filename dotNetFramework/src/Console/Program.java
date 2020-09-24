package Console;

import system.Console;

public class Program {
    public static void main(String[] arg) {
        try {
            Console.WriteLine("          Hello! Welcome to dotNet4Java.          ");
            Console.WriteLine("==================================================");
            Console.WriteLine("The program displays the String Hello World!");
            Console.WriteLine();
            Console.WriteLine("Hello World!");
            Console.WriteLine("Press any key to exit.");
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}