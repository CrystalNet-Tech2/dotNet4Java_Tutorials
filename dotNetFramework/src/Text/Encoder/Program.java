package Text.Encoder;

import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.types.BigByte;
import system.Console;
import system.text.Encoder;
import system.text.Encoding;

public class Program {
    public static void main(String[] arg) {
        // The characters to encode.
        char[] chars = new char[]{
                '\u0023', // #
                '\u0025', // %
                '\u03a0', // Pi
                '\u03a3'  // Sigma
        };

        try {
            // Encode characters using an Encoding object.
            Encoding encoding = Encoding.get_UTF7();
            Console.WriteLine("Using Encoding\n--------------");

            // Encode complete array for comparison.
            BigByte[] allCharactersFromEncoding = encoding.GetBytes(chars);
            Console.WriteLine("All characters encoded:");
            ShowArray(allCharactersFromEncoding);

            // Encode characters, one-by-one.
            // The Encoding object will NOT maintain state between calls.
            BigByte[] firstchar = encoding.GetBytes(chars, 0, 1);
            Console.WriteLine("First character:");
            ShowArray(firstchar);

            BigByte[] secondchar = encoding.GetBytes(chars, 1, 1);
            Console.WriteLine("Second character:");
            ShowArray(secondchar);

            BigByte[] thirdchar = encoding.GetBytes(chars, 2, 1);
            Console.WriteLine("Third character:");
            ShowArray(thirdchar);

            BigByte[] fourthchar = encoding.GetBytes(chars, 3, 1);
            Console.WriteLine("Fourth character:");
            ShowArray(fourthchar);

            // Now, encode characters using an Encoder object.
            Encoder encoder = encoding.GetEncoder();
            Console.WriteLine("Using Encoder\n-------------");

            // Encode complete array for comparison.
            BigByte[] allCharactersFromEncoder = new BigByte[encoder.GetByteCount(chars, 0, chars.length, true)];
            encoder.GetBytes(chars, 0, chars.length, allCharactersFromEncoder, 0, true);
            Console.WriteLine("All characters encoded:");
            ShowArray(allCharactersFromEncoder);

            // Do not flush state; i.e. maintain state between calls.
            boolean bFlushState = false;

            // Encode characters one-by-one.
            // By maintaining state, the Encoder will not store extra bytes in the output.
            BigByte[] firstcharNoFlush = new BigByte[encoder.GetByteCount(chars, 0, 1, bFlushState)];
            encoder.GetBytes(chars, 0, 1, firstcharNoFlush, 0, bFlushState);
            Console.WriteLine("First character:");
            ShowArray(firstcharNoFlush);

            BigByte[] secondcharNoFlush = new BigByte[encoder.GetByteCount(chars, 1, 1, bFlushState)];
            encoder.GetBytes(chars, 1, 1, secondcharNoFlush, 0, bFlushState);
            Console.WriteLine("Second character:");
            ShowArray(secondcharNoFlush);

            BigByte[] thirdcharNoFlush = new BigByte[encoder.GetByteCount(chars, 2, 1, bFlushState)];
            encoder.GetBytes(chars, 2, 1, thirdcharNoFlush, 0, bFlushState);
            Console.WriteLine("Third character:");
            ShowArray(thirdcharNoFlush);

            // Must flush state on last call to GetBytes().
            bFlushState = true;

            BigByte[] fourthcharNoFlush = new BigByte[encoder.GetByteCount(chars, 3, 1, bFlushState)];
            encoder.GetBytes(chars, 3, 1, fourthcharNoFlush, 0, bFlushState);
            Console.WriteLine("Fourth character:");
            ShowArray(fourthcharNoFlush);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowArray(BigByte[] theArray) throws EClrError {
        for (Object o : theArray) {
            Console.Write("[{0}]", o);
        }
        Console.WriteLine("\n");
    }
}

/* This code example produces the following output.

Using Encoding
--------------
All characters encoded:
[43][65][67][77][65][74][81][79][103][65][54][77][45]

First character:
[43][65][67][77][45]

Second character:
[43][65][67][85][45]

Third character:
[43][65][54][65][45]

Fourth character:
[43][65][54][77][45]

Using Encoder
-------------
All characters encoded:
[43][65][67][77][65][74][81][79][103][65][54][77][45]

First character:
[43][65][67]

Second character:
[77][65][74]

Third character:
[81][79][103]

Fourth character:
[65][54][77][45]


*/
