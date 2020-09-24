package Text.UTF32Encoding;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.io.FileMode;
import system.io.FileStream;
import system.io.StreamReader;
import system.text.UTF32Encoding;

public class Example3 {
    public static void main(String[] arg) {
        try {
            // Create a UTF-32 encoding that supports a BOM.
            UTF32Encoding enc = new UTF32Encoding();

            // A Unicode String with two characters outside an 8-bit code range.
            String s = "This Unicode String has 2 characters " +
                    "outside the ASCII range: \n" +
                    "Pi (\u03A0), and Sigma (\u03A3).";
            Console.WriteLine("Original String:");
            Console.WriteLine(s);
            Console.WriteLine();

            // Encode the String.
            BigByte[] encodedBytes = enc.GetBytes(s);
            Console.WriteLine("The encoded String has {0} bytes.\n",
                    encodedBytes.length);

            // Write the bytes to a file with a BOM.
            FileStream fs = new FileStream(".\\UTF32Encoding.txt", FileMode.Create);
            BigByte[] bom = enc.GetPreamble();
            fs.Write(bom, 0, bom.length);
            fs.Write(encodedBytes, 0, encodedBytes.length);
            Console.WriteLine("Wrote {0} bytes to the file.\n", fs.get_Length());
            fs.Close();

            // Open the file using StreamReader.
            StreamReader sr = new StreamReader(".\\UTF32Encoding.txt");
            String newString = sr.ReadToEnd();
            sr.Close();
            Console.WriteLine("String read using StreamReader:");
            Console.WriteLine(newString);
            Console.WriteLine();

            // Open the file as a binary file and decode the bytes back to a String.
            fs = new FileStream(".\\Utf32Encoding.txt", FileMode.Open);
            BigByte[] bytes = new BigByte[(int) fs.get_Length()];
            fs.Read(bytes, 0, (int) fs.get_Length());
            fs.Close();

            String decodedString = enc.GetString(bytes);
            Console.WriteLine("Decoded bytes from binary file:");
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
//    The encoded String has 340 bytes.
//
//    Wrote 344 bytes to the file.
//
//    String read using StreamReader:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
//
//    Decoded bytes from binary file:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
