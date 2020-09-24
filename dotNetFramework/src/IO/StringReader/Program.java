package IO.StringReader;

import system.Console;
import system.io.StringReader;
import system.text.StringBuilder;

public class Program {
    public static void main(String[] args) {
        try {
            ReadCharacters();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void ReadCharacters() throws Exception {
        StringBuilder stringToRead = new StringBuilder();
        stringToRead.AppendLine("Characters in 1st line to read");
        stringToRead.AppendLine("and 2nd line");
        stringToRead.AppendLine("and the end");

        StringReader reader = new StringReader(stringToRead.ToString());
        try {
            String readText = reader.ReadToEnd();
            Console.WriteLine(readText);
        } finally {
            reader.Dispose();
            reader.close();
        }
    }
}
