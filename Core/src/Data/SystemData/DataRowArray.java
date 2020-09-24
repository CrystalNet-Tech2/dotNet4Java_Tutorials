package Data.SystemData;

import com.dotNet4Java.TClrObjectArray;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

public class DataRowArray extends TClrObjectArray<DataRow> {

    public DataRowArray(DotNetNativeTypes.ClrObject object) throws EClrError {
        super(DataRow.class, object);
    }

    public DataRowArray(int length) throws EClrError {
        super(DataRow.class, dotNetAssembly.INSTANCE.sC_SysDat_DataRow, length);
    }

    public DataRowArray(DotNetNativeTypes.ClrArray array) throws EClrError {
        super(DataRow.class, array);
    }

    public DataRowArray(DataRow[] array) throws EClrError {
        super(DataRow.class, dotNetAssembly.INSTANCE.sC_SysDat_DataRow, array);
    }
}
