package Data;

public class DataTableMain {


    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java         ");
        System.out.println("==================================================");
        System.out.println("The following code example shows how to create and use .Net DataTable.");
        System.out.println();

        try {
            DataTableTest dataTable = new DataTableTest();

            dataTable.Constructor1();
            dataTable.acceptChanges();
            dataTable.clear();
            dataTable.testClone();
            dataTable.compute();
            dataTable.copy();
            dataTable.getChanges1();
            dataTable.getChanges2();
            dataTable.getChanges3();
            dataTable.importRow();
            dataTable.loadDataRow1();
            dataTable.loadDataRow2();
            dataTable.merge();
            dataTable.newRow();
            dataTable.rejectChanges();
            dataTable.columnChanged();
            dataTable.RowChanged();
            dataTable.getCaseSensitive();
            dataTable.setCaseSensitive();
            dataTable.MinimumCapacity();
            dataTable.Namespace();
            dataTable.PrimaryKey();
            dataTable.RemotingFormat();
            dataTable.TableName();
            dataTable.DataRowCollection_Clear();
            dataTable.DataRowCollection_Contains1();
            dataTable.DataRowCollection_Contains2();
            dataTable.DataRowCollection_CopyTo();
            dataTable.DataRowCollection_Find1();
            dataTable.DataRowCollection_Find2();
            dataTable.DataRowCollection_Index();
            dataTable.DataRowCollection_Remove();
            dataTable.DataRowCollection_Remove2();
            dataTable.DataColumnArray_Constructor();
            dataTable.DataColumnArray_Constructor2();
            dataTable.DataColumnArray_ToArray();
            dataTable.DataRow_AcceptChanges();
            dataTable.DataRow_IsNull();
            dataTable.DataRow_RejectChanges();
            dataTable.DataRow_getItemArray();
            dataTable.DataRow_setItemArray();
            dataTable.DataRow_getTable();
            dataTable.DataColumn_getTable();
            dataTable.DataColumnCollection_CopyTo();

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
