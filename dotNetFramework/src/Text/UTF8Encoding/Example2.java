package Text.UTF8Encoding;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.io.FileMode;
import system.io.FileStream;
import system.io.StreamReader;
import system.text.Encoding;
import system.text.UTF8Encoding;

public class Example2 {
    public static void main(String[] arg) {
        try {
            // Create a UTF-8 encoding that supports a BOM.
            Encoding utf8 = new UTF8Encoding(true);

            // A Unicode String with two characters outside an 8-bit code range.
            String unicodeString =
                    "This Unicode String has 2 characters outside the " +
                            "ASCII range:\n" +
                            "Pi (\u03A0)), and Sigma (\u03A3).";
            Console.WriteLine("Original String:");
            Console.WriteLine(unicodeString);
            Console.WriteLine();

            // Encode the String.
            BigByte[] encodedBytes = utf8.GetBytes(unicodeString);
            Console.WriteLine("The encoded String has {0} bytes.",
                    encodedBytes.length);
            Console.WriteLine();

            // Write the bytes to a file with a BOM.
            FileStream fs = new FileStream(".\\UTF8Encoding.txt", FileMode.Create);
            BigByte[] bom = utf8.GetPreamble();
            fs.Write(bom, 0, bom.length);
            fs.Write(encodedBytes, 0, encodedBytes.length);
            Console.WriteLine("Wrote {0} bytes to the file.", fs.get_Length());
            fs.Close();
            Console.WriteLine();

            // Open the file using StreamReader.
            StreamReader sr = new StreamReader(".\\UTF8Encoding.txt");
            String newString = sr.ReadToEnd();
            sr.Close();
            Console.WriteLine("String read using StreamReader:");
            Console.WriteLine(newString);
            Console.WriteLine();

            // Open the file as a binary file and decode the bytes back to a String.
            fs = new FileStream(".\\UTF8Encoding.txt", FileMode.Open);
            BigByte[] bytes = new BigByte[(int) fs.get_Length()];
            fs.Read(bytes, 0, (int) fs.get_Length());
            fs.Close();

            String decodedString = utf8.GetString(bytes);
            Console.WriteLine("Decoded bytes:");
            Console.WriteLine(decodedString);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}

// The example displays the following output:
//    Original String:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
//
//    The encoded String has 88 bytes.
//
//    Wrote 91 bytes to the file.
//
//    String read using StreamReader:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
//
//    Decoded bytes:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
//
