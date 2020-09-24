package Data.SystemData;

import com.dotNet4Java.IClrAssembly;
import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.enums.AssemblyLoadType;

public class dotNetAssembly {
    public static final dotNetAssembly INSTANCE = new dotNetAssembly();
    private final static String sC_SystemData_Asm_ID = "9096b7e8-7d54-49a5-b125-af32f581eef3";
    private final static String sC_SystemData_sC_AssemblyPath = "System.Data, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089";

    static {
        TClrAssembly.register(sC_SystemData_Asm_ID, sC_SystemData_sC_AssemblyPath, AssemblyLoadType.GACByFullName);
    }

    public final String sC_Sys_Boolean = "System.Boolean";
    public final String sC_Sys_Int32 = "System.Int32";
    public final String sC_Sys_Object_ = "System.Object";
    public final String sC_Sys_ObjectArray = "System.Object[]";
    public final String sC_Sys_String_ = "System.String";
    public final String sC_Sys_Type_ = "System.Type";
    public final String sC_SysDat_DataColumn = "System.Data.DataColumn";
    public final String sC_SysDat_DataColumnArray = "System.Data.DataColumn[]";
    public final String sC_SysDat_DataColumnChangeEventArgs = "System.Data.DataColumnChangeEventArgs";
    public final String sC_SysDat_DataRow = "System.Data.DataRow";
    public final String sC_SysDat_DataRowArray = "System.Data.DataRow[]";
    public final String sC_SysDat_DataRowChangeEventArgs = "System.Data.DataRowChangeEventArgs";
    public final String sC_SysDat_DataRowState = "System.Data.DataRowState";
    public final String sC_SysDat_DataRowVersion = "System.Data.DataRowVersion";
    public final String sC_SysDat_DataTable = "System.Data.DataTable";
    public final String sC_SysDat_XmlWriteMode = "System.Data.XmlWriteMode";
    public final String sc_sys_Console = "System.Console";
    private IClrAssembly dotNetAssembly;

    public IClrAssembly getAssembly() throws EClrError {
        if (dotNetAssembly == null)
            dotNetAssembly = TClrAssembly.getRegisteredAssembly(sC_SystemData_Asm_ID);
        return dotNetAssembly;
    }
}
