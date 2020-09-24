package Collections.GenericList;

import Collections.OtherExamples.SortedList;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrGenericObject;
import com.dotNet4Java.api.EClrError;

/**
 * Java equivalent of the .Net generic List class List<>
 */
class List<T> extends TClrGenericObject {
    public List(String genericTypeName) throws EClrError {
        super("System.Collections.Generic.List`1", new String[]{genericTypeName}, new Object[]{});
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public int getCapacity() throws EClrError {
        return getPropertyValueAsInt("Capacity");
    }

    public T get(int index) throws EClrError {
        return (T) getPropertyIndexValue("Item", index);
    }

    public void set(int index, T value) throws EClrError {
        setPropertyIndexValue("Item", index, value);
    }

    public void Insert(int index, T value) throws Exception {
        invokeVoidMethod("Insert", TClrArray.of("System.Int32", getTypeParameters()[0].getFullName()), TClrArray.of(index, value));
    }

    public boolean Contains(T value) throws Exception {
        return invokeBooleanMethod("Contains", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(value));
    }

    public int Add(T value) throws Exception {
        return invokeIntMethod("Add", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(value));
    }

    public void Remove(T value) throws Exception {
        invokeVoidMethod("Remove", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(value));
    }

    public void TrimExcess() throws Exception {
        invokeVoidMethod("TrimExcess");
    }

    public void Clear() throws Exception {
        invokeVoidMethod("Clear");
    }
}

public class GenericListExample {

    static void printKeysAndValues(SortedList myList) throws EClrError {
        System.out.println("\t-KEY-\t-VALUE-");
        for (int i = 0; i < myList.getCount(); i++) {
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

            List<String> Dinosaurs = new List<String>("System.String");

            System.out.println(String.format("Capacity: %s", Dinosaurs.getCapacity()));
            Dinosaurs.Add("Tyrannosaurus");
            Dinosaurs.Add("Amargasaurus");
            Dinosaurs.Add("Mamenchisaurus");
            Dinosaurs.Add("Deinonychus");
            Dinosaurs.Add("Compsognathus");
            System.out.println();

            for (int i = 0; i < Dinosaurs.getCount(); i++)
                System.out.println(Dinosaurs.get(i));

            System.out.println("Capacity: " + Dinosaurs.getCapacity());
            System.out.println("Count: " + Dinosaurs.getCount());

            System.out.println("Contains(\"Deinonychus\"): " + Dinosaurs.Contains("Deinonychus"));

            System.out.println("Insert(2, \"Compsognathus\")");
            Dinosaurs.Insert(2, "Compsognathus");

            System.out.println();
            for (int i = 0; i < Dinosaurs.getCount(); i++)
                System.out.println(Dinosaurs.get(i));

            // Shows accessing the list using the Item property.
            System.out.println("dinosaurs[3]: " + Dinosaurs.get(3));

            System.out.println("Remove(\"Compsognathus\")");
            Dinosaurs.Remove("Compsognathus");

            System.out.println();
            for (int i = 0; i < Dinosaurs.getCount(); i++)
                System.out.println(Dinosaurs.get(i));

            Dinosaurs.TrimExcess();
            System.out.println("TrimExcess()");
            System.out.println("Capacity: " + Dinosaurs.getCapacity());
            System.out.println("Count: " + Dinosaurs.getCount());

            Dinosaurs.Clear();
            System.out.println("Clear()");
            System.out.println("Capacity: " + Dinosaurs.getCapacity());
            System.out.println("Count: " + Dinosaurs.getCount());

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
