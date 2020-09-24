package Data.SystemData;

import com.dotNet4Java.IClrType;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

import java.util.HashMap;
import java.util.Map;

public class DataColumnCollection extends TClrObject {
    private Map<Integer, DataColumn> DataColumns = new HashMap<>();

    public DataColumnCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public void Add(DataColumn column) throws EClrError {
        invokeMethod("Add", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn), new Object[]{column});
    }

    public DataColumn Add(String columnName, IClrType type, String expression) throws Exception {
        return createInstance(invokeClrObjectMethod("Add",
                TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_Sys_Type_, dotNetAssembly.INSTANCE.sC_Sys_String_),
                new Object[]{columnName, type, expression}), DataColumn.class);
    }

    public DataColumn Add(String columnName, IClrType type) throws Exception {
        return createInstance(invokeClrObjectMethod("Add",
                TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_Sys_Type_),
                new Object[]{columnName, type}), DataColumn.class);
    }

    public DataColumn Add(String columnName) throws Exception {
        return createInstance(invokeClrObjectMethod("Add", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(columnName)), DataColumn.class);
    }

    public DataColumn Add() throws Exception {
        return createInstance(invokeClrObjectMethod("Add"), DataColumn.class);
    }

    public void AddRange(DataColumnArray columns) throws EClrError {
        invokeMethod("AddRange", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumnArray), new Object[]{columns});
    }

    public void Clear() throws EClrError {
        invokeMethod("Clear");
    }

    public boolean Contains(String name) throws EClrError {
        return invokeBooleanMethod("Contains", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(name));
    }

    public void CopyTo(DataColumnArray array, int index) throws EClrError {
        invokeMethod("CopyTo", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumnArray, dotNetAssembly.INSTANCE.sC_Sys_Int32), new Object[]{array, index});
    }

    public int IndexOf(DataColumn column) throws EClrError {
        return invokeIntMethod("IndexOf", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn), new Object[]{column});
    }

    public int IndexOf(String columnName) throws EClrError {
        return invokeIntMethod("IndexOf", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), new Object[]{columnName});
    }

    public void Remove(DataColumn column) throws EClrError {
        invokeMethod("Remove", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn), new Object[]{column});
    }

    public void Remove(String name) throws EClrError {
        invokeMethod("Remove", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), new Object[]{name});
    }

    public void RemoveAt(int index) throws EClrError {
        invokeMethod("RemoveAt", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Int32), new Object[]{index});
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public boolean getIsReadOnly() throws EClrError {
        return getPropertyValueAsBoolean("IsReadOnly");
    }

    public DataColumn getItem(int index) throws Exception {
        if (DataColumns.containsKey(index))
            return DataColumns.get(index);

        DataColumn dataColumn;
        dataColumn = createInstance(getPropertyIndexValueAsClrObject("Item", index), DataColumn.class);
        DataColumns.put(index, dataColumn);
        return dataColumn;
    }
}
