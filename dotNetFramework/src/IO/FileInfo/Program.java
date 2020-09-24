package IO.FileInfo;

import system.Console;
import system.io.FileInfo;
import system.io.Path;
import system.io.StreamReader;
import system.io.StreamWriter;

public class Program {
    public static void main(String[] arg) {
        try {

            String path = Path.GetTempFileName();
            FileInfo fi1 = new FileInfo(path);

            // Create a file to write to.
            StreamWriter sw = fi1.CreateText();
            try {
                sw.WriteLine("Hello");
                sw.WriteLine("And");
                sw.WriteLine("Welcome");
            } finally {
                sw.Dispose();
                sw.close();
            }

            // Open the file to read from.
            StreamReader sr = fi1.OpenText();
            try {
                while (!sr.get_EndOfStream())
                    Console.WriteLine(sr.ReadLine());
            } finally {
                sr.Dispose();
                sr.close();
            }

            String path2 = Path.GetTempFileName();
            FileInfo fi2 = new FileInfo(path2);

            // Ensure that the target does not exist.
            fi2.Delete();

            // Copy the file.
            fi1.CopyTo(path2);
            Console.WriteLine("{0} was copied to {1}.", path, path2);

            // Delete the newly created file.
            fi2.Delete();
            Console.WriteLine(path2 + " was successfully deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
