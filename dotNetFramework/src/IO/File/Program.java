package IO.File;

import system.Console;
import system.io.File;
import system.io.StreamReader;
import system.io.StreamWriter;

public class Program {
    public static void main(String[] arg) {
        String path = "c:\\temp\\MyTest.txt";

        try {
            if (!File.Exists(path)) {
                // Create a file to write to.
                StreamWriter sw = File.CreateText(path);
                try {
                    sw.WriteLine("Hello");
                    sw.WriteLine("And");
                    sw.WriteLine("Welcome");
                } finally {
                    sw.Dispose();
                    sw.close();
                }
            }

            // Open the file to read from.
            StreamReader sr = File.OpenText(path);
            try {
                while (!sr.get_EndOfStream())
                    Console.WriteLine(sr.ReadLine());
            } finally {
                sr.Dispose();
                sr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}