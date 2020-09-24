package BasicExamples.ArrayList;

import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;

/**
 * Java equivalent of the C# ArrayList Class
 */
class ArrayList extends TClrObject {
    public ArrayList() throws EClrError {
        super("System.Collections.ArrayList", TClrArray.emptyObjectArray());
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public int getCapacity() throws EClrError {
        return getPropertyValueAsInt("Capacity");
    }

    public Object get(int index) throws EClrError {
        return getPropertyIndexValue("Item", index);
    }

    public void set(int index, Object value) throws EClrError {
        setPropertyIndexValue("Item", TClrArray.of("System.String"), TClrArray.of(index), value);
    }

    public int Add(Object value) throws EClrError {
        return invokeIntMethod("Add", TClrArray.of("System.Object"), TClrArray.of(value));
    }
}

public class ArrayListExample {
    public static void main(String[] arg) {
        System.out.println("           Hello! Welcome to dotNet4Java          ");
        System.out.println("==================================================");
        System.out.println("      This program prints out ArrayList values.   ");
        System.out.println();
        try {
            ArrayList AArrayList = new ArrayList();
            AArrayList.Add("Hello");
            AArrayList.Add("World");
            AArrayList.Add("!");
            System.out.println("Array List");
            System.out.println("    Count:    " + AArrayList.getCount());
            System.out.println("    Capacity: " + AArrayList.getCapacity());
            System.out.print("    Values:");
            System.out.println();
            for (int i = 0; i < AArrayList.getCount(); i++) {
                System.out.println("\t   " + AArrayList.get(i));
            }
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}