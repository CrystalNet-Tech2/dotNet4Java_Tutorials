package Text.Encoding;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.text.Encoding;

public class Program {
    public static void main(String[] arg) {
        String unicodeString = "This String contains the unicode character Pi (\u03a0)";
        try {
            // Create two different encodings.
            Encoding ascii = Encoding.get_ASCII();
            Encoding unicode = Encoding.get_Unicode();

            // Convert the String into a BigByte array.
            BigByte[] unicodeBytes = unicode.GetBytes(unicodeString);

            // Perform the conversion from one encoding to the other.
            BigByte[] asciiBytes = Encoding.Convert(unicode, ascii, unicodeBytes);

            // Convert the new BigByte[] into a char[] and then into a String.
            char[] asciiChars = new char[ascii.GetCharCount(asciiBytes, 0, asciiBytes.length)];
            ascii.GetChars(asciiBytes, 0, asciiBytes.length, asciiChars, 0);
            String asciiString = new String(asciiChars);

            // Display the strings created before and after the conversion.
            Console.WriteLine("Original String: {0}", unicodeString);
            System.out.println(unicodeString);
            Console.WriteLine("Ascii converted String: {0}", asciiString);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}

// The example displays the following output:
//    Original String: This String contains the unicode character Pi (Π)
//    Ascii converted String: This String contains the unicode character Pi (?)