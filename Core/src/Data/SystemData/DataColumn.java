package Data.SystemData;

import com.dotNet4Java.IClrType;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.TClrType;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

public class DataColumn extends TClrObject {
    private IClrType DataType;
    private DataTable Table;

    public DataColumn(DotNetNativeTypes.ClrObject object) {
        super(object);
    }

    public DataColumn() throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, (Object[]) null);
    }

    public DataColumn(String columnName) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, columnName);
    }

    public DataColumn(String columnName, IClrType dataType) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, columnName, dataType);
    }

    public DataColumn(String columnName, IClrType dataType, String expr) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataColumn, columnName, dataType, expr);
    }

    public void Dispose() throws EClrError {
        invokeMethod("Dispose");
    }

    public void SetOrdinal(int ordinal) throws EClrError {
        invokeMethod("SetOrdinal", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_Int32), new Object[]{ordinal});
    }

    public boolean getAllowDBNull() throws EClrError {
        return getPropertyValueAsBoolean("AllowDBNull");
    }

    public void setAllowDBNull(boolean value) throws EClrError {
        setPropertyValue("AllowDBNull", value);
    }

    public boolean getAutoIncrement() throws EClrError {
        return getPropertyValueAsBoolean("AutoIncrement");
    }

    public void setAutoIncrement(boolean value) throws EClrError {
        setPropertyValue("AutoIncrement", value);
    }

    public long getAutoIncrementSeed() throws EClrError {
        return getPropertyValueAsLong("AutoIncrementSeed");
    }

    public void setAutoIncrementSeed(long value) throws EClrError {
        setPropertyValue("AutoIncrementSeed", value);
    }

    public long getAutoIncrementStep() throws EClrError {
        return getPropertyValueAsLong("AutoIncrementStep");
    }

    public void setAutoIncrementStep(long value) throws EClrError {
        setPropertyValue("AutoIncrementStep", value);
    }

    public String getCaption() throws EClrError {
        return getPropertyValueAsString("Caption");
    }

    public void setCaption(String value) throws EClrError {
        setPropertyValue("Caption", value);
    }

    public String getColumnName() throws EClrError {
        return getPropertyValueAsString("ColumnName");
    }

    public void setColumnName(String value) throws EClrError {
        setPropertyValue("ColumnName", value);
    }

    public IClrType getDataType() throws Exception {
        if (DataType == null)
            DataType = TClrObject.createInstance(getPropertyValueAsClrObject("DataType"), TClrType.class);
        return DataType;
    }

    public void setDataType(IClrType value) throws EClrError {
        DataType = value;
        setPropertyValue("DataType", value);
    }

    public Object getDefaultValue() throws EClrError {
        return getPropertyValue("DefaultValue");
    }

    public void setDefaultValue(Object value) throws EClrError {
        setPropertyValue("DefaultValue", value);
    }

    public int getMaxLength() throws EClrError {
        return getPropertyValueAsInt("MaxLength");
    }

    public void setMaxLength(int value) throws EClrError {
        setPropertyValue("MaxLength", value);
    }

    public String getNamespace() throws EClrError {
        return getPropertyValueAsString("Namespace");
    }

    public void setNamespace(String value) throws EClrError {
        setPropertyValue("Namespace", value);
    }

    public int getOrdinal() throws EClrError {
        return getPropertyValueAsInt("Ordinal");
    }

    public String getPrefix() throws EClrError {
        return getPropertyValueAsString("Prefix");
    }

    public void setPrefix(String value) throws EClrError {
        setPropertyValue("Prefix", value);
    }

    public boolean getReadOnly() throws EClrError {
        return getPropertyValueAsBoolean("ReadOnly");
    }

    public void setReadOnly(boolean value) throws EClrError {
        setPropertyValue("ReadOnly", value);
    }

    public DataTable getTable() throws Exception {
        if (Table == null)
            Table = createInstance(getPropertyValueAsClrObject("Table"), DataTable.class);

        return Table;
    }

    public boolean getUnique() throws EClrError {
        return getPropertyValueAsBoolean("Unique");
    }

    public void setUnique(boolean value) throws EClrError {
        setPropertyValue("Unique", value);
    }
}
