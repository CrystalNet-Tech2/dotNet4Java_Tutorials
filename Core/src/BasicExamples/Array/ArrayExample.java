package BasicExamples.Array;

import com.dotNet4Java.IClrArray;
import com.dotNet4Java.IClrType;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrType;

public class ArrayExample {

    static void printArrayByTypeName() throws Exception {
        IClrArray oArray = TClrArray.createInstance("System.Double", 6);
        oArray.setValue(900, 0);
        oArray.setValue(800, 1);
        oArray.setValue(700, 2);
        oArray.setValue(600, 3);
        oArray.setValue(500, 4);
        oArray.setValue(400, 5);

        System.out.println("Double Array");
        System.out.println("    Count:    " + oArray.getLength());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.getLength(); i++)
            System.out.println("\t\t " + oArray.getValue(i));

        System.out.println();
    }

    static void printArrayByType() throws Exception {
        IClrType oType = TClrType.getType("System.Double", true);
        IClrArray oArray = TClrArray.createInstance(oType, 6);
        oArray.setValue(900, 0);
        oArray.setValue(800, 1);
        oArray.setValue(700, 2);
        oArray.setValue(600, 3);
        oArray.setValue(500, 4);
        oArray.setValue(400, 5);

        System.out.println("Double Array");
        System.out.println("    Count:    " + oArray.getLength());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.getLength(); i++)
            System.out.println("\t\t " + oArray.getValue(i));

        System.out.println();
    }

    static void printArrayByObjectType() throws Exception {
        IClrArray oArray = TClrArray.createInstance("System.Object", 6);
        oArray.setValue(900, 0);
        oArray.setValue(800, 1);
        oArray.setValue(700, 2);
        oArray.setValue(600, 3);
        oArray.setValue(500, 4);
        oArray.setValue(400, 5);

        System.out.println("Object Array");
        System.out.println("    Count:    " + oArray.getLength());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.getLength(); i++)
            System.out.println("\t\t " + oArray.getValue(i));

        System.out.println();
    }

    static void printObjectArray() throws Exception {
        IClrArray oArray = TClrArray.createObjectArray(6);
        oArray.setValue(900, 0);
        oArray.setValue(800, 1);
        oArray.setValue(700, 2);
        oArray.setValue(600, 3);
        oArray.setValue(500, 4);
        oArray.setValue(400, 5);

        System.out.println("Object Array");
        System.out.println("    Count:    " + oArray.getLength());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.getLength(); i++)
            System.out.println("\t\t " + oArray.getValue(i));

        System.out.println();
    }

    static void printDoubleArray() throws Exception {
        IClrArray oArray = TClrArray.createDoubleArray(6);
        oArray.setValue(900, 0);
        oArray.setValue(800, 1);
        oArray.setValue(700, 2);
        oArray.setValue(600, 3);
        oArray.setValue(500, 4);
        oArray.setValue(400, 5);

        System.out.println("Double Array");
        System.out.println("    Count:    " + oArray.getLength());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.getLength(); i++)
            System.out.println("\t\t " + oArray.getValue(i));

        System.out.println();
    }

    public static void main(String[] arg) {
        System.out.println("Hello! Welcome to dotNet4Java");
        System.out.println("=====================================");
        System.out.println("This program prints out Array values.");
        System.out.println();
        try {
            printArrayByTypeName();
            printArrayByType();
            printArrayByObjectType();
            printObjectArray();
            printDoubleArray();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
