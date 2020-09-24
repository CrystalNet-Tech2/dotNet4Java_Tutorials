package Data.SystemData.Events;

import Data.SystemData.DataRow;
import Data.SystemData.Enums.DataRowAction;
import Data.SystemData.dotNetAssembly;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.EnumUtils;
import com.dotNet4Java.api.core.DotNetNativeTypes;

import java.util.Set;

public class DataRowChangeEventArgs extends TClrObject {

    private DataRow Row;

    public DataRowChangeEventArgs(DotNetNativeTypes.ClrObject clrObject) throws EClrError {
        super(clrObject);
    }

    public DataRowChangeEventArgs(DataRow row, DataRowAction action) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataRowChangeEventArgs, row, action);
    }

    public Set<DataRowAction> getAction() throws EClrError {
        return EnumUtils.setFromInteger(getPropertyValueAsInt("Action"), DataRowAction.class);
    }

    public DataRow getRow() throws Exception {
        if (Row == null)
            Row = TClrObject.createInstance(getPropertyValueAsClrObject("Row"), DataRow.class);
        return Row;
    }
}
