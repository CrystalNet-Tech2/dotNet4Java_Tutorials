package Collections.BitArray;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.collections.BitArray;
import system.collections.IEnumerable;
import system.collections.IEnumerator;

//The following code example shows how to create and initialize a Collections.BitArray and how to print out its values.

public class SamplesBitArray {

    public static void main(String[] arg) {
        try {
            // Creates and initializes several BitArrays.
            BitArray myBA1 = new BitArray(5);

            BitArray myBA2 = new BitArray(5, false);

            BigByte[] myBytes = {BigByte.valueOf(1), BigByte.valueOf(2), BigByte.valueOf(3), BigByte.valueOf(4), BigByte.valueOf(5)};
            BitArray myBA3 = new BitArray(myBytes);

            boolean[] myBools = {true, false, true, true, false};
            BitArray myBA4 = new BitArray(myBools);

            int[] myInts = {6, 7, 8, 9, 10};
            BitArray myBA5 = new BitArray(myInts);

            // Displays the properties and values of the BitArrays.
            Console.WriteLine("myBA1");
            Console.WriteLine("   get_Count():    {0}", myBA1.get_Count());
            Console.WriteLine("   get_Length():   {0}", myBA1.get_Length());
            Console.WriteLine("   Values:");
            PrintValues(myBA1, 8);

            Console.WriteLine("myBA2");
            Console.WriteLine("   get_Count():    {0}", myBA2.get_Count());
            Console.WriteLine("   get_Length():   {0}", myBA2.get_Length());
            Console.WriteLine("   Values:");
            PrintValues(myBA2, 8);

            Console.WriteLine("myBA3");
            Console.WriteLine("   get_Count():    {0}", myBA3.get_Count());
            Console.WriteLine("   get_Length():   {0}", myBA3.get_Length());
            Console.WriteLine("   Values:");
            PrintValues(myBA3, 8);

            Console.WriteLine("myBA4");
            Console.WriteLine("   get_Count():    {0}", myBA4.get_Count());
            Console.WriteLine("   get_Length():   {0}", myBA4.get_Length());
            Console.WriteLine("   Values:");
            PrintValues(myBA4, 8);

            Console.WriteLine("myBA5");
            Console.WriteLine("   get_Count():    {0}", myBA5.get_Count());
            Console.WriteLine("   get_Length():   {0}", myBA5.get_Length());
            Console.WriteLine("   Values:");
            PrintValues(myBA5, 8);

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void PrintValues(BitArray bitArray, int myWidth) throws Exception {
        int i = myWidth;
        IEnumerable myList = bitArray.AsType(IEnumerable.class);
        IEnumerator enumerator = myList.GetEnumerator();
        while (enumerator.MoveNext()) {
            if (i <= 0) {
                i = myWidth;
                Console.WriteLine();
            }
            i--;
            Console.Write("{0,8}", enumerator.get_Current());
        }
        Console.WriteLine();
    }
}
