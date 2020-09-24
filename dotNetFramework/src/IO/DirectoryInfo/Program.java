package IO.DirectoryInfo;

import system.Console;
import system.io.DirectoryInfo;

public class Program {
    public static void main(String[] arg) {
        // Specify the directories you want to manipulate.
        try {
            DirectoryInfo di = new DirectoryInfo("C:\\Temp\\MyDir");
            // Determine whether the directory exists.
            if (di.get_Exists()) {
                // Indicate that the directory already exists.
                Console.WriteLine("That path exists already.");
                return;
            }

            // Try to create the directory.
            di.Create();
            Console.WriteLine("The directory was created successfully.");

            // Delete the directory.
            di.Delete();
            Console.WriteLine("The directory was deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
