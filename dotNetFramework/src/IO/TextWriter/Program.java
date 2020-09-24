package IO.TextWriter;

import system.io.File;
import system.io.StreamWriter;

public class Program {
    public static void main(String[] args) {
        try {
            WriteCharacters();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void WriteCharacters() throws Exception {
        StreamWriter writer = File.CreateText("C:\\Temp\\newfile.txt");
        try {
            writer.WriteLine("First line of example");
            writer.WriteLine("and second line");
        } finally {
            writer.Dispose();
            writer.close();
        }
    }
}
