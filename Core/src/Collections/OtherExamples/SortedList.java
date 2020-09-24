package Collections.OtherExamples;

import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;

/**
 * .Net Sorted List equivalent in Java
 */
public class SortedList extends TClrObject {

    static String SortedList_TypeName = "System.Collections.SortedList";
    static String Object_TypeName = "System.Object";
    static String Integer_TypeName = "System.Int32";

    public SortedList() throws EClrError {
        super(SortedList_TypeName, new Object[]{});
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public Object get(Object key) throws EClrError {
        return getPropertyIndexValue("Item", new String[]{Object_TypeName}, new Object[]{key});
    }

    public void set(Object key, Object value) throws EClrError {
        setPropertyIndexValue("Item", TClrArray.of(Object_TypeName), TClrArray.of(key), value);
    }

    public Object GetByIndex(int index) throws EClrError {
        return invokeMethod("GetByIndex", TClrArray.of(Integer_TypeName), TClrArray.of(index));
    }

    public Object GetKey(int index) throws EClrError {
        return invokeMethod("GetKey", TClrArray.of(Integer_TypeName), TClrArray.of(index));
    }

    public int Add(Object key, Object value) throws EClrError {
        return invokeIntMethod("Add", TClrArray.of(Object_TypeName, Object_TypeName), TClrArray.of(key, value));
    }
}
