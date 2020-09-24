package Data.SystemData;

import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

import java.util.HashMap;
import java.util.Map;

public class DataRowCollection extends TClrObject {
    private Map<Integer, DataRow> DataRows = new HashMap<>();

    public DataRowCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public void Add(DataRow row) throws EClrError {
        invokeMethod("Add", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRow), TClrArray.of(row));
    }

    public DataRow Add(Object[] values) throws Exception {
        return createInstance(invokeClrObjectMethod("Add", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_ObjectArray), TClrArray.of((Object) values)), DataRow.class);
    }

    public void Clear() throws EClrError {
        invokeMethod("Clear");
    }

    public boolean Contains(Object key) throws EClrError {
        return invokeBooleanMethod("Contains", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Object_), TClrArray.of(key));
    }

    public boolean Contains(Object[] keys) throws EClrError {
        return invokeBooleanMethod("Contains", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_ObjectArray), new Object[]{keys}); //TClrArray.of(keys)
    }

    public void CopyTo(DataRowArray array, int index) throws EClrError {
        invokeMethod("CopyTo", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRowArray, dotNetAssembly.INSTANCE.sC_Sys_Int32), TClrArray.of(array, index));
    }

    public DataRow Find(Object key) throws Exception {
        return createInstance(invokeClrObjectMethod("Find", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Object_), TClrArray.of(key)), DataRow.class);
    }

    public DataRow Find(Object[] keys) throws Exception {
        return createInstance(invokeClrObjectMethod("Find", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_ObjectArray), new Object[]{keys}), DataRow.class); //Don't use TClrArray.of(keys)
    }

    public int IndexOf(DataRow row) throws EClrError {
        return invokeIntMethod("IndexOf", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRow), TClrArray.of(row));
    }

    public void Remove(DataRow row) throws EClrError {
        invokeMethod("Remove", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRow), TClrArray.of(row));
    }

    public void RemoveAt(int index) throws EClrError {
        invokeMethod("RemoveAt", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Int32), TClrArray.of(index));
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public DataRow getItem(int index) throws Exception {
        if (DataRows.containsKey(index)) return DataRows.get(index);
        DataRow dataRow = createInstance(getPropertyIndexValueAsClrObject("Item", index), DataRow.class);
        DataRows.put(index, dataRow);
        return dataRow;
    }
}