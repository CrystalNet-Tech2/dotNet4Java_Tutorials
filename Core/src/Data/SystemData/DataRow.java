package Data.SystemData;

import Data.SystemData.Enums.DataRowState;
import Data.SystemData.Enums.DataRowVersion;
import com.dotNet4Java.IClrGenericArray;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrGenericArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.EnumUtils;
import com.dotNet4Java.api.core.DotNetNativeTypes;

import java.util.Set;

public class DataRow extends TClrObject {
    private DataTable dataTable;
    private IClrGenericArray<Object> itemArray;

    public DataRow(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public void AcceptChanges() throws EClrError {
        invokeMethod("AcceptChanges");
    }

    public void Delete() throws EClrError {
        invokeMethod("Delete");
    }

    public boolean IsNull(int columnIndex) throws EClrError {
        return invokeBooleanMethod("IsNull", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Int32), new Object[]{columnIndex});
    }

    public boolean IsNull(String columnName) throws EClrError {
        return invokeBooleanMethod("IsNull", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), new Object[]{columnName});
    }

    public boolean IsNull(DataColumn column) throws EClrError {
        return invokeBooleanMethod("IsNull", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn), new Object[]{column});
    }

    public void RejectChanges() throws EClrError {
        invokeMethod("RejectChanges");
    }

    public void SetAdded() throws EClrError {
        invokeMethod("SetAdded");
    }

    public void SetModified() throws EClrError {
        invokeMethod("SetModified");
    }

    public boolean getHasErrors() throws EClrError {
        return getPropertyValueAsBoolean("HasErrors");
    }

    public Object getItem(int columnIndex) throws EClrError {
        return getPropertyIndexValue("Item", columnIndex);
    }

    public Object getItem(int columnIndex, DataRowVersion version) throws EClrError {
        return getPropertyIndexValue("Item", TClrArray.of("System.Int32", dotNetAssembly.INSTANCE.sC_SysDat_DataRowVersion), TClrArray.of(columnIndex, version));
    }

    public void setItem(int columnIndex, Object value) throws EClrError {
        setPropertyIndexValue("Item", columnIndex, value);
    }

    public IClrGenericArray<Object> getItemArray() throws Exception {
        if (itemArray == null)
            itemArray = TClrGenericArray.createInstance(getPropertyValue("ItemArray"), Object.class);
        return itemArray;
    }

    public void setItemArray(Object[] value) throws EClrError {
        setPropertyValue("ItemArray", value);
    }

    public DataTable getTable() throws Exception {
        if (dataTable == null)
            dataTable = createInstance(getPropertyValueAsClrObject("Table"), DataTable.class);

        return dataTable;
    }

    public Set<DataRowState> getRowState() throws Exception {
        return EnumUtils.setFromInteger(getPropertyValueAsInt("RowState"), DataRowState.class);
    }
}
