package DataTable;

import com.dotNet4Java.IClrGenericArray;
import system.DBNull;
import system.Type;
import system.data.*;

import java.util.EnumSet;

public class DataTableTest {

    private static DataTable createDataTable() throws Exception {
        // Create a DataSet with one table, two columns
        DataTable t = new DataTable("Items");

        // Add two columns
        DataColumn c;

        // First column
        c = t.get_Columns().Add("id", Type.GetType("System.Int32"));
        c.set_AutoIncrement(true);

        // Second column
        t.get_Columns().Add("item", Type.GetType("System.String"));

        // Set primary key
        t.set_PrimaryKey(new DataColumn[]{t.get_Columns().get_Item(0)});

        // Add twelve rows
        for (int i = 0; i < 10; i++) {
            DataRow row = t.NewRow();
            row.set_Item(0, i);
            row.set_Item(1, String.format("%s", i));
            t.get_Rows().Add(row);
        }
        DataRow row = t.NewRow();
        row.set_Item(0, 11);
        row.set_Item(1, "abc");
        t.get_Rows().Add(row);

        row = t.NewRow();
        row.set_Item(0, 15);
        row.set_Item(1, "ABC");
        t.get_Rows().Add(row);

        return t;
    }

    public void Constructor1() throws Exception {
        DataTable dataTable = new DataTable("table", "tableSpace");

        System.out.println(dataTable.get_TableName());
        System.out.println(dataTable.get_Namespace());
    }

    public void acceptChanges() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);

        System.out.println(row.get_RowState().containsAll(EnumSet.of(DataRowState.Added)));
        System.out.println(table.GetChanges().get_Rows().get_Count());
        table.AcceptChanges();

        System.out.println(column.get_AllowDBNull());

        System.out.println(row.get_RowState().containsAll(EnumSet.of(DataRowState.Unchanged)));
    }


    public void clear() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        System.out.println(table.get_Rows().get_Count());

        table.Clear();

        System.out.println(table.get_Rows().get_Count());
    }


    public void testClone() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        System.out.println(cloneTable.get_Rows().get_Count());
        System.out.println(cloneTable.get_Columns().get_Count());
        System.out.println(cloneTable.get_Columns().get_Item(0).get_DataType().get_Name());
    }


    public void compute() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("EmpId");
        column.set_ColumnName("EmpId");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        column = table.get_Columns().Add();
        column.set_AllowDBNull(false);
        column.set_Caption("Total");
        column.set_ColumnName("Total");
        column.set_DataType(Type.GetType("System.Double"));

        DataRow row = table.NewRow();
        row.set_Item(0, "5");
        row.set_Item(1, 45);
        table.get_Rows().Add(row);

        row = table.NewRow();
        row.set_Item(0, "5");
        row.set_Item(1, 56);
        table.get_Rows().Add(row);

        table.AcceptChanges();

        Object sumObject = table.Compute("Sum(Total)", "EmpID = 5");
        System.out.println(sumObject);
    }


    public void copy() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        DataTable copyTable = table.Copy();
        System.out.println(copyTable.get_Rows().get_Count());
        System.out.println(copyTable.get_Columns().get_Count());
    }


    public void getChanges1() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);

        System.out.println(table.GetChanges().get_Rows().get_Count());
        System.out.println(table.GetChanges().get_Rows().get_Item(0).get_Item(0));
    }


    public void getChanges2() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);

        //System.out.println(table.GetChanges(EnumSet.of(DataRowState.Added)).get_Rows().get_Count());
    }


    public void getChanges3() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);

//        System.out.println(null, table.GetChanges(EnumSet.of(DataRowState.Unchanged, DataRowState.Deleted)));
    }


    public void importRow() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        cloneTable.ImportRow(row);

        System.out.println(cloneTable.get_Rows().get_Count());
        System.out.println(cloneTable.get_Rows().get_Item(0).get_Item(0));
    }


    public void loadDataRow1() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        table.LoadDataRow(new Object[]{"Data 2"}, true);

//        System.out.println(null, table.GetChanges());
        System.out.println(table.get_Rows().get_Item(1).get_Item(0));

        table.LoadDataRow(new Object[]{"Data 2"}, false);

        System.out.println(table.GetChanges().get_Rows().get_Count());
        System.out.println(table.GetChanges().get_Rows().get_Item(0).get_Item(0));
    }


    public void loadDataRow2() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.PreserveChanges);

        System.out.println(table.get_Rows().get_Count());
        System.out.println(table.get_Rows().get_Item(1).get_Item(0));

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.OverwriteChanges);

        System.out.println(table.get_Rows().get_Count());

        table.LoadDataRow(new Object[]{"Data 2"}, LoadOption.Upsert);

        System.out.println(table.get_Rows().get_Count());
        System.out.println(table.get_Rows().get_Item(1).get_Item(0));
        System.out.println(table.get_Rows().get_Item(2).get_Item(0));
    }


    public void merge() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "Data 2");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        DataTable cloneTable = table.Clone();
        cloneTable.Merge(table, true);

        System.out.println(table.get_Rows().get_Count());
        System.out.println(cloneTable.get_Rows().get_Count());
        System.out.println(table.get_Rows().get_Item(0).get_Item(0));
        System.out.println(cloneTable.get_Rows().get_Item(0).get_Item(0));
    }


    public void newRow() throws Exception {
        DataTable table = new DataTable("Table");

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "Data 2");
        table.get_Rows().Add(row);

        System.out.println(table.get_Rows().get_Count());
        System.out.println(table.get_Rows().get_Item(0).get_Item(0));
        System.out.println(row.get_Item(0));
    }


    public void rejectChanges() throws Exception {
        DataTable table = new DataTable();

        DataColumn column = table.get_Columns().Add();
        column.set_AllowDBNull(true);
        column.set_Caption("MyData");
        column.set_ColumnName("MyData");

        column.set_DataType(Type.GetType("System.String"));
        column.set_MaxLength(255);

        DataRow row = table.NewRow();
        row.set_Item(0, "dataTable");
        table.get_Rows().Add(row);

        System.out.println(row.get_RowState().containsAll(EnumSet.of(DataRowState.Added)));
        System.out.println(table.GetChanges().get_Rows().get_Count());
        table.RejectChanges();

        System.out.println(row.get_RowState().containsAll(EnumSet.of(DataRowState.Detached)));
        System.out.println(table.get_Rows().get_Count());
    }


    public void columnChanged() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.get_Columns().Add("id", Type.GetType("System.Int32"));
        custTable.get_Columns().Add("name", Type.GetType("System.String"));
        custTable.get_Columns().Add("address", Type.GetType("System.String"));

        // set_ PrimaryKey
        custTable.get_Columns().get_Item(0).set_Unique(true);
        custTable.set_PrimaryKey(new DataColumn[]{custTable.get_Columns().get_Item(0)});

        // add a ColumnChanged event handler for the table.
        custTable.add_ColumnChanged(new DataColumnChangeEventHandler() {
            @Override
            public void invoke(Object sender, DataColumnChangeEventArgs e) throws Exception {
                System.out.println(e.get_Column().get_ColumnName() + " changed to '" + e.get_ProposedValue());
            }
        });

        // assertNotNull(custTable.get_ColumnChanged());
        // add ten rows
        for (int id = 1; id <= 10; id++) {
            custTable.get_Rows().Add(
                    new Object[]{id, String.format("customer%s", id),
                            String.format("address%s", id)});
        }

        custTable.AcceptChanges();

        // change the name column in all the rows
        for (int i = 0; i < custTable.get_Rows().get_Count(); i++) {
            custTable.get_Rows().get_Item(i).set_Item(1, String.format("vip%s", custTable.get_Rows().get_Item(i).get_Item(0)));
        }
    }


    public void RowChanged() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.get_Columns().Add("id", Type.GetType("System.Int32"));
        custTable.get_Columns().Add("name", Type.GetType("System.String"));
        custTable.get_Columns().Add("address", Type.GetType("System.String"));

        // set_ PrimaryKey
        custTable.get_Columns().get_Item(0).set_Unique(true);
        custTable.set_PrimaryKey(new DataColumn[]{custTable.get_Columns().get_Item(0)});

        // add a RowChanged event handler for the table.
        custTable.add_RowChanged(new DataRowChangeEventHandler() {
            @Override
            public void invoke(Object sender, DataRowChangeEventArgs e) throws Exception {
                System.out.println(String.format("Row_Changed Event: name=%s; action=%s", e.get_Row().get_Item(1), e.get_Action()));
            }
        });

        //assertNotNull(custTable.get_RowChanged());

        // add ten rows
        for (int id = 1; id <= 10; id++) {
//            // Not working, try all possible means to work
//            custTable.get_Rows().Add(
//                    new Object[]{ id, String.format("customer%s", id),
//                            String.format("address%s", id)});

            DataRow row = custTable.NewRow();
            row.set_Item(0, id);
            row.set_Item(1, String.format("customer%s", id));
            row.set_Item(2, String.format("address%s", id));
            custTable.get_Rows().Add(row);
        }

        custTable.AcceptChanges();

        // change the name column in all the rows
        for (int i = 0; i < custTable.get_Rows().get_Count(); i++) {
            custTable.get_Rows().get_Item(i).set_Item(1, String.format("vip%s", custTable.get_Rows().get_Item(i).get_Item(0)));
        }
    }


    public void getCaseSensitive() throws Exception {
        DataTable t = new DataTable();
        t.set_CaseSensitive(false);
        System.out.println(t.get_CaseSensitive());

        t.set_CaseSensitive(true);
        System.out.println(t.get_CaseSensitive());
    }


    public void setCaseSensitive() throws Exception {
        DataTable t;
        IClrGenericArray<DataRow> foundRows;

        t = createDataTable();

        t.set_CaseSensitive(false);
        foundRows = t.Select("item = 'abc'");

        System.out.println(foundRows.getLength());

        t.set_CaseSensitive(true);
        foundRows = t.Select("item = 'abc'");

        System.out.println(foundRows.getLength());
    }


    public void MinimumCapacity() throws Exception {
        DataTable t = new DataTable();
        t.set_MinimumCapacity(344);
        System.out.println(t.get_MinimumCapacity());
    }


    public void Namespace() throws Exception {
        DataTable t = new DataTable();
        t.set_Namespace("MyNamespace");
        System.out.println(t.get_Namespace());
    }


    public void PrimaryKey() throws Exception {
        DataTable custTable = new DataTable("Customers");
        // add columns
        custTable.get_Columns().Add("id", Type.GetType("System.Int32"));
        custTable.get_Columns().Add("name", Type.GetType("System.String"));
        custTable.get_Columns().Add("address", Type.GetType("System.String"));

        // set_ PrimaryKey
        custTable.get_Columns().get_Item(0).set_Unique(true);
        custTable.set_PrimaryKey(new DataColumn[]{custTable.get_Columns().get_Item(0)});

        System.out.println(custTable.get_PrimaryKey().getLength());
        System.out.println(custTable.get_PrimaryKey().get(0).get_ColumnName());
    }


    public void RemotingFormat() throws Exception {
        DataTable t = new DataTable("Customers");
        // add columns
        t.get_Columns().Add("id", Type.GetType("System.Int32"));
        t.get_Columns().Add("name", Type.GetType("System.String"));
        t.get_Columns().Add("address", Type.GetType("System.String"));

        System.out.println(t.get_RemotingFormat());
        t.set_RemotingFormat(SerializationFormat.Binary);
        System.out.println(t.get_RemotingFormat());
    }


    public void TableName() throws Exception {
        DataTable t = new DataTable("MyTable");
        System.out.println(t.get_TableName());
        t.set_TableName("DataSetTable");
        System.out.println(t.get_TableName());
    }


    public void DataRowCollection_Clear() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.get_Rows().get_Count() > 0);
        table.get_Rows().Clear();
        System.out.println(table.get_Rows().get_Count() == 0);
    }


    public void DataRowCollection_Contains1() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.get_Rows().Contains(1));
    }


    public void DataRowCollection_Contains2() throws Exception {
        DataTable table = createDataTable();
        System.out.println(table.get_Rows().Contains(new Object[]{0}));
    }


//    public void DataRowCollection_CopyTo() throws Exception {
//        DataTable table = createDataTable();
//        DataRow[] array = new DataRow[table.get_Rows().get_Count()];
//        table.get_Rows().CopyTo(array, 0);
//
//        System.out.println(array.length > 0);
//        System.out.println(array[0].get_Item(0));
//        System.out.println(array[0].get_Item(1));
//        System.out.println(array[1].get_Item(0));
//        System.out.println(array[1].get_Item(1));
//    }


    public void DataRowCollection_Find1() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.get_Rows().Find(1);
    }


    public void DataRowCollection_Find2() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.get_Rows().Find(new Object[]{1});
    }


    public void DataRowCollection_Index() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.get_Rows().get_Item(0);
        int indexOf = table.get_Rows().IndexOf(row);
        System.out.println(indexOf);
    }


    public void DataRowCollection_Remove() throws Exception {
        DataTable table = createDataTable();
        int rowCount = table.get_Rows().get_Count();
        DataRow row = table.get_Rows().get_Item(0);
        table.get_Rows().Remove(row);
        System.out.println(table.get_Rows().get_Count());
    }


    public void DataRowCollection_Remove2() throws Exception {
        DataTable table = createDataTable();
        int rowCount = table.get_Rows().get_Count();
        table.get_Rows().RemoveAt(0);
        System.out.println(table.get_Rows().get_Count());
    }


    public void DataColumnArray_Constructor() throws Exception {
        DataTable table = createDataTable();
        IClrGenericArray<DataColumn> array = table.get_PrimaryKey();

        System.out.println(array.getLength());
    }


    public void DataColumnArray_Constructor2() throws Exception {
        DataTable table = createDataTable();
        IClrGenericArray<DataColumn> array = table.get_PrimaryKey();

        System.out.println(array.getLength());
    }


    public void DataColumnArray_ToArray() throws Exception {
        DataTable table = createDataTable();
        DataColumn[] array = table.get_PrimaryKey().toArray();

        System.out.println(array.length);
    }


    public void DataRow_AcceptChanges() throws Exception {
        //Run a function to create a DataTable with one column.
        // Make a simple table with one column.
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", Type.GetType("System.String"));
        table.get_Columns().Add(fNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        // Detached row.
        System.out.println(row.get_RowState());

        table.get_Rows().Add(row);
        // New row.
        System.out.println(row.get_RowState());

        table.AcceptChanges();
        // Unchanged row.
        System.out.println(row.get_RowState());

        row.set_Item(0, "Scott");
        // Modified row.
        System.out.println(row.get_RowState());

        row.Delete();
        // Deleted row.
        System.out.println(row.get_RowState());
    }


    public void DataRow_IsNull() throws Exception {
        DataTable table = createDataTable();
        DataRow row = table.get_Rows().get_Item(0);
        System.out.println(row.IsNull(1));

        row.set_Item(1, DBNull.getValue());
        System.out.println(row.IsNull(1));
    }


    public void DataRow_RejectChanges() throws Exception {
        // Create a simple DataTable with two columns and ten rows.
        DataTable table = new DataTable("table");
        DataColumn idColumn = new DataColumn("id", Type.GetType("System.Int32"));
        idColumn.set_AutoIncrement(true);
        DataColumn itemColumn = new DataColumn("item", Type.GetType("System.String"));
        table.get_Columns().Add(idColumn);
        table.get_Columns().Add(itemColumn);

        // Add ten rows.
        DataRow newRow;

        for (int i = 0; i < 10; i++) {
            newRow = table.NewRow();
            newRow.set_Item(1, "Item " + i);
            table.get_Rows().Add(newRow);
        }
        table.AcceptChanges();

        DataRowCollection itemColumns = table.get_Rows();
        itemColumns.get_Item(0).Delete();
        itemColumns.get_Item(2).Delete();
        itemColumns.get_Item(3).Delete();
        itemColumns.get_Item(5).Delete();
        System.out.println(itemColumns.get_Item(3).get_RowState());

        // Reject changes on one deletion.
        itemColumns.get_Item(3).RejectChanges();

        // Change the value of the column so it stands out.
        itemColumns.get_Item(3).set_Item(1, "Deleted, Undeleted, Edited");

        // Accept changes on others.
        table.AcceptChanges();

        System.out.println(idColumn.get_AutoIncrement());
        System.out.println(itemColumns.get_Item(3).get_Item(1));
    }


    public void DataRow_getItemArray() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", Type.GetType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", Type.GetType("System.String"));
        table.get_Columns().Add(fNameColumn);
        table.get_Columns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.set_Item(0, "James");
        row.set_Item(1, "Andrew");
        table.get_Rows().Add(row);
        table.AcceptChanges();

        IClrGenericArray<system.Object> items = row.get_ItemArray();

        System.out.println(items.getLength());
        System.out.println(items.get(0));
        System.out.println(items.get(1));
    }


    public void DataRow_setItemArray() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", Type.GetType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", Type.GetType("System.String"));
        table.get_Columns().Add(fNameColumn);
        table.get_Columns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.set_ItemArray(new Object[]{"James", "Andrew"});
        table.AcceptChanges();

        IClrGenericArray<system.Object> items = row.get_ItemArray();

        System.out.println(items.getLength());
        System.out.println(items.get(0));
        System.out.println(items.get(1));
    }


    public void DataRow_getTable() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", Type.GetType("System.String"));
        DataColumn lNameColumn = new DataColumn("LastName", Type.GetType("System.String"));
        table.get_Columns().Add(fNameColumn);
        table.get_Columns().Add(lNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.set_Item(0, "James");
        row.set_Item(1, "Andrew");
        table.AcceptChanges();

        DataTable dataTable = row.get_Table();

        System.out.println(dataTable.equals(table));
    }


    public void DataColumn_getTable() throws Exception {
        DataTable table = new DataTable("table");
        DataColumn fNameColumn = new DataColumn("FirstName", Type.GetType("System.String"));
        table.get_Columns().Add(fNameColumn);
        DataRow row;

        // Create a new DataRow.
        row = table.NewRow();
        row.set_Item(0, "James");
        table.AcceptChanges();

        DataTable dataTable = fNameColumn.get_Table();

        System.out.println(dataTable.equals(table));
    }

//    public void DataColumnCollection_CopyTo() throws Exception {
//        DataTable table = createDataTable();
//        DataColumn[] array = new DataColumn[table.get_Columns().get_Count()];
//        table.get_Columns().CopyTo(array, 0);
//
//        System.out.println(array.length > 0);
//        System.out.println(array[0].get_ColumnName());
//        System.out.println(array[1].get_ColumnName());
//    }
}