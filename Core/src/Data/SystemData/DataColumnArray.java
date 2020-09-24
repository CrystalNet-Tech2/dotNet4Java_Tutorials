package Data.SystemData;

import com.dotNet4Java.TClrObjectArray;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

public class DataColumnArray extends TClrObjectArray<DataColumn> {
    public DataColumnArray(DotNetNativeTypes.ClrObject object) throws EClrError {
        super(DataColumn.class, object);
    }

    public DataColumnArray(int length) throws EClrError {
        super(DataColumn.class, dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, length);
    }

    public DataColumnArray(DotNetNativeTypes.ClrArray array) throws EClrError {
        super(DataColumn.class, array);
    }

    public DataColumnArray(DataColumn[] array) throws EClrError {
        super(DataColumn.class, dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, array);
    }
}