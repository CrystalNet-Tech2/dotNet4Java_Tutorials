package Text.UTF32Encoding;

import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.types.BigByte;
import system.BitConverter;
import system.Console;
import system.text.DecoderFallbackException;
import system.text.Encoding;
import system.text.UTF32Encoding;

public class Example1 {
    public static void main(String[] arg) {
        try {
            // Create a UTF32Encoding_1 object with error detection enabled.
            UTF32Encoding encExc = new UTF32Encoding(!BitConverter.getIsLittleEndian(), true, true);
            // Create a UTF32Encoding_1 object with error detection disabled.
            UTF32Encoding encRepl = new UTF32Encoding(!BitConverter.getIsLittleEndian(), true, false);

            // Create a byte arrays from a String, and add an invalid surrogate pair, as follows.
            //    Latin Small Letter Z (U+007A)
            //    Latin Small Letter A (U+0061)
            //    Combining Breve (U+0306)
            //    Latin Small Letter AE With Acute (U+01FD)
            //    Greek Small Letter Beta (U+03B2)
            //    a high-surrogate value (U+D8FF)
            //    an invalid low surrogate (U+01FF)
            String s = "za\u0306\u01FD\u03B2";

            // Encode the String using little-endian byte order.
            int index = encExc.GetByteCount(s);
            BigByte[] bytes = new BigByte[index + 4];
            encExc.GetBytes(s, 0, s.length(), bytes, 0);
            bytes[index] = BigByte.valueOf(0xFF);
            bytes[index + 1] = BigByte.valueOf(0xD8);
            bytes[index + 2] = BigByte.valueOf(0xFF);
            bytes[index + 3] = BigByte.valueOf(0x01);

            // Decode the byte array with error detection.
            Console.WriteLine("Decoding with error detection:");
            PrintDecodedString(bytes, encExc);

            // Decode the byte array without error detection.
            Console.WriteLine("Decoding without error detection:");
            PrintDecodedString(bytes, encRepl);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    // Decode the bytes and display the String.
    public static void PrintDecodedString(BigByte[] bytes, Encoding enc) throws EClrError {
        try {
            Console.WriteLine("   Decoded String: {0}", enc.GetString(bytes, 0, bytes.length));
        } catch (DecoderFallbackException e) {
            Console.WriteLine(e.ToString());
        }
        Console.WriteLine();
    }
}

// The example displays the following output:
//    Decoding with error detection:
//    System.Text.DecoderFallbackException: Unable to translate bytes [FF][D8][FF][01] at index
//    20 from specified code page to Unicode.
//       at System.Text.DecoderExceptionFallbackBuffer.Throw(BigByte[] bytesUnknown, Int32 index)
//       at System.Text.DecoderExceptionFallbackBuffer.Fallback(BigByte[] bytesUnknown, Int32 index
//    )
//       at System.Text.DecoderFallbackBuffer.InternalFallback(BigByte[] bytes, BigByte* pBytes)
//       at System.Text.UTF32Encoding.UTF32Encoding_1.GetCharCount(BigByte* bytes, Int32 count, DecoderNLS baseDeco
//    der)
//       at System.Text.UTF32Encoding.UTF32Encoding_1.GetString(BigByte[] bytes, Int32 index, Int32 count)
//       at Example.PrintDecodedString(BigByte[] bytes, Encoding enc)
//
//    Decoding without error detection:
//       Decoded String: zăǽβ�
