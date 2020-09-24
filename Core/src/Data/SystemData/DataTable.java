package Data.SystemData;

import Data.SystemData.Enums.*;
import Data.SystemData.Events.DataColumnChangeEventArgs;
import Data.SystemData.Events.DataColumnChangeEventHandler;
import Data.SystemData.Events.DataRowChangeEventArgs;
import Data.SystemData.Events.DataRowChangeEventHandler;
import com.dotNet4Java.*;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.EnumUtils;
import com.dotNet4Java.api.core.DotNetNativeTypes;
import com.dotNet4Java.api.util.ClrEventArgsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataTable extends TClrObject {
    // Event Handlers
    static final DotNetNativeTypes.IClrEventHandler ColumnChangedHandler = new DotNetNativeTypes.IClrEventHandler() {
        @Override
        public void invoke(DotNetNativeTypes.ClrObject sender, DotNetNativeTypes.ClrEventArgs eventArgs) throws Exception {
            DataTable evtObject = (DataTable) TClrNotifyEvent.INSTANCE.getSource(sender);
            //***Event Arguments Options***
            //1.=> Best option
            DataColumnChangeEventArgs evtArg = TClrObject.createInstance(ClrEventArgsUtils.getEventParamValue(eventArgs, 1), DataColumnChangeEventArgs.class);
            // if (evtObject != null && evtObject.ColumnChanged != null) {
            //    evtObject.ColumnChanged.invoke(evtObject, evtArg);
            //}
            if (evtObject != null) {
                for (DataColumnChangeEventHandler handler : evtObject.ColumnChanged) {
                    handler.invoke(evtObject, evtArg);
                }
            }

            //2.
            DataColumnChangeEventArgs evtArg2 = TClrObject.createInstance(ClrEventArgsUtils.getEventParamValueAsObject(eventArgs, 1), DataColumnChangeEventArgs.class);

            //3.
            DataColumnChangeEventArgs evtArg3 = new DataColumnChangeEventArgs(ClrEventArgsUtils.toClrObject(eventArgs));
        }
    };
    // Event Handlers
    static final DotNetNativeTypes.IClrEventHandler RowChangedHandler = new DotNetNativeTypes.IClrEventHandler() {
        @Override
        public void invoke(DotNetNativeTypes.ClrObject sender, DotNetNativeTypes.ClrEventArgs eventArgs) throws Exception {
            DataTable evtObject = (DataTable) TClrNotifyEvent.INSTANCE.getSource(sender);
            DataRowChangeEventArgs evtArg = TClrObject.createInstance(ClrEventArgsUtils.getEventParamValue(eventArgs, 1), DataRowChangeEventArgs.class);
//            if (evtObject != null && evtObject.RowChanged != null) {
//                evtObject.RowChanged.invoke(evtObject, evtArg);
//            }
            if (evtObject != null) {
                for (DataRowChangeEventHandler handler : evtObject.RowChanged) {
                    handler.invoke(evtObject, evtArg);
                }
            }
        }
    };
    private final static IClrObject staticDataTable = TClrActivator.createStaticInstance(dotNetAssembly.INSTANCE.sC_SysDat_DataTable);
    //Fields & Properties
    private DataColumnCollection Columns;
    private DataColumnArray PrimaryKey;
    private DataRowCollection Rows;
    // Events
    private List<DataColumnChangeEventHandler> ColumnChanged = new ArrayList<>();
    private List<DataRowChangeEventHandler> RowChanged = new ArrayList<>();

    // Constructors
    public DataTable(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public DataTable() throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataTable, (DotNetNativeTypes.ClrObject) null);
    }

    public DataTable(String tableName) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataTable, tableName);
    }

    public DataTable(String tableName, String tableNamespace) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataTable, tableName, tableNamespace);
    }

    public DataTable(String tableName, TRefObject<String> tableNamespace) throws EClrError {
        super(dotNetAssembly.INSTANCE.sC_SysDat_DataTable, tableName, tableNamespace.argValue);

        tableNamespace.argValue = "MyData";
    }

    // Methods
    public void AcceptChanges() throws EClrError {
        invokeMethod("AcceptChanges");
    }

    public void Clear() throws EClrError {
        invokeMethod("Clear");
    }

    public DataTable Clone() throws Exception {
        return TClrObject.createInstance(invokeClrObjectMethod("Clone"), DataTable.class);
    }

    public Object Compute(String expression, String filter) throws EClrError {
        return invokeMethod("Compute", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(expression, filter));
    }

    public DataTable Copy() throws Exception {
        return TClrObject.createInstance(invokeClrObjectMethod("Copy"), DataTable.class);
    }

    public DataTable GetChanges() throws Exception {
        return TClrObject.createInstance(invokeClrObjectMethod("GetChanges"), DataTable.class);
    }

    public DataTable GetChanges(Set<DataRowState> rowStates) throws Exception {
        return TClrObject.createInstance(invokeClrObjectMethod("GetChanges", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRowState), TClrArray.of(rowStates)), DataTable.class);
    }

    public void ImportRow(DataRow row) throws EClrError {
        invokeMethod("ImportRow", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataRow), TClrArray.of(row));
    }

    public DataRow LoadDataRow(Object[] values, boolean fAcceptChanges) throws Exception {
        return createInstance(invokeClrObjectMethod("LoadDataRow", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_ObjectArray, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(values, fAcceptChanges)), DataRow.class);
    }

    public DataRow LoadDataRow(Object[] values, LoadOption loadOption) throws Exception {
        return createInstance(invokeClrObjectMethod("LoadDataRow", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_ObjectArray, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(values, loadOption)), DataRow.class);
    }

    public void Merge(DataTable table, boolean preserveChanges) throws Exception {
        invokeMethod("Merge", TClrArray.of(dotNetAssembly.INSTANCE.sC_SysDat_DataTable, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(table, preserveChanges));
    }

    public DataRow NewRow() throws Exception {
        return createInstance(invokeClrObjectMethod("NewRow"), DataRow.class);
    }

    public XmlReadMode ReadXml(String fileName) throws EClrError {
        return EnumUtils.fromInteger(invokeIntMethod("ReadXml", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(fileName)), XmlReadMode.class);
    }

    public void ReadXmlSchema(String fileName) throws EClrError {
        invokeMethod("ReadXmlSchema", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(fileName));
    }

    public void RejectChanges() throws EClrError {
        invokeMethod("RejectChanges");
    }

    public DataRowArray Select() throws Exception {
        return createInstance(invokeClrObjectMethod("Select"), DataRowArray.class);
    }

    public DataRowArray Select(String filterExpression) throws Exception {
        return createInstance(invokeClrObjectMethod("Select", TClrArray.of("System.String"), TClrArray.of(filterExpression)), DataRowArray.class);
    }

    public void WriteXml(String fileName, XmlWriteMode mode) throws EClrError {
        invokeMethod("WriteXml", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_SysDat_XmlWriteMode), TClrArray.of(fileName, mode));
    }

    public void WriteXml(String fileName, XmlWriteMode mode, boolean writeHierarchy) throws EClrError {
        invokeMethod("WriteXml", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_SysDat_XmlWriteMode, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(fileName, mode, writeHierarchy));
    }

    public void WriteXml(String fileName) throws EClrError {
        invokeMethod("WriteXml", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(fileName));
    }

    public void WriteXml(String fileName, boolean writeHierarchy) throws EClrError {
        invokeMethod("WriteXml", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(fileName, writeHierarchy));
    }

    public void WriteXmlSchema(String fileName) throws EClrError {
        invokeMethod("WriteXmlSchema", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_), TClrArray.of(fileName));
    }

//    // Event Handlers getter
//    public DataColumnChangeEventHandler getColumnChanged() {
//        return ColumnChanged;
//    }
//
//    // Event Handlers setter
//    public void setColumnChanged(DataColumnChangeEventHandler value) throws EClrError {
//        if (value != null)
//            registerEventCallBack("ColumnChanged", ColumnChangedHandler);
//        else
//            unRegisterEventCallBack("ColumnChanged", ColumnChangedHandler);
//        ColumnChanged = value;
//    }

    public void WriteXmlSchema(String fileName, boolean writeHierarchy) throws EClrError {
        invokeMethod("WriteXmlSchema", TClrArray.of(dotNetAssembly.INSTANCE.sC_Sys_String_, dotNetAssembly.INSTANCE.sC_Sys_Boolean), TClrArray.of(fileName, writeHierarchy));
    }

    // Add Event Handlers
    public void addColumnChanged(DataColumnChangeEventHandler value) throws EClrError {
        registerEventCallBack("ColumnChanged", ColumnChangedHandler);
        ColumnChanged.add(value);
    }

    // Remove Event Handlers
    public void removeColumnChanged(DataColumnChangeEventHandler value) throws EClrError {
        unRegisterEventCallBack("ColumnChanged", ColumnChangedHandler);
        ColumnChanged.remove(value);
    }

//    // Event Handlers getter
//    public DataRowChangeEventHandler getRowChanged() {
//        return RowChanged;
//    }
//
//    // Event Handlers setter
//    public void setRowChanged(DataRowChangeEventHandler value) throws EClrError {
//        if (value != null)
//            registerEventCallBack("RowChanged", RowChangedHandler);
//        else
//            unRegisterEventCallBack("RowChanged", RowChangedHandler);
//        RowChanged = value;
//    }

    // Add Event Handlers
    public void addRowChanged(DataRowChangeEventHandler value) throws EClrError {
        registerEventCallBack("RowChanged", RowChangedHandler);
        RowChanged.add(value);
    }

    // Remove Event Handlers
    public void removeRowChanged(DataRowChangeEventHandler value) throws EClrError {
        unRegisterEventCallBack("RowChanged", RowChangedHandler);
        RowChanged.remove(value);
    }

    // Fields & Properties
    public boolean getCaseSensitive() throws EClrError {
        return getPropertyValueAsBoolean("CaseSensitive");
    }

    public void setCaseSensitive(boolean value) throws EClrError {
        setPropertyValue("CaseSensitive", value);
    }

    public DataColumnCollection getColumns() throws Exception {
        if (Columns == null)
            Columns = createInstance(getPropertyValueAsClrObject("Columns"), DataColumnCollection.class);
        return Columns;
    }

    public int getMinimumCapacity() throws EClrError {
        return getPropertyValueAsInt("MinimumCapacity");
    }

    public void setMinimumCapacity(int value) throws EClrError {
        setPropertyValue("MinimumCapacity", value);
    }

    public String getNamespace() throws EClrError {
        return getPropertyValueAsString("Namespace");
    }

    public void setNamespace(String value) throws EClrError {
        setPropertyValue("Namespace", value);
    }

    public DataColumnArray getPrimaryKey() throws Exception {
        if (PrimaryKey == null)
            PrimaryKey = createInstance(getPropertyValueAsClrObject("PrimaryKey"), DataColumnArray.class);
        return PrimaryKey;
    }

    public void setPrimaryKey(DataColumnArray value) throws EClrError {
        PrimaryKey = value;
        setPropertyValue("PrimaryKey", value);
    }

    public SerializationFormat getRemotingFormat() throws EClrError {
        return EnumUtils.fromInteger(getPropertyValueAsInt("RemotingFormat"), SerializationFormat.class);
    }

    public void setRemotingFormat(SerializationFormat value) throws EClrError {
        setPropertyValue("RemotingFormat", value);
    }

    public DataRowCollection getRows() throws Exception {
        if (Rows == null)
            Rows = createInstance(getPropertyValueAsClrObject("Rows"), DataRowCollection.class);
        return Rows;
    }

    public String getTableName() throws EClrError {
        return getPropertyValueAsString("TableName");
    }

    public void setTableName(String value) throws EClrError {
        setPropertyValue("TableName", value);
    }
}