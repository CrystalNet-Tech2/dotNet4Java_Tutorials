package AppDomainAndAssemblies.CreateInstance.UsingActivator;

import com.dotNet4Java.*;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.util.ClrExceptionUtils;

public class ActivatorExample {

    static void displayObjectTypeInfo(Object AObject) throws EClrError {
        if (AObject == null) {
            System.out.println("Object has not been instantiated");
        } else {
            IClrType m_type = TClrType.getObjectType(AObject);
            System.out.println("Object has been instantiated");
            System.out.println("Assembly FullName:     " + m_type.getAssembly().getFullName());
            System.out.println("FullName:              " + m_type.getFullName());
            System.out.println("ToString:              " + m_type.toString());
            System.out.println();
            System.out.println();
        }
    }

    static void loadAssembly() throws EClrError {
        TClrAssembly.load("System.Data, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089");
    }

    static void createInstanceUsingTypeName() throws EClrError {
        IClrObject sqlConnection = TClrActivator.createInstance("System.Data.SqlClient.SqlConnection");
        displayObjectTypeInfo(sqlConnection.toObject());
    }

    static void createInstanceUsingType() throws EClrError {
        IClrType sqlConnectionType = TClrAssembly.findType("System.Data.SqlClient.SqlConnection");
        IClrObject sqlConnection = TClrActivator.createInstance(sqlConnectionType);
        displayObjectTypeInfo(sqlConnection.toObject());
    }

    static void createInstanceUsingTypeWithNoConstructorParameters() throws EClrError {
        //Create Instance with a parameter
        Object[] objects = new Object[]{"Data Source=myServerAddress;Initial Catalog=myDataBase;" +
                "User ID=myDomain\\myUsername;Password=myPassword;"}; //<Change Me>

        //Assembly has been loaded in CreateInstance2, no need to reload assembly
        IClrType sqlConnectionType = TClrAssembly.findType("System.Data.SqlClient.SqlConnection");
        IClrObject sqlConnection = TClrActivator.createInstance(sqlConnectionType, objects);
        displayObjectTypeInfo(sqlConnection);
    }

    public static void main(String[] arg) {
        System.out.println("                Hello! Welcome to dotNet4Java            ");
        System.out.println("=========================================================");
        System.out.println("The program demonstrate how to use ActivatorExample Class");
        System.out.println("to create an instance of a .Net Object.           ");
        System.out.println();
        try {
            loadAssembly();
            createInstanceUsingTypeName();
            createInstanceUsingType();
            createInstanceUsingTypeWithNoConstructorParameters();
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}