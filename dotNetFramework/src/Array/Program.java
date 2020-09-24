package Array;

import system.Array;
import system.Type;

public class Program {

    static void printArrayByTypeName() throws Exception {
        Array oArray = Array.CreateInstance(Type.GetType("System.Double"), 6);
        oArray.SetValue(900, 0);
        oArray.SetValue(800, 1);
        oArray.SetValue(700, 2);
        oArray.SetValue(600, 3);
        oArray.SetValue(500, 4);
        oArray.SetValue(400, 5);

        System.out.println("Double Array");
        System.out.println("    Count:    " + oArray.get_Length());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.get_Length(); i++)
            System.out.println("\t\t " + oArray.GetValue(i));

        System.out.println();
    }

    static void printArrayByType() throws Exception {
        Type oType = Type.GetType("System.Double", true);
        Array oArray = Array.CreateInstance(oType, 6);
        oArray.SetValue(900, 0);
        oArray.SetValue(800, 1);
        oArray.SetValue(700, 2);
        oArray.SetValue(600, 3);
        oArray.SetValue(500, 4);
        oArray.SetValue(400, 5);

        System.out.println("Double Array");
        System.out.println("    Count:    " + oArray.get_Length());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.get_Length(); i++)
            System.out.println("\t\t " + oArray.GetValue(i));

        System.out.println();
    }

    static void printArrayByObjectType() throws Exception {
        Array oArray = Array.CreateInstance(Type.GetType("System.Object"), 6);
        oArray.SetValue(900, 0);
        oArray.SetValue(800, 1);
        oArray.SetValue(700, 2);
        oArray.SetValue(600, 3);
        oArray.SetValue(500, 4);
        oArray.SetValue(400, 5);

        System.out.println("Object Array");
        System.out.println("    Count:    " + oArray.get_Length());
        System.out.println("    Rank:     " + oArray.getPropertyValue("Rank"));
        System.out.println("    Values:");

        for (int i = 0; i < oArray.get_Length(); i++)
            System.out.println("\t\t " + oArray.GetValue(i));

        System.out.println();
    }

    public static void main(String[] arg) {
        System.out.println("Hello! Welcome to dotNet4Java .Net Framework");
        System.out.println("===========================================");
        System.out.println("This program prints out Array values.");
        System.out.println();
        try {
            printArrayByTypeName();
            printArrayByType();
            printArrayByObjectType();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
