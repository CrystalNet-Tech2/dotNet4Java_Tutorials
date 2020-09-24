package Data;

import Data.SystemData.*;
import Data.SystemData.Enums.DataRowState;
import Data.SystemData.Enums.LoadOption;
import Data.SystemData.Enums.SerializationFormat;
import Data.SystemData.Events.DataColumnChangeEventArgs;
import Data.SystemData.Events.DataColumnChangeEventHandler;
import Data.SystemData.Events.DataRowChangeEventArgs;
import Data.SystemData.Events.DataRowChangeEventHandler;
import com.dotNet4Java.IClrGenericArray;
import com.dotNet4Java.TClrType;
import com.dotNet4Java.api.core.DotNetNativeTypes;
import com.dotNet4Java.api.util.ClrActivatorUtils;
import com.dotNet4Java.api.util.ClrObjectUtils;

import java.util.EnumSet;

public class DataTableTest {

    private static DataTable createDataTable() throws Exception {
        // Create a DataSet with one table, two columns
        DataTable t = new DataTable("Items");

        // Add two columns
        DataColumn c;

        // First column
        c = t.getColumns().Add("id", TClrType.getType("System.Int32"));
        c.setAutoIncrement(true);

        // Second column
        t.getColumns().Add("item", TClrType.getType("System.String"));

        // Set primary key
        t.setPrimaryKey(new DataColumnArray(new DataColumn[]{t.getColumns().getItem(0)}));

        // Add twelve rows
        for (int i = 0; i < 10; i++) {
            DataRow row = t.NewRow();
            row.setItem(0, i);
            row.setItem(1, String.format("%s", i));
            t.getRows().Add(row);
        }
        DataRow row = t.NewRow();
        row.setItem(0, 11);
        row.setItem(1, "abc");
        t.getRows().Add(row);

        row = t.NewRow();
        row.setItem(0, 15);
        row.setItem(1, "ABC");
        t.getRows().Add(row);

        return t;
    }

    public void Constructor1() throws Exception {
        DataTable dataTable = new DataTable("table", "tableSpace");

        System.out.println(dataTable.getTableName());
        System.out.println(dataTable.getNamespace());
    }

    public void acceptChanges() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);

        System.out.println(row.getRowState().containsAll(EnumSet.of(DataRowState.Added)));
        System.out.println(table.GetChanges().getRows().getCount());
        table.AcceptChanges();

        System.out.println(column.getAllowDBNull());

        System.out.println(row.getRowState().containsAll(EnumSet.of(DataRowState.Unchanged)));
    }


    public void clear() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        System.out.println(table.getRows().getCount());

        table.Clear();

        System.out.println(table.getRows().getCount());
    }


    public void testClone() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        System.out.println(cloneTable.getRows().getCount());
        System.out.println(cloneTable.getColumns().getCount());
        System.out.println(cloneTable.getColumns().getItem(0).getDataType().getName());
    }


    public void compute() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("EmpId");
        column.setColumnName("EmpId");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        column = table.getColumns().Add();
        column.setAllowDBNull(false);
        column.setCaption("Total");
        column.setColumnName("Total");
        column.setDataType(TClrType.getType("System.Double"));

        DataRow row = table.NewRow();
        row.setItem(0, "5");
        row.setItem(1, 45);
        table.getRows().Add(row);

        row = table.NewRow();
        row.setItem(0, "5");
        row.setItem(1, 56);
        table.getRows().Add(row);

        table.AcceptChanges();

        Object sumObject = table.Compute("Sum(Total)", "EmpID = 5");
        System.out.println(sumObject);
    }


    public void copy() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        DataTable copyTable = table.Copy();
        System.out.println(copyTable.getRows().getCount());
        System.out.println(copyTable.getColumns().getCount());
    }


    public void getChanges1() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);

        System.out.println(table.GetChanges().getRows().getCount());
        System.out.println(table.GetChanges().getRows().getItem(0).getItem(0));
    }


    public void getChanges2() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);

        //System.out.println(table.GetChanges(EnumSet.of(DataRowState.Added)).getRows().getCount());
    }


    public void getChanges3() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);

//        System.out.println(null, table.GetChanges(EnumSet.of(DataRowState.Unchanged, DataRowState.Deleted)));
    }


    public void importRow() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        cloneTable.ImportRow(row);

        System.out.println(cloneTable.getRows().getCount());
        System.out.println(cloneTable.getRows().getItem(0).getItem(0));
    }


    public void loadDataRow1() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        table.LoadDataRow(new Object[]{"Data 2"}, true);

//        System.out.println(null, table.GetChanges());
        System.out.println(table.getRows().getItem(1).getItem(0));

        table.LoadDataRow(new Object[]{"Data 2"}, false);

        System.out.println(table.GetChanges().getRows().getCount());
        System.out.println(table.GetChanges().getRows().getItem(0).getItem(0));
    }


    public void loadDataRow2() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);
        table.AcceptChanges();

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.PreserveChanges);

        System.out.println(table.getRows().getCount());
        System.out.println(table.getRows().getItem(1).getItem(0));

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.OverwriteChanges);

        System.out.println(table.getRows().getCount());

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.Upsert);

        System.out.println(table.getRows().getCount());
        System.out.println(table.getRows().getItem(1).getItem(0));
        System.out.println(table.getRows().getItem(2).getItem(0));
    }


    public void merge() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "Data 2");
        table.getRows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        cloneTable.Merge(table, true);

        System.out.println(table.getRows().getCount());
        System.out.println(cloneTable.getRows().getCount());
        System.out.println(table.getRows().getItem(0).getItem(0));
        System.out.println(cloneTable.getRows().getItem(0).getItem(0));
    }


    public void newRow() throws Exception {
        DataTable table = new DataTable("Table");

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "Data 2");
        table.getRows().Add(row);

        System.out.println(table.getRows().getCount());
        System.out.println(table.getRows().getItem(0).getItem(0));
        System.out.println(row.getItem(0));
    }


    public void rejectChanges() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.getColumns().Add();
        column.setAllowDBNull(true);
        column.setCaption("MyData");
        column.setColumnName("MyData");

        column.setDataType(TClrType.getType("System.String"));
        column.setMaxLength(255);

        DataRow row = table.NewRow();
        row.setItem(0, "dataTable");
        table.getRows().Add(row);

        System.out.println(row.getRowState().containsAll(EnumSet.of(DataRowState.Added)));
        System.out.println(table.GetChanges().getRows().getCount());
        table.RejectChanges();

        System.out.println(row.getRowState().containsAll(EnumSet.of(DataRowState.Detached)));
        System.out.println(table.getRows().getCount());
    }


    public void columnChanged() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.getColumns().Add("id", TClrType.getType("System.Int32"));
        custTable.getColumns().Add("name", TClrType.getType("System.String"));
        custTable.getColumns().Add("address", TClrType.getType("System.String"));

        // set PrimaryKey
        custTable.getColumns().getItem(0).setUnique(true);
        custTable.setPrimaryKey(new DataColumnArray(new DataColumn[]{custTable.getColumns().getItem(0)}));

        // add a ColumnChanged event handler for the table.
        custTable.addColumnChanged(new DataColumnChangeEventHandler() {
            @Override
            public void invoke(Object sender, DataColumnChangeEventArgs e) throws Exception {
                System.out.println(e.getColumn().getColumnName() + " changed to '" + e.getProposedValue());
            }
        });

        // assertNotNull(custTable.getColumnChanged());
        // add ten rows
        for (int id = 1; id <= 10; id++) {
            custTable.getRows().Add(
                    new Object[]{id, String.format("customer%s", id),
                            String.format("address%s", id)});
        }

        custTable.AcceptChanges();

        // change the name column in all the rows
        for (int i = 0; i < custTable.getRows().getCount(); i++) {
            custTable.getRows().getItem(i).setItem(1, String.format("vip%s", custTable.getRows().getItem(i).getItem(0)));
        }
    }


    public void RowChanged() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.getColumns().Add("id", TClrType.getType("System.Int32"));
        custTable.getColumns().Add("name", TClrType.getType("System.String"));
        custTable.getColumns().Add("address", TClrType.getType("System.String"));

        // set PrimaryKey
        custTable.getColumns().getItem(0).setUnique(true);
        custTable.setPrimaryKey(new DataColumnArray(new DataColumn[]{custTable.getColumns().getItem(0)}));

        // add a RowChanged event handler for the table.
        custTable.addRowChanged(new DataRowChangeEventHandler() {
            @Override
            public void invoke(Object sender, DataRowChangeEventArgs e) throws Exception {
                System.out.println(String.format("Row_Changed Event: name=%s; action=%s", e.getRow().getItem(1), e.getAction()));
            }
        });

        //assertNotNull(custTable.getRowChanged());

        // add ten rows
        for (int id = 1; id <= 10; id++) {
//            // Not working, try all possible means to work
//            custTable.getRows().Add(
//                    new Object[]{ id, String.format("customer%s", id),
//                            String.format("address%s", id)});

            DataRow row = custTable.NewRow();
            row.setItem(0, id);
            row.setItem(1, String.format("customer%s", id));
            row.setItem(2, String.format("address%s", id));
            custTable.getRows().Add(row);
        }

        custTable.AcceptChanges();

        // change the name column in all the rows
        for (int i = 0; i < custTable.getRows().getCount(); i++) {
            custTable.getRows().getItem(i).setItem(1, String.format("vip%s", custTable.getRows().getItem(i).getItem(0)));
        }
    }


    public void getCaseSensitive() throws Exception {
        DataTable t = new DataTable();
        t.setCaseSensitive(false);
        System.out.println(t.getCaseSensitive());

        t.setCaseSensitive(true);
        System.out.println(t.getCaseSensitive());
    }


    public void setCaseSensitive() throws Exception {
        DataTable t;
        DataRowArray foundRows;

        t = createDataTable();

        t.setCaseSensitive(false);
        foundRows = t.Select("item = 'abc'");

        System.out.println(foundRows.getLength());

        t.setCaseSensitive(true);
        foundRows = t.Select("item = 'abc'");

        System.out.println(foundRows.getLength());
    }


    public void MinimumCapacity() throws Exception {
        DataTable t = new DataTable();
        t.setMinimumCapacity(344);
        System.out.println(t.getMinimumCapacity());
    }


    public void Namespace() throws Exception {
        DataTable t = new DataTable();
        t.setNamespace("MyNamespace");
        System.out.println(t.getNamespace());
    }


    public void PrimaryKey() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.getColumns().Add("id", TClrType.getType("System.Int32"));
        custTable.getColumns().Add("name", TClrType.getType("System.String"));
        custTable.getColumns().Add("address", TClrType.getType("System.String"));

        // set PrimaryKey
        custTable.getColumns().getItem(0).setUnique(true);
        custTable.setPrimaryKey(new DataColumnArray(new DataColumn[]{custTable.getColumns().getItem(0)}));

        System.out.println(custTable.getPrimaryKey().getLength());
        System.out.println(custTable.getPrimaryKey().get(0).getColumnName());
    }


    public void RemotingFormat() throws Exception {
        DataTable t = new DataTable("Customers");
        // add columns
        t.getColumns().Add("id", TClrType.getType("System.Int32"));
        t.getColumns().Add("name", TClrType.getType("System.String"));
        t.getColumns().Add("address", TClrType.getType("System.String"));

        System.out.println(t.getRemotingFormat());
        t.setRemotingFormat(SerializationFormat.Binary);
        System.out.println(t.getRemotingFormat());
    }


    public void TableName() throws Exception {
        DataTable t = new DataTable("MyTable");
        System.out.println(t.getTableName());
        t.setTableName("DataSetTable");
        System.out.println(t.getTableName());
    }


    public void DataRowCollection_Clear() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.getRows().getCount() > 0);
        table.getRows().Clear();
        System.out.println(table.getRows().getCount() == 0);
    }


    public void DataRowCollection_Contains1() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.getRows().Contains(1));
    }


    public void DataRowCollection_Contains2() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.getRows().Contains(new Object[]{0}));
    }


    public void DataRowCollection_CopyTo() throws Exception {
        DataTable table = createDataTable();
        DataRowArray array = new DataRowArray(table.getRows().getCount());
        table.getRows().CopyTo(array, 0);

        System.out.println(array.getLength() > 0);
        System.out.println(array.get(0).getItem(0));
        System.out.println(array.get(0).getItem(1));
        System.out.println(array.get(1).getItem(0));
        System.out.println(array.get(1).getItem(1));
    }


    public void DataRowCollection_Find1() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.getRows().Find(1);
    }


    public void DataRowCollection_Find2() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.getRows().Find(new Object[]{1});
    }


    public void DataRowCollection_Index() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.getRows().getItem(0);
        int indexOf = table.getRows().IndexOf(row);
        System.out.println(indexOf);
    }


    public void DataRowCollection_Remove() throws Exception {
        DataTable table = createDataTable();
        int rowCount = table.getRows().getCount();
        DataRow row = table.getRows().getItem(0);
        table.getRows().Remove(row);
        System.out.println(table.getRows().getCount());
    }


    public void DataRowCollection_Remove2() throws Exception {
        DataTable table = createDataTable();
        int rowCount = table.getRows().getCount();
        table.getRows().RemoveAt(0);
        System.out.println(table.getRows().getCount());
    }


    public void DataColumnArray_Constructor() throws Exception {
        DataTable table = createDataTable();
        DataColumnArray array = new DataColumnArray(table.getPrimaryKey().getBaseObject());

        System.out.println(array.getLength());
    }


    public void DataColumnArray_Constructor2() throws Exception {
        DataTable table = createDataTable();
        DataColumnArray array = new DataColumnArray(table.getPrimaryKey().getDefaultPointer());

        System.out.println(array.getLength());
    }


    public void DataColumnArray_ToArray() throws Exception {
        DataTable table = createDataTable();
        DataColumn[] array = table.getPrimaryKey().toArray();

        System.out.println(array.length);
    }


    public void DataRow_AcceptChanges() throws Exception {
        //Run a function to create a DataTable with one column.
        // Make a simple table with one column.
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", TClrType.getType("System.String"));
        table.getColumns().Add(fNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        // Detached row.
        System.out.println(row.getRowState());

        table.getRows().Add(row);
        // New row.
        System.out.println(row.getRowState());

        table.AcceptChanges();
        // Unchanged row.
        System.out.println(row.getRowState());

        row.setItem(0, "Scott");
        // Modified row.
        System.out.println(row.getRowState());

        row.Delete();
        // Deleted row.
        System.out.println(row.getRowState());
    }


    public void DataRow_IsNull() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.getRows().getItem(0);
        System.out.println(row.IsNull(1));

        DotNetNativeTypes.ClrObject DbNull = ClrActivatorUtils.createStaticInstance("System.DBNull");

        row.setItem(1, ClrObjectUtils.getFieldValue(DbNull, "Value"));
        System.out.println(row.IsNull(1));
    }


    public void DataRow_RejectChanges() throws Exception {
        // Create a simple DataTable with two columns and ten rows.
        DataTable table = new DataTable("table");
        DataColumn idColumn = new DataColumn("id", TClrType.getType("System.Int32"));
        idColumn.setAutoIncrement(true);
        DataColumn itemColumn = new DataColumn("item", TClrType.getType("System.String"));
        table.getColumns().Add(idColumn);
        table.getColumns().Add(itemColumn);

        // Add ten rows.
        DataRow newRow;

        for (int i = 0; i < 10; i++) {
            newRow = table.NewRow();
            newRow.setItem(1, "Item " + i);
            table.getRows().Add(newRow);
        }
        table.AcceptChanges();

        DataRowCollection itemColumns = table.getRows();
        itemColumns.getItem(0).Delete();
        itemColumns.getItem(2).Delete();
        itemColumns.getItem(3).Delete();
        itemColumns.getItem(5).Delete();
        System.out.println(itemColumns.getItem(3).getRowState());

        // Reject changes on one deletion.
        itemColumns.getItem(3).RejectChanges();

        // Change the value of the column so it stands out.
        itemColumns.getItem(3).setItem(1, "Deleted, Undeleted, Edited");

        // Accept changes on others.
        table.AcceptChanges();

        System.out.println(idColumn.getAutoIncrement());
        System.out.println(itemColumns.getItem(3).getItem(1));
    }


    public void DataRow_getItemArray() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", TClrType.getType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", TClrType.getType("System.String"));
        table.getColumns().Add(fNameColumn);
        table.getColumns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.setItem(0, "James");
        row.setItem(1, "Andrew");
        table.getRows().Add(row);
        table.AcceptChanges();

        IClrGenericArray<Object> items = row.getItemArray();

        System.out.println(items.getLength());
        System.out.println(items.get(0));
        System.out.println(items.get(1));
    }


    public void DataRow_setItemArray() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", TClrType.getType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", TClrType.getType("System.String"));
        table.getColumns().Add(fNameColumn);
        table.getColumns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.setItemArray(new Object[]{"James", "Andrew"});
        table.AcceptChanges();

        IClrGenericArray<Object> items = row.getItemArray();

        System.out.println(items.getLength());
        System.out.println(items.get(0));
        System.out.println(items.get(1));
    }


    public void DataRow_getTable() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", TClrType.getType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", TClrType.getType("System.String"));
        table.getColumns().Add(fNameColumn);
        table.getColumns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.setItem(0, "James");
        row.setItem(1, "Andrew");
        table.AcceptChanges();

        DataTable dataTable = row.getTable();

        System.out.println(dataTable.equals(table));
    }


    public void DataColumn_getTable() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", TClrType.getType("System.String"));
        table.getColumns().Add(fNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.setItem(0, "James");
        table.AcceptChanges();

        DataTable dataTable = fNameColumn.getTable();

        System.out.println(dataTable.equals(table));
    }

    public void DataColumnCollection_CopyTo() throws Exception {
        DataTable table = createDataTable();
        DataColumnArray array = new DataColumnArray(table.getColumns().getCount());
        table.getColumns().CopyTo(array, 0);

        System.out.println(array.getLength() > 0);
        System.out.println(array.get(0).getColumnName());
        System.out.println(array.get(1).getColumnName());
    }
}