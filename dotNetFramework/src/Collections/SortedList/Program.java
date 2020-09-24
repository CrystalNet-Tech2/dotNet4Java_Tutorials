package Collections.SortedList;

import com.dotNet4Java.api.EClrError;
import system.collections.SortedList;

public class Program {

    static void printKeysAndValues(SortedList myList) throws EClrError {
        System.out.println("\t-KEY-\t-VALUE-");
        for (int i = 0; i < myList.get_Count(); i++) {
            System.out.println(String.format("\t%s:\t%s", myList.GetKey(i), myList.GetByIndex(i)));
        }
        System.out.println();
    }

    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java         ");
        System.out.println("==================================================");
        System.out.println("The following code example shows how to add elements to a SortedList object.");
        System.out.println();

        try {

            // Creates and initializes a new SortedList.
            SortedList mySL = new SortedList();
            mySL.Add("one", "The");
            mySL.Add("two", "quick");
            mySL.Add("three", "brown");
            mySL.Add("four", "fox");

            // Displays the SortedList.
            System.out.println("The SortedList contains the following:");
            printKeysAndValues(mySL);

        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}
