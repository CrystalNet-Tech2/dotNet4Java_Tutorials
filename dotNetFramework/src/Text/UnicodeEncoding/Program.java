package Text.UnicodeEncoding;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.text.UnicodeEncoding;

public class Program {
    public static void main(String[] arg) {
        try {
            // The encoding.
            UnicodeEncoding unicode = new UnicodeEncoding();

            // Create a String that contains Unicode characters.
            String unicodeString =
                    "This Unicode String contains two characters " +
                            "with codes outside the traditional ASCII code range, " +
                            "Pi (\u03a0) and Sigma (\u03a3).";
            Console.WriteLine("Original String:");
            Console.WriteLine(unicodeString);

            // Encode the String.
            BigByte[] encodedBytes = unicode.GetBytes(unicodeString);
            Console.WriteLine();
            Console.WriteLine("Encoded bytes:");
            for (BigByte b : encodedBytes) {
                Console.Write("[{0}]", b);
            }
            Console.WriteLine();

            // Decode bytes back to String.
            // Notice Pi and Sigma characters are still present.
            String decodedString = unicode.GetString(encodedBytes);
            Console.WriteLine();
            Console.WriteLine("Decoded bytes:");
            Console.WriteLine(decodedString);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
