package BasicExamples.ClrObjectExample;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.TClrObject;

public class ClrObjectExample {

    static void createDotNetSqlConnectionObject() throws Exception {

        IClrObject sqlConnection = new TClrObject(TClrActivator.createInstance("System.Data.SqlClient.SqlConnection"));

        try {

            String connectionString = "Data Source=MyDataSourceName;Initial Catalog=MyDBName;User ID=MyUserName;Password=MyPasswd";//<Change Me>
            System.out.println("Connecting to SQL Connection using : " + connectionString);
            System.out.println();
            sqlConnection.setPropertyValue("ConnectionString", connectionString);
            System.out.println("SqlConnection.ConnectionString = " + sqlConnection.getPropertyValue("ConnectionString", String.class));
            System.out.println();
            sqlConnection.invokeVoidMethod("Open");
            System.out.println("Connection Opened");
            System.out.println();
            sqlConnection.invokeVoidMethod("Close");
            System.out.println("Connection Closed");

        } finally {
            sqlConnection.close();
        }
    }

    public static void main(String[] arg) {
        System.out.println("           Hello! Welcome to dotNet4Java.         ");
        System.out.println("==================================================");
        System.out.println("This program demonstrates how to use IClrObject to connect to Sql Server.");
        System.out.println();
        try {
            TClrAssembly.loadWithPartialName("System.Data");
            System.out.println("The Assembly [System.Data] has been loaded.");

            createDotNetSqlConnectionObject();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
