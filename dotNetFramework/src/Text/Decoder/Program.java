package Text.Decoder;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.Convert;
import system.text.Decoder;
import system.text.Encoding;

public class Program {
    public static void main(String[] arg) {
        // These bytes in UTF-8 correspond to 3 different Unicode
        // characters: space (U+0020), # (U+0023), and the biohazard
        // symbol (U+2623).  Note the biohazard symbol requires 3 bytes
        // in UTF-8 (hexadecimal e2, 98, a3).  Decoders store state across
        // multiple calls to GetChars, handling the case when one char
        // is in multiple byte arrays.
        BigByte[] bytes1 = {BigByte.valueOf(0x20), BigByte.valueOf(0x23), BigByte.valueOf(0xe2)};
        BigByte[] bytes2 = {BigByte.valueOf(0x98), BigByte.valueOf(0xa3)};
        char[] chars = new char[3];

        try {
            Decoder d = Encoding.get_UTF8().GetDecoder();
            int charLen = d.GetChars(bytes1, 0, bytes1.length, chars, 0);
            // The value of charLen should be 2 now.
            charLen += d.GetChars(bytes2, 0, bytes2.length, chars, charLen);
            for (char c : chars)
                Console.Write("U+{0:X4}  ", Convert.ToUInt16(c));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}