package Collections.Generics;

import system.collections.SortedList;
import system.collections.generic.GenericList;

public class List {

    static void printKeysAndValues(SortedList myList) throws Exception {
        System.out.println("\t-KEY-\t-VALUE-");
        for (int i = 0; i < myList.get_Count(); i++) {
            System.out.println(String.format("\t%s:\t%s", myList.GetKey(i), myList.GetByIndex(i)));
        }
        System.out.println();
    }

    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java         ");
        System.out.println("==================================================");
        System.out.println("The following code example shows how to use .Net generic List.");
        System.out.println();

        try {
            GenericList<String> Dinosaurs = new GenericList<>(new String[]{"System.String"});

            System.out.println(String.format("Capacity: %s", Dinosaurs.get_Capacity()));
            Dinosaurs.Add("Tyrannosaurus");
            Dinosaurs.Add("Amargasaurus");
            Dinosaurs.Add("Mamenchisaurus");
            Dinosaurs.Add("Deinonychus");
            Dinosaurs.Add("Compsognathus");
            System.out.println();

            for (int i = 0; i < Dinosaurs.get_Count(); i++)
                System.out.println(Dinosaurs.get_Item(i));

            System.out.println("Capacity: " + Dinosaurs.get_Capacity());
            System.out.println("Count: " + Dinosaurs.get_Count());

            System.out.println("Contains(\"Deinonychus\"): " + Dinosaurs.Contains("Deinonychus"));

            System.out.println("Insert(2, \"Compsognathus\")");
            Dinosaurs.Insert(2, "Compsognathus");

            System.out.println();
            for (int i = 0; i < Dinosaurs.get_Count(); i++)
                System.out.println(Dinosaurs.get_Item(i));

            // Shows accessing the list using the Item property.
            System.out.println("dinosaurs[3]: " + Dinosaurs.get_Item(3));

            System.out.println("Remove(\"Compsognathus\")");
            Dinosaurs.Remove("Compsognathus");

            System.out.println();
            for (int i = 0; i < Dinosaurs.get_Count(); i++)
                System.out.println(Dinosaurs.get_Item(i));

            Dinosaurs.TrimExcess();
            System.out.println("TrimExcess()");
            System.out.println("Capacity: " + Dinosaurs.get_Capacity());
            System.out.println("Count: " + Dinosaurs.get_Count());

            Dinosaurs.Clear();
            System.out.println("Clear()");
            System.out.println("Capacity: " + Dinosaurs.get_Capacity());
            System.out.println("Count: " + Dinosaurs.get_Count());

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
