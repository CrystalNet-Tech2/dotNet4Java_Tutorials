package Data.SystemData.Events;

import Data.SystemData.DataColumn;
import Data.SystemData.DataRow;
import Data.SystemData.dotNetAssembly;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

public class DataColumnChangeEventArgs extends TClrObject {
    DataColumn column;
    DataRow row;

    public DataColumnChangeEventArgs(DotNetNativeTypes.ClrObject clrObject) throws EClrError {
        super(clrObject);
    }

    public DataColumnChangeEventArgs(DataRow row, DataColumn column, Object value) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataColumnChangeEventArgs, row, column, value);
    }

    public DataColumn getColumn() throws Exception {
        if (column == null)
            column = TClrObject.createInstance(getPropertyValueAsClrObject("Column"), DataColumn.class);
        return column;
    }

    public DataRow getRow() throws Exception {
        if (row == null)
            row = TClrObject.createInstance(getPropertyValueAsClrObject("Row"), DataRow.class);
        return row;
    }

    public Object getProposedValue() throws EClrError {
        return getPropertyValue("ProposedValue");
    }

    public void setProposedValue(Object value) throws EClrError {
        setPropertyValue("ProposedValue", value);
    }
}