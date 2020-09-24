package Collections.ArrayList;

import system.collections.ArrayList;

public class Program {

    public static void main(String[] arg) {
        System.out.println("   Hello! Welcome to dotNet4Java .net Framework   ");
        System.out.println("==================================================");
        System.out.println("      This program prints out Collections.ArrayList values.   ");
        System.out.println();
        try {
            ArrayList AArrayList = new ArrayList();
            AArrayList.Add("Hello");
            AArrayList.Add("World");
            AArrayList.Add("!");
            System.out.println("Array List");
            System.out.println("    Count:    " + AArrayList.get_Count());
            System.out.println("    Capacity: " + AArrayList.get_Capacity());
            System.out.print("    Values:");
            System.out.println();
            for (int i = 0; i < AArrayList.get_Count(); i++) {
                System.out.println("\t   " + AArrayList.get_Item(i));
            }
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}