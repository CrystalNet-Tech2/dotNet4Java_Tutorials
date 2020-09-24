package IO.StreamReader;

import system.Console;
import system.io.StreamReader;

public class Program {
    public static void main(String[] arg) {
        try {
            // Create an instance of StreamReader to read from a file.
            // The using statement also closes the StreamReader.
            StreamReader sr = new StreamReader("C:\\Temp\\TestFile.txt");
            try {
                String line;
                // Read and display lines from the file until the end of
                // the file is reached.
                while ((line = sr.ReadLine()) != null) {
                    Console.WriteLine(line);
                }
            } finally {
                sr.Dispose();
                sr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
