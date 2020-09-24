package Text.UTF8Encoding;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.text.UTF8Encoding;

public class Example1 {
    public static void main(String[] arg) {
        try {
            // Create a UTF-8 encoding.
            UTF8Encoding utf8 = new UTF8Encoding();

            // A Unicode String with two characters outside an 8-bit code range.
            String unicodeString =
                    "This Unicode String has 2 characters outside the " +
                            "ASCII range:\n" +
                            "Pi (\u03a0), and Sigma (\u03a3).";
            Console.WriteLine("Original String:");
            Console.WriteLine(unicodeString);

            // Encode the String.
            BigByte[] encodedBytes = utf8.GetBytes(unicodeString);
            Console.WriteLine();
            Console.WriteLine("Encoded bytes:");
            for (int ctr = 0; ctr < encodedBytes.length; ctr++) {
                Console.Write("{0:X2} ", encodedBytes[ctr]);
                if ((ctr + 1) % 25 == 0)
                    Console.WriteLine();
            }
            Console.WriteLine();

            // Decode bytes back to String.
            String decodedString = utf8.GetString(encodedBytes);
            Console.WriteLine();
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
//    Encoded bytes:
//    54 68 69 73 20 55 6E 69 63 6F 64 65 20 73 74 72 69 6E 67 20 68 61 73 20 32
//    20 63 68 61 72 61 63 74 65 72 73 20 6F 75 74 73 69 64 65 20 74 68 65 20 41
//    53 43 49 49 20 72 61 6E 67 65 3A 20 0D 0A 50 69 20 28 CE A0 29 2C 20 61 6E
//    64 20 53 69 67 6D 61 20 28 CE A3 29 2E
//
//    Decoded bytes:
//    This Unicode String has 2 characters outside the ASCII range:
//    Pi (π), and Sigma (Σ).
